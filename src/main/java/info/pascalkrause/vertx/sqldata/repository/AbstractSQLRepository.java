package info.pascalkrause.vertx.sqldata.repository;

import java.util.List;
import java.util.function.Supplier;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;

public class AbstractSQLRepository implements SQLRepository<Resource> {

    private final SQLClient client;

    public AbstractSQLRepository(SQLClient client) {
        this.client = client;
    }

    private Future<SQLConnection> getAutoCommitConnection() {
        Future<SQLConnection> autoCommitFuture = Future.future();
        Future<SQLConnection> connFuture = Future.future();
        client.getConnection(connFuture.completer());
        connFuture.compose(conn -> {
            conn.setAutoCommit(true, res -> {
                if(res.failed()) {
                    autoCommitFuture.fail(res.cause());
                } else {
                    autoCommitFuture.complete(conn);
                }
            });
        }, autoCommitFuture);
        return autoCommitFuture;
    }

    @Override
    public SQLRepository<Resource> execute(String sql, Handler<AsyncResult<Void>> resultHandler) {
        Future<Void> resultHandlerFuture = Future.future();
        resultHandlerFuture.setHandler(resultHandler);
        
        Future<SQLConnection> connFuture = getAutoCommitConnection();
        connFuture.compose(conn -> {
            Future<Void> executFutrue = Future.future();
            conn.execute(sql, executFutrue.completer());
            return executFutrue;
        }).compose(v -> {
            connFuture.result().close(resultHandlerFuture.completer());
        }, resultHandlerFuture);
        return this;
    }

    @Override
    public SQLRepository<Resource> count(Handler<AsyncResult<Long>> resultHandler) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SQLRepository<Resource> list(Handler<AsyncResult<List<Resource>>> resultHandler) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SQLRepository<Resource> insert(Resource resource, Handler<AsyncResult<Void>> resultHandler) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SQLRepository<Resource> update(Resource resources, Handler<AsyncResult<Integer>> resultHandler) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SQLRepository<Resource> get(Object id, Handler<AsyncResult<Resource>> resultHandler) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SQLRepository<Resource> delete(Object id, Handler<AsyncResult<Integer>> resultHandler) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SQLRepository<Resource> find(String sql, List<Supplier<Object>> paramSuppliers,
            Handler<AsyncResult<List<Resource>>> resultHandler) {
        // TODO Auto-generated method stub
        return null;
    }
}

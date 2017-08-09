package info.pascalkrause.vertx.sqldata.repository;

import java.util.List;
import java.util.function.Supplier;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public interface SQLRepository<T extends Resource> {

    /**
     * Executes the given SQL statement
     *
     * @param sql the SQL to execute. For example <code>CREATE TABLE IF EXISTS table ...</code>
     * @param resultHandler the handler which is called when this operation completes.
     * @return The actual SQLRepository.
     */
    SQLRepository<T> execute(String sql, Handler<AsyncResult<Void>> resultHandler);

    /**
     * Count all entries in the repository.
     *
     * @param resultHandler the handler which is called when this operation completes, it provides the repository size.
     * @return The actual SQLRepository.
     */
    SQLRepository<T> count(Handler<AsyncResult<Long>> resultHandler);

    /**
     * List all entries in the repository.
     *
     * @param resultHandler the handler which is called when this operation completes, it provides a list with all
     * entries in the repository.
     * @return The actual SQLRepository.
     */
    SQLRepository<T> list(Handler<AsyncResult<List<T>>> resultHandler);

    /**
     * Insert an element into the repository.
     *
     * @param resource the element to insert.
     * @param resultHandler the handler which is called when this operation completes.
     * @return The actual SQLRepository.
     */
    SQLRepository<T> insert(T resource, Handler<AsyncResult<Void>> resultHandler);

    /**
     * Update an element in the repository. The ID is used to identify the target element.
     *
     * @param resource the element to update.
     * @param resultHandler the handler which is called when this operation completes, it provides the number of
     * affected rows.
     * @return The actual SQLRepository.
     */
    SQLRepository<T> update(T resources, Handler<AsyncResult<Integer>> resultHandler);

    /**
     * Retrieve an element from the repository.
     *
     * @param id the id of the element.
     * @param resultHandler the handler which is called when this operation completes, it provides the retrieved
     * element, or null if there is no element with this ID.
     * @return The actual SQLRepository.
     */
    SQLRepository<T> get(Object id, Handler<AsyncResult<T>> resultHandler);

    /**
     * Delete an element from the repository.
     *
     * @param id the id of the element to delete.
     * @param resultHandler the handler which is called when this operation completes, it provides the number of
     * affected rows.
     * @return The actual SQLRepository.
     */
    SQLRepository<T> delete(Object id, Handler<AsyncResult<Integer>> resultHandler);

    /**
     * Find elements in the repository.
     * </p>
     * If the SQL query contains ?-parameters, you need to pass Suppliers for these parameters in the exact same order.
     * </p>
     * Example:
     * </p>
     * <code> SELECT * FROM Messages WHERE author = ? AND age = ? </code> <br>
     * This query needs two parameters, so it's necessary to add a list with suppliers for these two parameters, in the
     * same order.
     * 
     * @param sql The SQL string which represents the query (should begin with "SELECT").
     * @param paramSuppliers Suppliers which provides the needed values if the query contains parameters.
     * @param resultHandler the handler which is called when this operation completes, it provides a list with all
     * findings.
     * @return The actual SQLRepository.
     */
    SQLRepository<T> find(String sql, List<Supplier<Object>> paramSuppliers,
            Handler<AsyncResult<List<T>>> resultHandler);
}

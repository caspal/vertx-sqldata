package info.pascalkrause.vertx.sqldata.repository;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class SQLRepositoryTestSuite {

    private static EmbeddedPostgres epg = null;
    private static SQLClient sqlClient = null;
    private static Vertx vertx;

    private static String DB_HOST = "localhost";
    private static String DB_USER = "postgres";
    private static String DB_NAME = "postgres";
    private static int DB_PORT = 5432;

    @Before
    public void setUp() throws IOException, SQLException {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            try {
                e.printStackTrace();
                epg.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        epg = EmbeddedPostgres.builder().setPort(DB_PORT).setCleanDataDirectory(true).start();
        String url = epg.getJdbcUrl(DB_USER, DB_NAME);

        System.out.println("Postrges accessible under: " + url);
        vertx = Vertx.vertx();
        JsonObject postgreSQLClientConfig = new JsonObject().put("host", DB_HOST).put("database", DB_NAME)
                .put("username", DB_USER).put("port", DB_PORT);
        sqlClient = PostgreSQLClient.createNonShared(vertx, postgreSQLClientConfig);
    }

    @After
    public void tearDown(TestContext context) throws IOException {
        Async tearDownComplete = context.async();
        epg.close();
        sqlClient.close(v -> {
            vertx.close(v1 -> {
                tearDownComplete.complete();
            });
        });
    }
}

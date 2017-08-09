package info.pascalkrause.vertx.sqldata.repository.sqlrepository;

import info.pascalkrause.vertx.sqldata.repository.Resource;
import info.pascalkrause.vertx.sqldata.repository.SQLRepository;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.unit.TestSuite;

public abstract class AbstractSQLRepositoryTest<E> {

    private final TestSuite suite;

    public AbstractSQLRepositoryTest(String suiteName, SQLRepository<Resource> testclass, SQLClient sqlClient) {
        suite = TestSuite.create(suiteName);
        suite.before(c -> {
        });
        suite.after(c -> {
        });

        addTestCases(suite, testclass, sqlClient);
    }

    public abstract void addTestCases(TestSuite suite, SQLRepository<Resource> testclass, SQLClient sqlClient);

    public TestSuite getSuite() {
        return suite;
    }
}

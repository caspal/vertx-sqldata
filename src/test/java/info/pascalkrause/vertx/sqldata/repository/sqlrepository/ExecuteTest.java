package info.pascalkrause.vertx.sqldata.repository.sqlrepository;

import info.pascalkrause.vertx.sqldata.repository.Resource;
import info.pascalkrause.vertx.sqldata.repository.SQLRepository;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.unit.TestSuite;

public class ExecuteTest extends AbstractSQLRepositoryTest<Resource> {

    public ExecuteTest(String suiteName, SQLRepository<Resource> testclass, SQLClient sqlClient) {
        super(suiteName, testclass, sqlClient);
    }

    @Override
    public void addTestCases(TestSuite suite, SQLRepository<Resource> testclass, SQLClient sqlClient) {
        
    }
}

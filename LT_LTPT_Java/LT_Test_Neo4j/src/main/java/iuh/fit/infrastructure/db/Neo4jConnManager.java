package iuh.fit.infrastructure.db;

import org.neo4j.driver.*;

public class Neo4jConnManager implements AutoCloseable{
    private Driver driver;
    private String dbName;

    public Neo4jConnManager(String uri, String username, String password, String dbName) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
        this.dbName = dbName;
    }

    public Session getSession() {
//        return driver.session();//dbName: neo4j
        return driver.session(SessionConfig.forDatabase(dbName));//dbName: Specific database
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }
}

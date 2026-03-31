package db;

import org.neo4j.driver.*;

public class Neo4jConnection implements AutoCloseable{
    private final Driver driver;
    private final String dbName;

    public Neo4jConnection(String user,String password,String url, String dbName) {
        this.driver = GraphDatabase.driver(url, AuthTokens.basic(user,password));
        this.dbName = dbName;
    }

    public Session OpenSession(){
        return driver.session(SessionConfig.forDatabase(dbName));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }
}

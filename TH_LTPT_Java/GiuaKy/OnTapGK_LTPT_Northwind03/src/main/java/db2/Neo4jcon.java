package db2;

import org.neo4j.driver.*;

public class Neo4jcon implements AutoCloseable{
    private final Driver driver;
    private final String dbName;

    public Neo4jcon(String userName,String password,String url, String dbName) {
        this.driver = GraphDatabase.driver(url, AuthTokens.basic(userName,password));
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

package infrastructure.db;

import org.neo4j.driver.*;


public class Neo4jConnection implements AutoCloseable{

    private final Driver driver;
    private final String dbName;

    public Neo4jConnection(String url,String username,String password,String dbName) {
        this.driver = GraphDatabase.driver(url, AuthTokens.basic(username,password));
        this.dbName = dbName;
    }

    public Session openSession(){
        return driver.session(SessionConfig.forDatabase(dbName));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }
}

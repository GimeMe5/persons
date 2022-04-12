package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Artyom Kulagin
 */
public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/goods" );
        config.setUsername( "mex" );
        config.setPassword( "qazlweydernbn" );
        config.setConnectionTimeout(600000000);
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        dataSource = new HikariDataSource( config );
    }

    public DataSource() {

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

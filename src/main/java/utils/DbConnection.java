package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String url = "jdbc:mysql://localhost:3306/progetto";
    private static final String user = "root";
    private static final String password = "1234";
    private static DbConnection instance;
    private Connection conn;

    private DbConnection() {
        try {

            conn = DriverManager.getConnection(url, user, password);
        } catch ( SQLException e) {
            this.conn = null;
        }

    }

    public static synchronized DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.conn;
    }
}

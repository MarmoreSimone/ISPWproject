package utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private static DbConnection instance;
    private Connection conn;

    private DbConnection() {

        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/java/utils/config.properties")) {
            properties.load(fis);

            // Estrai i parametri di connessione dal file
            String URL = properties.getProperty("db.url");
            String USER = properties.getProperty("db.username");
            String PASSWORD = properties.getProperty("db.password");

            // Crea la connessione al database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database", e);
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

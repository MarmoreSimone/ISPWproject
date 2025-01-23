package utils;


import exception.SystemErrorException;
import model.DAOfactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private static DbConnection instance;
    private Connection conn;

    protected DbConnection() throws SystemErrorException {

        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/java/utils/config.properties")) {
            properties.load(fis);

            // Estrai i parametri di connessione dal file
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            // Crea la connessione al database
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            DAOfactory.setDAOfactory(1);
            throw new SystemErrorException("db connection error, rollback on demo version");

        }


    }

    public static synchronized DbConnection getInstance() throws SystemErrorException {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.conn;
    }
}

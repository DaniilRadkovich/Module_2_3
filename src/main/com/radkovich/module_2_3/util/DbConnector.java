package main.com.radkovich.module_2_3.util;

import main.com.radkovich.module_2_3.exception.DatabaseConnectionException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnector {
    private static final String PROPERTIES_FILE = "database.properties";
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String DRIVER_KEY = "db.driver";

    private final String url;
    private final String username;
    private final String password;

    public DbConnector() {
        Properties properties = loadProperties();
        this.url = properties.getProperty(URL_KEY);
        this.username = properties.getProperty(USERNAME_KEY);
        this.password = properties.getProperty(PASSWORD_KEY);
        
        String driver = properties.getProperty(DRIVER_KEY);
        if (driver != null) {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new DatabaseConnectionException("Database driver not found: " + driver, e);
            }
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to set up database connection!", e);
        }
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream == null) {
                throw new DatabaseConnectionException("Properties file not found: " + PROPERTIES_FILE, null);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new DatabaseConnectionException("Failed to load properties file: " + PROPERTIES_FILE, e);
        }
        return properties;
    }
}

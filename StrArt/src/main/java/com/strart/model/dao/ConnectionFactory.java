package com.strart.model.dao;

import com.strart.ApplicationStrArt;
import com.strart.model.domain.Role;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection;

    private ConnectionFactory() {}


    public static Connection getConnection(){

        if(connection == null){

            // Usa ClassLoader per ottenere l'InputStream del file nel classpath
            ClassLoader classLoader = ApplicationStrArt.class.getClassLoader();

            try (InputStream input = classLoader.getResourceAsStream("db.properties")) {
                Properties properties = new Properties();
                properties.load(input);

                String connectionUrl = properties.getProperty("CONNECTION_URL");
                String user = properties.getProperty("LOGIN_USER");
                String pass = properties.getProperty("LOGIN_PASS");

                connection = DriverManager.getConnection(connectionUrl, user, pass);
            } catch (IOException | SQLException e) {
                throw new IllegalArgumentException(e);
            }
        }

        return connection;
    }

    public static void changeRole(Role role) throws SQLException {
        if(connection != null){
            connection.close();
        }

        // Usa ClassLoader per ottenere l'InputStream del file nel classpath
        ClassLoader classLoader = ApplicationStrArt.class.getClassLoader();

        try (InputStream input = classLoader.getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty(role.name() + "_USER");
            String pass = properties.getProperty(role.name() + "_PASS");

            connection = DriverManager.getConnection(connectionUrl, user, pass);
        } catch (IOException | SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

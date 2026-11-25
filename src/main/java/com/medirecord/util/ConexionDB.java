package com.medirecord.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {

    private static final Properties PROPS = new Properties();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (InputStream input = ConexionDB.class.getClassLoader()
                    .getResourceAsStream("db.properties")) {
                if (input == null) {
                    throw new RuntimeException("No se encontró src/main/resources/db.properties");
                }
                PROPS.load(input);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver JDBC de MySQL", e);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar db.properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = PROPS.getProperty("db.url");
        String user = PROPS.getProperty("db.user");
        String password = PROPS.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }
}

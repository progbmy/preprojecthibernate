package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final static String url = "jdbc:mysql://localhost:3306/prepro";
    private final static String username = "root";
    private final static String password = "1234";
    private static Connection connection;


    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }






}

package jm.task.core.jdbc.util;

import com.mysql.cj.xdevapi.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String HOST = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER = "Emir";
    private static final String PASS = "100%Hlopok";

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(HOST, USER, PASS);
            System.out.println("Connect OK!");
        } catch (SQLException e) {
            System.out.println("Connect ERROR!");
            throw new RuntimeException(e);
        }
        return connection;
    }
}

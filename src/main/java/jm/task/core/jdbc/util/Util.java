package jm.task.core.jdbc.util;

import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String HOST = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER = "Emir";
    private static final String PASS = "100%Hlopok";
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, HOST);
                settings.put(Environment.USER, USER);
                settings.put(Environment.PASS, PASS);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    public static void close(){
        sessionFactory.close();
    }

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

package database.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorFactory {
    
    private static final String URL =  "jdbc:mysql://localhost:3306/coursedb";
    private static final String USER = "developer";
    private static final String USER_PASSWORD = "12345";

    public static Connection getConnection() throws SQLException { 
        return DriverManager.getConnection(URL,USER,USER_PASSWORD);
    }
}

package com.haven.app.haven;


import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;
public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "project";
        String databaseUser = "root";
        String databasePassword = "124519@#maisk#";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
        

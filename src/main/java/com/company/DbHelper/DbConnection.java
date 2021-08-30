package com.company.DbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11432869", "user", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

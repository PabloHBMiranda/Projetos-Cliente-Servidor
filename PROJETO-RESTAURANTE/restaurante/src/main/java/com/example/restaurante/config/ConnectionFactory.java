package com.example.restaurante.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private ConnectionFactory(){}
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/restaurante",
                    "root",
                    ""
            );
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}


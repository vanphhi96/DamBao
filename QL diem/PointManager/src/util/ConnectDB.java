/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vanph
 */
public class ConnectDB {
    public static Connection connectInstance;

    public static Connection getConnect() {
        try {
            if (connectInstance == null) {
                String dbURL = "jdbc:sqlserver://localhost;databaseName=DiemSV;user=sa;password=12";
                connectInstance = DriverManager.getConnection(dbURL);
                if (connectInstance != null) {
                    System.out.println("Connected");
                }
        
            }
        } catch (SQLException exception) {

        }
        return connectInstance;

    }

}

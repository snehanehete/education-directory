/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.educationDirectory.database;
import java.sql.*;




public class DBConnection {
    public static Connection getMySqlConn()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/education","root","");
        return con;
    }
}



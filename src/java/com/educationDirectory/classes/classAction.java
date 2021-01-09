/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.educationDirectory.classes;

import com.educationDirectory.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author SAGAR
 */
public class classAction {
    private String name, address, contact;
    private int deleted=0;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
    public int saveClasses(){
        int id = 0;
        int chk = 0;
        try{
            Connection con = DBConnection.getMySqlConn();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select Max(Id) from classes");
            while(res.next())
            {
                id = res.getInt("id");
            }
            id = id + 1;
            PreparedStatement pstmt=con.prepareStatement("insert into classes values(?,?,?,?,?)");
            pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, contact);
            pstmt.setInt(5, deleted);
            chk = pstmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return chk;
    }
    
    public int deleteClasses(int del_id){
        int chk = 0;
        try{
            Connection con = DBConnection.getMySqlConn();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select * from classes where id="+del_id);
            String name="",address="",contact="";
            int id = 0;
            while(result.next())
            {
                name = result.getString("name");
                address= result.getString("address");
                contact = result.getString("contact");
                id = result.getInt("id");
            }
            int deleted = 1;
            PreparedStatement pstmt=con.prepareStatement("update classes set deleted=? where id="+del_id);
            pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, contact);
            pstmt.setInt(5, deleted);
            chk = pstmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return chk;
    }
    
    public int updateClasses(int id, String name, String address, String contact){
        int chk = 0;
        try{
            Connection con = DBConnection.getMySqlConn();
            PreparedStatement pstmt=con.prepareStatement("update classes set name=?, address=?, contact=? where id="+id);
            pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, contact);
            chk = pstmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return chk;
    }
}

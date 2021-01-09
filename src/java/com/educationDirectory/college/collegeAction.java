/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.educationDirectory.college;

import com.educationDirectory.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author SAGAR
 */
public class collegeAction {
    private String name, address, contact, priname, courses, file;
    private int deleted = 0;

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

    public String getPriname() {
        return priname;
    }

    public void setPriname(String priname) {
        this.priname = priname;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
    public int saveCollege(){
        int id = 0;
        int chk = 0;
        try{
            Connection con = DBConnection.getMySqlConn();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select Max(Id) from college");
            while(res.next())
            {
                id = res.getInt("id");
            }
            id = id + 1;
            PreparedStatement pstmt=con.prepareStatement("insert into college values(?,?,?,?,?,?,?,?)");
            pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, contact);
            pstmt.setString(5, priname);
            pstmt.setString(6, file);
            pstmt.setString(7, courses);
            pstmt.setInt(8, deleted);
            chk = pstmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return chk;
    }
    
    public int deleteCollege(int del_id){
        int chk = 0;
        try{
            Connection con = DBConnection.getMySqlConn();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select * from college where id="+del_id);
            String name="",address="",contact="", priname="", courses="", file="";
            int id = 0;
            while(result.next())
            {
                name = result.getString("name");
                address= result.getString("address");
                contact = result.getString("contact");
                priname = result.getString("priname");
                courses = result.getString("courses");
                file = result.getString("file");
                id = result.getInt("id");
            }
            int deleted = 1;
            PreparedStatement pstmt=con.prepareStatement("update college set deleted=? where id="+del_id);
            pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, contact);
            pstmt.setString(5, priname);
            pstmt.setString(6, file);
            pstmt.setString(7, courses);
            pstmt.setInt(8, deleted);
            chk = pstmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return chk;
    }
    
    public int updateCollege(int id, String name, String address, String contact, String priname, String file, String courses){
        int chk = 0;
        try{
            Connection con = DBConnection.getMySqlConn();
            PreparedStatement pstmt=con.prepareStatement("update classes set name=?, address=?, contact=?, "
                    + "priname=?, photo=?, courses=? where id="+id);
            pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, contact);
            pstmt.setString(5, priname);
            pstmt.setString(6, file);
            pstmt.setString(7, courses);
            chk = pstmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return chk;
    }
}

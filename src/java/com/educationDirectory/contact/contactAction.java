/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.educationDirectory.contact;

import com.educationDirectory.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author SAGAR
 */
public class contactAction {
    private String name, email, text;
    private int flag=0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    public void saveContact(){
        int id = 0;
        String str = "";
        try{
            Connection con = DBConnection.getMySqlConn();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select Max(Id) from contact");
            while(res.next())
            {
                id = res.getInt("id");
            }
            id = id + 1;
            PreparedStatement pstmt=con.prepareStatement("insert into contact values(?,?,?,?,?)");
            pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, text);
            pstmt.setInt(5, flag);
            int chk = pstmt.executeUpdate();
            if(chk > 0){
                str = "success";
            }else{
                str = "fails";
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

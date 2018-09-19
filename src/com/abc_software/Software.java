/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc_software;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author SiXandSeven8ths
 */
public class Software {

    // class variables (global)
    private String strSoftwareID = "";
    private String strSoftwareName = "";
    
    Connection conn;
    Statement st;
    ResultSet rs;

    // constructor - employee id as parameter
    public Software(String s)
    {
        // load private variable
        strSoftwareID = s;

        // initialize database objects
        try
        {
            //initialize database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }

        // call method to get employee info from database
        // and load class variables
        getSoftwareInfo(s);
    }

    private void getSoftwareInfo(String software)
    {
        try
        {
            //create database connection

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://172.30.5.10:3306/sad", "csuser", "abc");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            rs = st.executeQuery("SELECT * FROM software WHERE SoftwareID = '" + software +"'");

            //loop to load class variables from result set
            strSoftwareName = "Not Found";
            while(rs.next())
            {
                strSoftwareName = rs.getString("Software Name");
            }

            //close stuff
            rs.close();
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public String getSoftwareName()
    {
        return strSoftwareName;
    }
    public void setSoftwareName(String sn)
    {
        strSoftwareName = sn;
    }
}

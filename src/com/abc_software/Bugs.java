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
public class Bugs {

    // class variables (global)
    private String TicketNumber = "";
    private int EmployeeID = 0;
    private String strSoftware = "";
    private String strCustomer = "";
    private String ticketDate;
    private String strDescription = "";
    private String strResponses = "";
    
    Connection conn;
    Statement st;
    ResultSet rs;

    // constructor - ticket number as parameter
    /**
     *
     */
    public Bugs(String b)
    {
        // load private variable
        TicketNumber = b;

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
        getBugInfo(b);
    }

    private void getBugInfo(String bugs)
    {
        try
        {
            //create database connection

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://172.30.5.10:3306/sad", "csuser", "abc");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            rs = st.executeQuery("SELECT * FROM bug_details WHERE TicketNumber = '" + bugs + "'");

            //loop to load class variables from result set
            while(rs.next())
            {
                TicketNumber = rs.getString("TicketNumber");
                strDescription = rs.getString("Description");
                strResponses = rs.getString("Responses");
                strCustomer = rs.getString("Customer");
                strSoftware = rs.getString("Software");
                EmployeeID = rs.getInt("EmployeeID");
                ticketDate = rs.getString("Date");
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
    
    public String getCustomer()
    {
        return strCustomer;
    }
    public void setCustomer(String c)
    {
        strCustomer = c;
    }
    
    public String getSoftware()
    {
        return strSoftware;
    }
    public void setSoftware(String s)
    {
        strSoftware = s;
    }
    
    public int getEmployeeID()
    {
        return EmployeeID;
    }
    public void setEmployeeID(int id)
    {
        EmployeeID = id;
    }

    public String getDescription()
    {
        return strDescription;
    }
    public void setDescription(String d)
    {
        strDescription = d;
    }
    
    public String getResponses()
    {
        return strResponses;
    }
    public void setResponses(String r)
    {
        strResponses = r;
    }
    public String getDate()
    {
        return ticketDate;
    }
    public void setDate(String dt)
    {
        ticketDate = dt;
    }



 public void updateBugInfo()
    {
        try
        {
            //create database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");
            //conn = DriverManager.getConnection("jdbc:mysql://172.30.5.10:3306/sad", "csuser", "abc");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            st.executeUpdate("UPDATE Bug_Details " +
                    "SET "
                    + "EmployeeID = " + getEmployeeID() + ", " 
                    + "Description = '" + getDescription() + "', " +
                    "Customer = '" + getCustomer() + "', " +
                    "Software = '" + getSoftware() + "' " +
                    "WHERE TicketNumber = '" + TicketNumber + "'");
            
            //close stuff
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public void insertNewBug()
    {
        try
        {
            //create database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");
            //conn = DriverManager.getConnection("jdbc:mysql://172.30.5.10:3306/sad", "csuser", "abc");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            st.executeUpdate("INSERT INTO bug_details(Customer, Software, Date, Description, "
                    + "EmployeeID) " +
                    "VALUES('"+ getCustomer() +"', '"+ getSoftware() +"', '"+ getDate() +"', '"+ getDescription() +"', " + getEmployeeID() +")");
                    //"VALUES('Cybertron Industries Inc', 'Army of Ants', '2013-03-27', 'Test', '"+ getEmployeeID() +"'");
            
            //close stuff
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            System.out.println(getSoftware());
            System.out.println(getCustomer());
            System.out.println(getDate());
            System.out.println(getDescription());
            System.out.println(getEmployeeID());
            System.out.println();
        }
    }
}
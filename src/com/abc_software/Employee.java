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
public class Employee {

    // class variables (global)
    private String strEmployeeID = "";
    private String strPassword = "";
    private String strEmpLastName = "";
    private String strEmpFirstName = "";
    private String strError = "";

    Connection conn;
    Statement st;
    ResultSet rs;

    // constructor - employee id as parameter
    public Employee(String c)
    {
        // load private variable
        strEmployeeID = c;

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
        getEmployeeInfo(c);
    }

    private void getEmployeeInfo(String employee)
    {
        try
        {
            //create database connection

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            rs = st.executeQuery("SELECT * FROM Employees WHERE EmployeeID = '" + employee +"'");

            //loop to load class variables from result set
            strEmpLastName = "Not Found";
            while(rs.next())
            {
                strEmpLastName = rs.getString("LastName");
                strEmpFirstName = rs.getString("FirstName");
                strPassword = rs.getString("PassWord");

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

    public String getEmpLastName()
    {
        return strEmpLastName;
    }
    public void setEmpLastName(String ln)
    {
        strEmpLastName = ln;
    }

    public String getEmpFirstName()
    {
        return strEmpFirstName;
    }
    public void setEmpFirstName(String fn)
    {
        strEmpFirstName = fn;
    }
    
    public String getPassWord()
    {
        return strPassword;
    }
    public void setPassWord(String pw)
    {
        strPassword = pw;
    }

 public void updateEmployeeInfo()
    {
        try
        {
            //create database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            st.executeUpdate("UPDATE Employees " +
                    "SET LastName = '" + getEmpLastName() + "', " +
                    "FirstName = '" + getEmpFirstName() + "', " +
                    "PassWord = '" + getPassWord() + "', " +
                    "WHERE EmployeeID = '" + strEmployeeID + "'");

            //close stuff
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public void insertNewEmployee()
    {
        try
        {
            //create database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            st.executeUpdate("INSERT INTO Employees(EmployeeID, LastName, "
                    + "FirstName, PassWord) " +
                    "VALUES('', '', '', ''" );

            //close stuff
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public void deleteEmployee()
    {
        try
        {
            //create database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            st.executeUpdate("DELETE FROM Employees " +
                    "WHERE EmployeeID = '" + strEmployeeID  + "'" );

            //close stuff
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
        public boolean isError()
    {
        if(strError.length() > 0)
            return true;
        else
            return false;
    }
    public String getErrorMessage()
    {
        return strError;
    }
    public void setErrorMessage(String e)
    {
        strError = e;
    }
}
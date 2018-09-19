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
public class Customer {

    // class variables (global)
    private String strCustomerID = "";
    private String strPassword = "";
    private String strCompany = "";
    private String strCustLastName = "";
    private String strCustFirstName = "";
    private String strPhone = "";
    private String strError = "";

    Connection conn;
    Statement st;
    ResultSet rs;

    // constructor - customer id as parameter
    public Customer(String c)
    {
        // load private variable
        strCustomerID = c;
        
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

        // call method to get customer info from database
        // and load class variables
        getCustomerInfo(c);
    }

    private void getCustomerInfo(String cust)
    {
        try
        {
            //create database connection

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://172.30.5.10:3306/sad", "csuser", "abc");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            rs = st.executeQuery("SELECT * FROM customers WHERE CustomerID = '" + cust +"'");

            //loop to load class variables from result set
            strCompany = "Not Found";
            while(rs.next())
            {
                strCompany = rs.getString("CompanyName"); //name in quotes is column name
                strCustLastName = rs.getString("CustLastName");
                strCustFirstName = rs.getString("CustFirstName");
                //strPassword = rs.getString("CustPassword");
                strPhone = rs.getString("PhoneNumber");
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

    public String getCompany()
    {
        return strCompany;
    }
    public void setCompany(String co)
    {
        strCompany = co;
    }
    
    public String getCustLastName()
    {
        return strCustLastName;
    }
    public void setCustLastName(String ln)
    {
        strCustLastName = ln;
    }

    public String getCustFirstName()
    {
        return strCustFirstName;
    }
    public void setCustFirstName(String fn)
    {
        strCustFirstName = fn;
    }
    
    public String getPassWord()
    {
        return strPassword;
    }
    public void setPassWord(String pw)
    {
        strPassword = pw;
    }
    
    public String getPhone()
    {
        return strPhone;
    }
    public void setPhone(String ph)
    {
        strPhone = ph;
    }

 public void updateCustomerInfo()
    {
        try
        {
            //create database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");
            //conn = DriverManager.getConnection("jdbc:mysql://172.30.5.10:3306/sad", "csuser", "abc");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            st.executeUpdate("UPDATE Customers " +
                    "SET Company = '" + getCompany() + "', " +
                    "LastName = '" + getCustLastName() + "', " +
                    "FirstName = '" + getCustFirstName() + "', " +
                    "PassWord = '" + getPassWord() + "', " +
                    "Phone = '" + getPhone() + "', " +
                    "WHERE CustomerID = '" + strCustomerID + "'");

            //close stuff
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public void insertNewCustomer()
    {
        try
        {
            //create database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");
            //conn = DriverManager.getConnection("jdbc:mysql://172.30.5.10:3306/sad", "csuser", "abc");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            st.executeUpdate("INSERT INTO Customers(CustomerID, Company, LastName, "
                    + "FirstName, PassWord, Phone) " +
                    "VALUES('', '', '', '', '', '')");

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
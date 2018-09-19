/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc_software;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author Jefffrey Herold
 */
public class BugList {
    // class variables (global)
    public DefaultListModel model;
    public DefaultComboBoxModel model2;

    // constructor -  ticket number as parameter
    public BugList()
    {
        // call method to get bug info from database
        // and load class variables
        getBugInfo();
    }

    private void getBugInfo()
    {
        try
        {
            //initialize database driver
            Class.forName("com.mysql.jdbc.Driver");

            //create database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_software", "root", "");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://172.30.5.10:3306/sad", "csuser", "abc");
            
            //create statement object
            Statement st = conn.createStatement();

            //create result set (executes SQL)
            ResultSet rs = st.executeQuery("SELECT TicketNumber FROM bug_details ORDER BY TicketNumber");

            //loop to load class variables from result set
            model = new DefaultListModel();
            model2 = new DefaultComboBoxModel();
            while(rs.next())
            {
                model.addElement(rs.getString(1));
                model2.addElement(rs.getString(1));
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
    


    public DefaultListModel getBugList()
    {
        getBugInfo();
        return model;
    }

    public DefaultComboBoxModel getBugCombo()
    {
        getBugInfo();
        return model2;
    }
    
}


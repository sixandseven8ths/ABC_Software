/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc_software;

/**
 *
 * @author SiXandSeven8ths
 */
public class DBUtils {
    //handle string that contain one or more apostrophes (')
    private static String fixDBString(String s)
    {
        //if the string is null, return it
        if (s == null)
            return s;
    
        //add an apostrophe before each existing apostrophe
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++)
        {
            char ch = sb.charAt(i);
            if (ch == 39) //39 is the ASCII code for an apostrophe
                sb.insert(i++, "'");
        }
        return sb.toString();
    } 
}

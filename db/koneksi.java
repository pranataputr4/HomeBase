/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author windows
 */
public class Konek {
    private static Connection conn = null;
    public static Connection getConnect(){
        try{
            Class.forName("org.sqlite.jdbc");
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\windows\\Documents\\HomeBase\\hb.db");
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Database tidak ditemukan");
        }
        return conn;
    }
}


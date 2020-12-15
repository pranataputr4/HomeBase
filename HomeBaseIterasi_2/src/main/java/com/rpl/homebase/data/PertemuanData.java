/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpl.homebase.data;

import com.rpl.homebase.database.JdbcUtilities;
import com.rpl.homebase.model.Pertemuan;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Doni
 */
public class PertemuanData extends JdbcUtilities{
    public PertemuanData(){
        super();
    }
    
    public ObservableList<Pertemuan> getDataPertemuanList(int id) throws SQLException{
       ObservableList<Pertemuan> pertemuanList = FXCollections.observableArrayList();
       String sql = "SELECT * FROM tb_pertemuan WHERE pertemuan_jadwal_id = ?";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql, id);
       try{
           Pertemuan pertemuan;
           int no = 1;
           while(result.next()){
               pertemuan = new Pertemuan(result.getInt("pertemuan_id"),
                       no,
                        result.getString("pertemuan_nama"),
                        result.getString("pertemuan_tanggal"),
                        result.getString("pertemuan_jadwal_id"),
                        getHadir(result.getInt("pertemuan_id")),
                       getAbsen(result.getInt("pertemuan_id"))
               );
               no++;
               pertemuanList.add(pertemuan);
               
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return pertemuanList;
    }
    
    public ObservableList<Pertemuan> getDataPertemuanList(int id, String cari) throws SQLException{
       ObservableList<Pertemuan> pertemuanList = FXCollections.observableArrayList();
       String sql = "SELECT * FROM tb_pertemuan WHERE pertemuan_jadwal_id = ? AND (pertemuan_nama LIKE '%"+cari+"%' OR pertemuan_tanggal LIKE '%"+cari+"%')";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql, id);
       try{
           Pertemuan pertemuan;
           while(result.next()){
               pertemuan = new Pertemuan(result.getInt("pertemuan_id"),
                        result.getString("pertemuan_nama"),
                        result.getString("pertemuan_tanggal"),
                        result.getString("pertemuan_jadwal_id"),
                        getHadir(result.getInt("pertemuan_id")),
                       getAbsen(result.getInt("pertemuan_id"))
               );
               pertemuanList.add(pertemuan);
               
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return pertemuanList;
    }
    
    public String getHadir(int id) throws SQLException{
       String sql = "SELECT count(presensi_status) AS hadir FROM tb_presensi WHERE presensi_status = 'Hadir' AND presensi_pertemuan_id = ?";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql, id);
       try{
           while(result.next()){
               return result.getString("hadir");
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return null;
    }
    
    public String getAbsen(int id) throws SQLException{
       String sql = "SELECT count(presensi_status) AS absen FROM tb_presensi WHERE presensi_status = 'Absen' AND presensi_pertemuan_id = ?";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql, id);
       try{
           while(result.next()){
               return result.getString("absen");
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return null;
    }
    
    public Pertemuan getPertemuan(int id) throws SQLException{
       String sql = "SELECT * FROM tb_pertemuan WHERE pertemuan_id = ?";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql, id);
       try{
           while(result.next()){
               Pertemuan pertemuan = new Pertemuan(
                       result.getInt("pertemuan_id"), 
                       result.getString("pertemuan_nama"),
                       result.getString("pertemuan_tanggal"),
                       result.getString("pertemuan_jadwal_id")
               );
               return pertemuan;
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return null;
    }
    
    public Pertemuan getLastPertemuan() throws SQLException{
       String sql = "SELECT MAX(pertemuan_id),* FROM tb_pertemuan";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql);
       try{
           while(result.next()){
               Pertemuan pertemuan = new Pertemuan(
                       result.getInt("pertemuan_id"), 
                       result.getString("pertemuan_nama"),
                       result.getString("pertemuan_tanggal"),
                       result.getString("pertemuan_jadwal_id")
               );
               return pertemuan;
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return null;
    }
    
    public void delete(int id) throws SQLException{
        String query = "DELETE FROM tb_pertemuan WHERE pertemuan_id = ?";
        this.jdbcUtilities.executeUpdate(query,id);
        
        String query2 = "DELETE FROM tb_presensi WHERE presensi_pertemuan_id = ?";
        this.jdbcUtilities.executeUpdate(query2,id);
    }
    
    public void insert(String nama, String tanggal, int jadwalID)throws Exception{
        String query = "INSERT INTO tb_pertemuan (pertemuan_nama, pertemuan_tanggal, pertemuan_jadwal_id)"
                    + "VALUES (?,?,?)";
        this.jdbcUtilities.executeInsert(query, nama, tanggal, jadwalID);
    }
    
}

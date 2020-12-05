package com.rpl.homebase.data;

import com.rpl.homebase.database.JdbcUtilities;
import com.rpl.homebase.model.Makul;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Pranata
 */
public class MakulData extends JdbcUtilities{
    public MakulData(){
        super();
    }
    
    public ArrayList<String> getMakulIDList() throws SQLException{
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * from `tb_makul`";
        ResultSet result = this.jdbcUtilities.selectFromDatabase(query);
        while(result.next()){
            list.add(result.getString("makul_id"));
        }
        return list; 
    }
    
    public ArrayList<String> getMakulList() throws SQLException{
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * from `tb_makul`";
        ResultSet result = this.jdbcUtilities.selectFromDatabase(query);
        while(result.next()){
            list.add(result.getString("makul_nama"));
        }
        return list; 
    }
    
    public ArrayList<String> getKodeMakulList() throws SQLException{
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * from `tb_makul`";
        ResultSet result = this.jdbcUtilities.selectFromDatabase(query);
        while(result.next()){
            list.add(result.getString("makul_kode"));
        }
        return list; 
    }
    
    public ArrayList<String> getDosenIDList() throws SQLException{
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * from `tb_makul`";
        ResultSet result = this.jdbcUtilities.selectFromDatabase(query);
        while(result.next()){
            list.add(result.getString("makul_user_id"));
        }
        return list; 
    }
    
    public ArrayList<String> getDosenList() throws SQLException{
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * from `tb_makul`";
        ResultSet result = this.jdbcUtilities.selectFromDatabase(query);
        while(result.next()){
            list.add(result.getString("makul_user_nama"));
        }
        return list; 
    }
    
    public Makul getMakul(int id)throws Exception { 
        String query = "SELECT * from tb_makul WHERE makul_id = ?";
        ResultSet result = this.jdbcUtilities.selectFromDatabase(query, id);
        if(result.next()){
            Makul temp = new Makul(
                    result.getInt("makul_id"), 
                    result.getString("makul_kode"),
                    result.getString("makul_nama"),
                    result.getString("makul_user_id"),
                    result.getString("makul_user_nama"));
            return temp;
        }
        return null;
    }
    
    public ObservableList<Makul> getDataMakulList() throws SQLException{
       ObservableList<Makul> makulList = FXCollections.observableArrayList();
       String sql = "SELECT * FROM tb_makul";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql);
       try{
           Makul makul;
           while(result.next()){
               makul = new Makul(result.getInt("makul_id"),
                        result.getString("makul_kode"),
                        result.getString("makul_nama"),
                        result.getString("makul_user_id"),
                        result.getString("makul_user_nama"));
               makulList.add(makul);
               
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return makulList;
    }
    
    public void insert(String kode, String userID, String userNama, String nama)throws Exception{
        String query = "INSERT INTO tb_makul (makul_kode, makul_user_id, makul_user_nama, makul_nama)"
                    + "VALUES (?,?,?,?)";
        this.jdbcUtilities.executeInsert(query, kode, userID, userNama, nama);
    }
    
    public void delete(int id) throws SQLException{
        String query = "DELETE FROM tb_makul WHERE makul_id = ?";
        this.jdbcUtilities.executeUpdate(query,id);
    }
    
    public void edit(int id, String kode, String userID, String userNama, String nama) throws SQLException{
        String query = "UPDATE tb_makul SET makul_kode = ? , makul_user_id = ?, makul_user_nama = ? , makul_nama = ?"
                + "WHERE makul_id = ?";
        this.jdbcUtilities.executeUpdate(query, kode, userID , userNama, nama, id);
    }
}

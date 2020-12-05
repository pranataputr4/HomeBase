package com.rpl.homebase.data;

import com.rpl.homebase.database.JdbcUtilities;
import com.rpl.homebase.model.Mahasiswa;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Pranata
 */
public class MahasiswaData extends JdbcUtilities{
    public MahasiswaData(){
        super();
    }
    
    public Mahasiswa getMahasiswa(int id)throws Exception { 
        String query = "SELECT * from tb_mahasiswa WHERE mahasiswa_id = ?";
        ResultSet result = this.jdbcUtilities.selectFromDatabase(query, id);
        if(result.next()){
            Mahasiswa temp = new Mahasiswa(
                    result.getInt("mahasiswa_id"), 
                    result.getString("mahasiswa_nim"),
                    result.getString("mahasiswa_nama"),
                    result.getString("mahasiswa_kelamin"),
                    result.getString("mahasiswa_jurusan"));
            return temp;
        }
        return null;
    }
    
    public ObservableList<Mahasiswa> getDataMahasiswaList() throws SQLException{
       ObservableList<Mahasiswa> mahasiswaList = FXCollections.observableArrayList();
       String sql = "SELECT * FROM tb_mahasiswa";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql);
       try{
           Mahasiswa mahasiswa;
           while(result.next()){
               mahasiswa = new Mahasiswa(result.getInt("mahasiswa_id"),
                        result.getString("mahasiswa_nim"),
                        result.getString("mahasiswa_nama"),
                        result.getString("mahasiswa_kelamin"),
                        result.getString("mahasiswa_jurusan"));
               mahasiswaList.add(mahasiswa);
               
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return mahasiswaList;
    }
    
    public void insert(String nim, String nama, String kelamin, String jurusan)throws Exception{
        String query = "INSERT INTO tb_mahasiswa (mahasiswa_nim, mahasiswa_nama, mahasiswa_kelamin, mahasiswa_jurusan)"
                    + "VALUES (?,?,?,?)";
        this.jdbcUtilities.executeInsert(query, nim, nama, kelamin, jurusan);
    }
    
    public void delete(int id) throws SQLException{
        String query = "DELETE FROM tb_mahasiswa WHERE mahasiswa_id = ?";
        this.jdbcUtilities.executeUpdate(query,id);
    }
    
    public void edit(int id, String nim, String nama, String gender, String jurusan) throws SQLException{
        String query = "UPDATE tb_mahasiswa SET mahasiswa_nim = ? , mahasiswa_nama = ?, mahasiswa_kelamin = ? , mahasiswa_jurusan = ?"
                + "WHERE mahasiswa_id = ?";
        this.jdbcUtilities.executeUpdate(query, nim, nama , gender, jurusan, id);
    }
}

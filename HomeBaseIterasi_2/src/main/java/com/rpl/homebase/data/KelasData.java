package com.rpl.homebase.data;

import com.rpl.homebase.database.JdbcUtilities;
import com.rpl.homebase.model.Kelas;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Doni
 */
public class KelasData extends JdbcUtilities{
    
    public KelasData(){
        super();
    }
    
    public ObservableList<Kelas> getDataKelasList() throws SQLException{
       ObservableList<Kelas> kelasList = FXCollections.observableArrayList();
       String sql = "SELECT * FROM tb_kelas";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql);
       try{
           Kelas kelas;
           while(result.next()){
               kelas = new Kelas(
                       result.getInt("kelas_id"), 
                       result.getString("kelas_nama"),
                       result.getString("kelas_makul_id"),
                       result.getString("kelas_makul_code"),
                       result.getString("kelas_makul_nama"),
                       result.getString("kelas_user_id"),
                       result.getString("kelas_user_nama")
               );
               kelasList.add(kelas);
               
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return kelasList;
    }
    
    public void insert(String namaKelas, String makulID, String makulNama, String userID, String userNama, String makulCode)throws Exception{
        String query = "INSERT INTO tb_kelas (kelas_nama, kelas_makul_id, kelas_makul_nama, kelas_user_id, kelas_user_nama, kelas_makul_code)"
                    + "VALUES (?,?,?,?,?,?)";
        this.jdbcUtilities.executeInsert(query, namaKelas, makulID, makulNama, userID, userNama, makulCode);
    }
    
    public void delete(int id) throws SQLException{
        String query = "DELETE FROM tb_kelas WHERE kelas_id = ?";
        this.jdbcUtilities.executeUpdate(query,id);
    }
    
    public void edit(int id, String namaKelas, String makulID, String makulNama, String userID, String userNama, String makulCode) throws SQLException{
        String query = "UPDATE tb_kelas SET kelas_nama = ? , kelas_makul_id = ?, kelas_makul_nama = ? , kelas_user_id = ?, kelas_user_nama = ? , kelas_makul_code = ?"
                + "WHERE kelas_id = ?";
        this.jdbcUtilities.executeUpdate(query, namaKelas, makulID , makulNama, userID, userNama, makulCode);
    }
}

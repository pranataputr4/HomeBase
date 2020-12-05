package com.rpl.homebase.data;

import com.rpl.homebase.database.JdbcUtilities;
import com.rpl.homebase.model.Jadwal;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Doni
 */
public class JadwalData extends JdbcUtilities{
    public JadwalData(){
        super();
    }
    
    public ObservableList<Jadwal> getDataJadwalList() throws SQLException{
       ObservableList<Jadwal> jadwalList = FXCollections.observableArrayList();
       String sql = "SELECT * FROM tb_jadwal";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql);
       try{
           Jadwal jadwal;
           while(result.next()){
               jadwal = new Jadwal(
                       result.getInt("jadwal_id"), 
                       result.getString("jadwal_kelas_id"),
                       result.getString("jadwal_hari"),
                       result.getString("jadwal_jam_mulai"),
                       result.getString("jadwal_jam_berakhir"),
                       result.getString("jadwal_kelas_nama"),
                       result.getString("jadwal_makul_id"),
                       result.getString("jadwal_makul_nama"),
                       result.getString("jadwal_user_id"),
                       result.getString("jadwal_user_nama"),
                       result.getString("jadwal_makul_code")
               );
               jadwalList.add(jadwal);
               
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return jadwalList;
    }
}

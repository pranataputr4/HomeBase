package com.rpl.homebase.data;

import com.rpl.homebase.database.JdbcUtilities;
import com.rpl.homebase.model.KelasKuota;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Doni
 */
public class KelasKuotaData extends JdbcUtilities{
    KelasKuotaData(){
        super();
    }
    
    public ObservableList<KelasKuota> getDataKelasKuotaList() throws SQLException{
       ObservableList<KelasKuota> kelasKuotaList = FXCollections.observableArrayList();
       String sql = "SELECT * FROM tb_kelas_kuota";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql);
       try{
           KelasKuota kelas;
           while(result.next()){
               kelas = new KelasKuota(
                       result.getInt("kelas_kuota_id"), 
                       result.getString("kelas_kuota_mahasiswa_id"),
                       result.getString("kelas_kuota_mahasiswa_nama"),
                       result.getString("kelas_kuota_kelas_id"),
                       result.getString("kelas_kuota_kelas_nama"),
                       result.getString("kelas_kuota_user_id"),
                       result.getString("kelas_kuota_user_nama"),
                       result.getString("kelas_kuota_makul_id"),
                       result.getString("kelas_kuota_makul_code"),
                       result.getString("kelas_kuota_makul_nama")
               );
               kelasKuotaList.add(kelas);
               
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return kelasKuotaList;
    }
    
    public void insert(String mhsID, String mhsNama, String kelasID, String kelasNama, String userID, String userNama, String makulID, String makulCode, String makulNama)throws Exception{
        String query = "INSERT INTO tb_kelas_kuota ("
                + "kelas_kuota_mahasiswa_id, "
                + "kelas_kuota_mahasiswa_nama, "
                + "kelas_kuota_kelas_id, "
                + "kelas_kuota_kelas_nama, "
                + "kelas_kuota_user_id, "
                + "kelas_kuota_user_nama, "
                + "kelas_kuota_makul_id,"
                + "kelas_kuota_makul_code,"
                + "kulas_kuota_makul_nama)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
        this.jdbcUtilities.executeInsert(query, mhsID,mhsNama,kelasID,kelasNama,userID,userNama,makulID, makulCode,makulNama);
    }
    
    public void delete(int id) throws SQLException{
        String query = "DELETE FROM tb_kelas_kuota WHERE kelas_kuota_id = ?";
        this.jdbcUtilities.executeUpdate(query,id);
    }
}

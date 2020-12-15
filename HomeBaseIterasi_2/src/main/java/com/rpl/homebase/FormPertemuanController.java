/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpl.homebase;

import com.rpl.homebase.data.PertemuanData;
import com.rpl.homebase.data.PresensiData;
import com.rpl.homebase.model.Jadwal;
import com.rpl.homebase.model.Pertemuan;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Doni
 */
public class FormPertemuanController implements Initializable {

    private Jadwal jadwalSession;
    private Pertemuan pertemuanSession;
    
    @FXML
    private AnchorPane formPertemuan;
    @FXML
    private TextField txtPertemuan;
    @FXML
    private DatePicker tglPresensi;
    @FXML
    private Label lblNamaKelas;
    @FXML
    private Label lblMakul;
    @FXML
    private Label lblKode;
    @FXML
    private Label lblDosen;
    @FXML
    private TableView<Pertemuan> tvPertemuan;
    @FXML
    private TableColumn<Pertemuan, String> colPertemuan;
    @FXML
    private TableColumn<Pertemuan, String> colTanggal;
    @FXML
    private TableColumn<Pertemuan, String> colNim;
    @FXML
    private TextField txtSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        JadwalSession jadwal = JadwalSession.getInstace(this.jadwalSession);
        this.jadwalSession = jadwal.getJadwal();
        
        PertemuanSession pertemuan = PertemuanSession.getInstace(this.pertemuanSession);
        pertemuan.cleanPertemuanSession();
        
        lblNamaKelas.setText(this.jadwalSession.getKelasNama());
        lblMakul.setText(this.jadwalSession.getMakulNama());
        lblKode.setText(this.jadwalSession.getMakulCode());
        lblDosen.setText(this.jadwalSession.getUserNama());
        
        try {
            showPertemuan();
        } catch (SQLException ex) {
            Logger.getLogger(FormPertemuanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void showPertemuan() throws SQLException{
        PertemuanData pertemuanData = new PertemuanData();
        ObservableList<Pertemuan> list = pertemuanData.getDataPertemuanList(this.jadwalSession.getId());
        
        colPertemuan.setCellValueFactory(new PropertyValueFactory<Pertemuan, String>("nama"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<Pertemuan, String>("tanggal"));
        
        tvPertemuan.setItems(list);
    }
    
    public void showPertemuan(String cari) throws SQLException{
        PertemuanData pertemuanData = new PertemuanData();
        ObservableList<Pertemuan> list = pertemuanData.getDataPertemuanList(this.jadwalSession.getId(),cari);
        
        colPertemuan.setCellValueFactory(new PropertyValueFactory<Pertemuan, String>("nama"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<Pertemuan, String>("tanggal"));
        
        tvPertemuan.setItems(list);
    }
    
    public void insert() throws Exception{
        if(txtPertemuan.getText().equals("") || tglPresensi.getValue()==null){
            MessageDialog.infoBox("Field is empty", "Insert Failed", "Failed");
        }else{
            PertemuanData pertemuan = new PertemuanData();
            pertemuan.insert(txtPertemuan.getText(), tglPresensi.getValue().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")), this.jadwalSession.getId());
            
            PresensiData presensi = new PresensiData();
            presensi.insertMahasiswa(Integer.toString(this.jadwalSession.getId()), this.jadwalSession.getMakulID(), this.jadwalSession.getUserID(), this.jadwalSession.getKelasID(), this.jadwalSession.getKelasNama(), this.jadwalSession.getUserNama(), this.jadwalSession.getMakulNama(), this.jadwalSession.getMakulCode(), this.jadwalSession.getJamMulai(), this.jadwalSession.getJamBerakhir());
            
            txtPertemuan.setText("");
            tglPresensi.setValue(null);
            showPertemuan();
        }
        
    }
    
    public void delete() throws SQLException{
        PertemuanData pertemuan = new PertemuanData();
        pertemuan.delete(tvPertemuan.getSelectionModel().getSelectedItem().getId());
        showPertemuan();
    }

    @FXML
    private void btnSimpan(ActionEvent event) throws Exception {
        insert();
    }

    @FXML
    private void btnBack(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formPertemuan,"dashAdminPresensi");
    }

    @FXML
    private void btnPresensi(ActionEvent event) throws IOException, SQLException {
        PertemuanData pertemuanData = new PertemuanData();
        Pertemuan temp = pertemuanData.getPertemuan(tvPertemuan.getSelectionModel().getSelectedItem().getId());
        
        this.pertemuanSession = temp;
        PertemuanSession.getInstace(this.pertemuanSession);
        
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formPertemuan,"formPresensi");
    }

    @FXML
    private void btnSearch(ActionEvent event) throws SQLException {
        showPertemuan(txtSearch.getText());
    }

    @FXML
    private void btnHapus(ActionEvent event) throws SQLException {
        delete();
    }
    
}

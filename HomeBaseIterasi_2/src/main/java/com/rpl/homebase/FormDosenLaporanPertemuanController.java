/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpl.homebase;

import com.rpl.homebase.data.PertemuanData;
import com.rpl.homebase.data.PresensiData;
import com.rpl.homebase.model.Jadwal;
import com.rpl.homebase.model.LaporanKehadiran;
import com.rpl.homebase.model.Pertemuan;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ANGKY
 */
public class FormDosenLaporanPertemuanController implements Initializable {

    private Jadwal jadwalSession;
    
    @FXML
    private AnchorPane formDosenLaporanPertemuan;
    @FXML
    private TableView<Pertemuan> tvPertemuan;
    @FXML
    private TableColumn<?, ?> colNim;
    @FXML
    private TableColumn<Pertemuan, String> colPertemuan;
    @FXML
    private TableColumn<Pertemuan, String> colTanggal;
    private TextField txtSearch;
    @FXML
    private Label lblNamaKelas;
    @FXML
    private Label lblMakul;
    @FXML
    private Label lblKode;
    @FXML
    private Label lblDosen;
    @FXML
    private TableColumn<Pertemuan, String> colKehadiran;
    @FXML
    private TableColumn<Pertemuan, String> colAbsen;
    @FXML
    private TableView<LaporanKehadiran> tvStatus;
    @FXML
    private TableColumn<LaporanKehadiran, String> colNim2;
    @FXML
    private TableColumn<LaporanKehadiran, String> colNama;
    @FXML
    private TableColumn<LaporanKehadiran, String> colPersentase;
    @FXML
    private TableColumn<LaporanKehadiran, String> colStatus;
    @FXML
    private TableColumn<Pertemuan, Integer> colNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JadwalSession jadwal = JadwalSession.getInstace(this.jadwalSession);
        this.jadwalSession = jadwal.getJadwal();
        
        lblNamaKelas.setText(this.jadwalSession.getKelasNama());
        lblMakul.setText(this.jadwalSession.getMakulNama());
        lblKode.setText(this.jadwalSession.getMakulCode());
        lblDosen.setText(this.jadwalSession.getUserNama());
        
        try {
            // TODO
            
            showKehadiran();
            showPertemuan();
        } catch (SQLException ex) {
            Logger.getLogger(FormDosenLaporanPertemuanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void showKehadiran() throws SQLException{
        PresensiData pertemuanData = new PresensiData();
        ObservableList<LaporanKehadiran> list = pertemuanData.getDataKehadiranList(this.jadwalSession.getId());
        
        colNim2.setCellValueFactory(new PropertyValueFactory<LaporanKehadiran, String>("nim"));
        colNama.setCellValueFactory(new PropertyValueFactory<LaporanKehadiran, String>("nama"));
        colPersentase.setCellValueFactory(new PropertyValueFactory<LaporanKehadiran, String>("persentase"));
        colStatus.setCellValueFactory(new PropertyValueFactory<LaporanKehadiran, String>("status"));
        
        tvStatus.setItems(list);
    }
    
    public void showPertemuan() throws SQLException{
        PertemuanData pertemuanData = new PertemuanData();
        ObservableList<Pertemuan> list = pertemuanData.getDataPertemuanList(this.jadwalSession.getId());
        
        colNo.setCellValueFactory(new PropertyValueFactory<Pertemuan, Integer>("no"));
        colPertemuan.setCellValueFactory(new PropertyValueFactory<Pertemuan, String>("nama"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<Pertemuan, String>("tanggal"));
        colKehadiran.setCellValueFactory(new PropertyValueFactory<Pertemuan, String>("jumlahHadir"));
        colAbsen.setCellValueFactory(new PropertyValueFactory<Pertemuan, String>("jumlahAbsen"));
        
        tvPertemuan.setItems(list);
    }
    
    
    @FXML
    private void btnBack(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formDosenLaporanPertemuan,"dashDosenLaporan");
    }

    
    
}

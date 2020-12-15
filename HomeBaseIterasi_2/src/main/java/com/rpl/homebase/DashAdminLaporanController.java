/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpl.homebase;

import com.rpl.homebase.data.JadwalData;
import com.rpl.homebase.model.Jadwal;
import com.rpl.homebase.model.User;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Doni
 */
public class DashAdminLaporanController implements Initializable {

    private User sessionUser;
    private Jadwal jadwalSession;
    
    @FXML
    private AnchorPane dashAdminLaporan;
    @FXML
    private TableView<Jadwal> tvKelas;
    @FXML
    private TableColumn<Jadwal, String> colID;
    @FXML
    private TableColumn<Jadwal, String> colNama;
    @FXML
    private TableColumn<Jadwal, String> colKode;
    @FXML
    private TableColumn<Jadwal, String> colMakul;
    @FXML
    private TableColumn<Jadwal, String> colDosen;
    @FXML
    private Label lblAdmin;
    @FXML
    private TextField txtSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserSession session = UserSession.getInstace(this.sessionUser);
        this.sessionUser = session.getUser();
        
        JadwalSession jadwal = JadwalSession.getInstace(this.jadwalSession);
        jadwal.cleanJadwalSession();
        
        lblAdmin.setText(sessionUser.getName());
        
        try {
            showJadwal();
        } catch (SQLException ex) {
            Logger.getLogger(DashAdminLaporanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void showJadwal() throws SQLException{
        JadwalData jadwalData = new JadwalData();
        ObservableList<Jadwal> list = jadwalData.getDataJadwalList();
        
        colNama.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("kelasNama"));
        colMakul.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("makulNama"));
        colKode.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("makulCode"));
        colDosen.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("userNama"));
        
        tvKelas.setItems(list);
    }
    
    public void showJadwal(String cari) throws SQLException{
        JadwalData jadwalData = new JadwalData();
        ObservableList<Jadwal> list = jadwalData.getDataJadwalList(cari);
        
        colNama.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("kelasNama"));
        colMakul.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("makulNama"));
        colKode.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("makulCode"));
        colDosen.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("userNama"));
        
        tvKelas.setItems(list);
    }

    @FXML
    private void click(MouseEvent event) {
    }

    @FXML
    private void btnLogout(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminLaporan,"login");
        UserSession session = UserSession.getInstace(this.sessionUser);
        session.cleanUserSession();
    }

    @FXML
    private void btnLihat(ActionEvent event) throws IOException, SQLException {
        JadwalData jadwal = new JadwalData();
        Jadwal temp = jadwal.getJadwal(tvKelas.getSelectionModel().getSelectedItem().getId());
        
        this.jadwalSession = temp;
        JadwalSession.getInstace(this.jadwalSession);
        
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminLaporan,"formLaporanPertemuan");
    }

    @FXML
    private void btnUser(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminLaporan,"dashAdminDosen");
    }

    @FXML
    private void btnMahasiswa(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminLaporan,"dashAdmin");
    }

    @FXML
    private void btnMakul(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminLaporan,"dashAdminMakul");
    }

    @FXML
    private void btnKelas(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminLaporan,"dashAdminKelas");
    }

    @FXML
    private void btnJadwal(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminLaporan,"dashAdminJadwal");
    }

    @FXML
    private void btnPresensi(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminLaporan,"dashAdminPresensi");
    }

    @FXML
    private void btnLaporan(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void btnSearch(ActionEvent event) throws SQLException {
        showJadwal(txtSearch.getText());
    }
    
}

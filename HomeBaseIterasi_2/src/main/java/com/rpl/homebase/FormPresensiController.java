package com.rpl.homebase;

import com.rpl.homebase.data.PresensiData;
import com.rpl.homebase.model.Presensi;
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
public class FormPresensiController implements Initializable {

    @FXML
    private AnchorPane formPresensi;
    @FXML
    private Label lblNamaKelas;
    @FXML
    private Label lblMakul;
    @FXML
    private Label lblKode;
    @FXML
    private Label lblDosen;
    @FXML
    private TableColumn<Presensi, String> colNim;
    @FXML
    private TableColumn<Presensi, String> colNama;
    @FXML
    private TableColumn<Presensi, String> colStatus;
    @FXML
    private DatePicker tglPresensi;
    @FXML
    private TableView<Presensi> tvMahasiswa;
    @FXML
    private TextField txtPertemuan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            showPresensi();
        } catch (Exception ex) {
            Logger.getLogger(FormPresensiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public void showPresensi() throws Exception{
        PresensiData presensiData = new PresensiData();
        ObservableList<Presensi> list = presensiData.getDataPresensiList();
        
        colNama.setCellValueFactory(new PropertyValueFactory<Presensi, String>("mahasiswaNama"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Presensi, String>("status"));
        
        tvMahasiswa.setItems(list);
    } 

    @FXML
    private void btnBack(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formPresensi,"dashAdminPresensi");
    }

    @FXML
    private void btnHadir(ActionEvent event) throws SQLException, Exception {
        PresensiData presensi = new PresensiData();
        presensi.setHadir(tvMahasiswa.getSelectionModel().getSelectedItem().getId(), "Hadir");
        showPresensi();
    }

    @FXML
    private void btnAbsen(ActionEvent event) throws SQLException, Exception {
        PresensiData presensi = new PresensiData();
        presensi.setAbsen(tvMahasiswa.getSelectionModel().getSelectedItem().getId(), "Absen");
        showPresensi();
    }

    @FXML
    private void btnSimpan(ActionEvent event) {
    } 
}
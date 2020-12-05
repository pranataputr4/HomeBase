package com.rpl.homebase;

import com.rpl.homebase.data.PresensiData;
import com.rpl.homebase.model.Presensi;
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
 * @author SuryaNKT
 */
public class FormDosenPresensiController implements Initializable {

    private User sessionUser;
    
    @FXML
    private AnchorPane formDosenPresensi;
    @FXML
    private Label lblNamaKelas;
    @FXML
    private Label lblMakul;
    @FXML
    private Label lblKode;
    @FXML
    private Label lblDosen;
    @FXML
    private DatePicker tglPresensi;
    @FXML
    private TableView<Presensi> tvMahasiswa;
    @FXML
    private TableColumn<Presensi, String> colNim;
    @FXML
    private TableColumn<Presensi, String> colNama;
    @FXML
    private TableColumn<Presensi, String> colStatus;
    @FXML
    private TextField txtPertemuan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UserSession session = UserSession.getInstace(this.sessionUser);
        this.sessionUser = session.getUser();
        
        try {
            showPresensi();
        } catch (Exception ex) {
            Logger.getLogger(FormDosenPresensiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void showPresensi() throws Exception{
        PresensiData presensiData = new PresensiData();
        ObservableList<Presensi> list = presensiData.getDataPresensiList(this.sessionUser.getUserID());
        
        colNama.setCellValueFactory(new PropertyValueFactory<Presensi, String>("mahasiswaNama"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Presensi, String>("status"));
        
        tvMahasiswa.setItems(list);
    } 

    @FXML
    private void btnBack(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formDosenPresensi,"dashDosenPresensi");
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
        presensi.setHadir(tvMahasiswa.getSelectionModel().getSelectedItem().getId(), "Absen");
        showPresensi();
    }

    @FXML
    private void btnSimpan(ActionEvent event) {
    }
}
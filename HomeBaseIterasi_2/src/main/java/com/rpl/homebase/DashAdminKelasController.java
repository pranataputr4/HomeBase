package com.rpl.homebase;

import com.rpl.homebase.data.KelasData;
import com.rpl.homebase.model.Kelas;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author SuryaNKT
 */
public class DashAdminKelasController implements Initializable {

    private User sessionUser;
    private User userEdit;
    
    @FXML
    private AnchorPane dashAdminKelas;
    @FXML
    private TableColumn<Kelas, Integer> colID;
    @FXML
    private TableColumn<Kelas, String> colNama;
    @FXML
    private TableColumn<Kelas, String> colMakul;
    @FXML
    private TableColumn<Kelas, String> colDosen;
    @FXML
    private Label lblAdmin;
    @FXML
    private TableView<Kelas> tvKelas;
    @FXML
    private TableColumn<Kelas, String> colKode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserSession session = UserSession.getInstace(this.sessionUser);
        this.sessionUser = session.getUser();
        
        lblAdmin.setText(sessionUser.getName());
        
        try {
            showKelas();
        } catch (Exception ex) {
            Logger.getLogger(DashAdminKelasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public void showKelas() throws Exception{
        KelasData kelasData = new KelasData();
        ObservableList<Kelas> list = kelasData.getDataKelasList();
        
        colNama.setCellValueFactory(new PropertyValueFactory<Kelas, String>("kelasNama"));
        colMakul.setCellValueFactory(new PropertyValueFactory<Kelas, String>("makulNama"));
        colKode.setCellValueFactory(new PropertyValueFactory<Kelas, String>("makulKode"));
        colDosen.setCellValueFactory(new PropertyValueFactory<Kelas, String>("dosenNama"));
        
        tvKelas.setItems(list);
    } 
    
    @FXML
    private void click(MouseEvent event) {
    }

    @FXML
    private void btnLogout(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminKelas,"login");
        UserSession session = UserSession.getInstace(this.sessionUser);
        session.cleanUserSession();
    }

    @FXML
    private void btnTambah(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminKelas,"formTambahEditKelas");
    }

    @FXML
    private void btnUbah(ActionEvent event) {
        
    }

    @FXML
    private void btnHapus(ActionEvent event) throws SQLException, Exception {
        KelasData kelasData = new KelasData();
        kelasData.delete(tvKelas.getSelectionModel().getSelectedItem().getId());
        showKelas();
    }

    @FXML
    private void btnUser(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminKelas,"dashAdminDosen");
    }

    @FXML
    private void btnMahasiswa(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminKelas,"dashAdmin");
    }

    @FXML
    private void btnMakul(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminKelas,"dashAdminMakul");
    }

    @FXML
    private void btnKelas(ActionEvent event) {
    }

    @FXML
    private void btnJadwal(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminKelas,"dashAdminJadwal");
    }

    @FXML
    private void btnPresensi(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminKelas,"dashAdminPresensi");
    }

    @FXML
    private void btnData(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminKelas,"formKelasKuota");
    }
}
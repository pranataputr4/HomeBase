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
import javafx.scene.control.Button;
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
public class DashAdminJadwalController implements Initializable {

    private User sessionUser;
    private User userEdit;
    
    @FXML
    private AnchorPane dashAdminJadwal;
    @FXML
    private TableColumn<Jadwal, Integer> colID;
    @FXML
    private Label lblAdmin;
    @FXML
    private TableView<Jadwal> tvJadwal;
    @FXML
    private TableColumn<Jadwal, String> colKelas;
    @FXML
    private Button btnDetail;
    @FXML
    private TableColumn<Jadwal, String> colKode;
    @FXML
    private TableColumn<Jadwal, String> colMakul;
    @FXML
    private TableColumn<Jadwal, String> colHari;

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
            showJadwal();
        } catch (SQLException ex) {
            Logger.getLogger(DashAdminJadwalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void showJadwal() throws SQLException{
        JadwalData jadwalData = new JadwalData();
        ObservableList<Jadwal> list = jadwalData.getDataJadwalList();
        
        colKelas.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("kelasNama"));
        colMakul.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("makulNama"));
        colKode.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("makulCode"));
        colHari.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("hari"));
        
        tvJadwal.setItems(list);
    }

    @FXML
    private void click(MouseEvent event) {
    }

    @FXML
    private void btnLogout(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminJadwal,"login");
        UserSession session = UserSession.getInstace(this.sessionUser);
        session.cleanUserSession();
    }

    @FXML
    private void btnTambah(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminJadwal,"formTambahEditJadwal");
    }

    @FXML
    private void btnUbah(ActionEvent event) {
    }

    @FXML
    private void btnHapus(ActionEvent event) {
    }

    @FXML
    private void btnUser(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminJadwal,"dashAdminDosen");
    }

    @FXML
    private void btnMahasiswa(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminJadwal,"dashAdmin");
    }

    @FXML
    private void btnMakul(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminJadwal,"dashAdminMakul");
    }

    @FXML
    private void btnKelas(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminJadwal,"dashAdminKelas");
    }

    @FXML
    private void btnJadwal(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void btnPresensi(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminJadwal,"dashAdminPresensi");
    }
}
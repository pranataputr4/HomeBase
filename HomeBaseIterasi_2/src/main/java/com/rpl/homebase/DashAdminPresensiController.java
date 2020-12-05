package com.rpl.homebase;

import com.rpl.homebase.data.JadwalData;
import com.rpl.homebase.data.PresensiData;
import com.rpl.homebase.model.Jadwal;
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
public class DashAdminPresensiController implements Initializable {

    
    private User sessionUser;
    private User userEdit;
    
    @FXML
    private AnchorPane dashAdminPresensi;
    @FXML
    private TableColumn<Jadwal, Integer> colID;
    @FXML
    private TableColumn<Jadwal, String> colKelas;
    @FXML
    private Label lblAdmin;
    @FXML
    private TableColumn<Jadwal, String> colMakul;
    @FXML
    private TableColumn<Jadwal, String> colDosen;
    @FXML
    private TableColumn<Jadwal, String> colTanggal;
    @FXML
    private TableView<Jadwal> tvPresensi;

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
            Logger.getLogger(DashAdminPresensiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void showJadwal() throws SQLException{
        JadwalData jadwalData = new JadwalData();
        ObservableList<Jadwal> list = jadwalData.getDataJadwalList();
        
        colKelas.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("kelasNama"));
        colMakul.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("makulNama"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("makulCode"));
        colDosen.setCellValueFactory(new PropertyValueFactory<Jadwal, String>("hari"));
        
        tvPresensi.setItems(list);
    }

    @FXML
    private void click(MouseEvent event) {
    }

    @FXML
    private void btnLogout(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminPresensi,"login");
        UserSession session = UserSession.getInstace(this.sessionUser);
        session.cleanUserSession();
    }


    @FXML
    private void btnUser(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminPresensi,"dashAdmin");
    }

    @FXML
    private void btnMahasiswa(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminPresensi,"dashAdminMahasiswa");
    }

    @FXML
    private void btnMakul(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminPresensi,"dashAdminMakul");
    }

    @FXML
    private void btnKelas(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminPresensi,"dashAdminKelas");
    }

    @FXML
    private void btnJadwal(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminPresensi,"dashAdminJadwal");
    }

    @FXML
    private void btnPresensi(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void btnPilihKelas(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminPresensi,"formPresensi");
    }
}
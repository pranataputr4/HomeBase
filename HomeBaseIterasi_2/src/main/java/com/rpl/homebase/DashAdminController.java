package com.rpl.homebase;

import com.rpl.homebase.data.MahasiswaData;
import com.rpl.homebase.model.Mahasiswa;
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
public class DashAdminController implements Initializable {

    private User sessionUser;
    private Mahasiswa mahasiswaEdit;
    
    @FXML
    private AnchorPane dashAdmin;
    @FXML
    private TableView<Mahasiswa> tvMahasiswa;
    @FXML
    private TableColumn<Mahasiswa, Integer> colID;
    @FXML
    private TableColumn<Mahasiswa, String> colNim;
    @FXML
    private TableColumn<Mahasiswa, String> colNama;
    @FXML
    private TableColumn<Mahasiswa, String> colJurusan;
    @FXML
    private TableColumn<Mahasiswa, String> colKelamin;
    @FXML
    private Label lblAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserSession session = UserSession.getInstace(this.sessionUser);
        this.sessionUser = session.getUser();
        
        MahasiswaEdit edit = MahasiswaEdit.getInstace(this.mahasiswaEdit);
        edit.cleanMahasiswaSession();
        
        lblAdmin.setText(sessionUser.getName());
        try {
            showMahasiswa();
        } catch (Exception ex) {
            Logger.getLogger(DashAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void showMahasiswa() throws Exception{
        MahasiswaData mahasiswaData = new MahasiswaData();
        ObservableList<Mahasiswa> list = mahasiswaData.getDataMahasiswaList();
        
        colID.setCellValueFactory(new PropertyValueFactory<Mahasiswa, Integer>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("nama"));
        colNim.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("nim"));
        colKelamin.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("kelamin"));
        colJurusan.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("jurusan"));
        
        tvMahasiswa.setItems(list);
    }  

    @FXML
    private void click(MouseEvent event) {
    }

    @FXML
    private void btnLogout(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdmin,"login");
        UserSession session = UserSession.getInstace(this.sessionUser);
        session.cleanUserSession();
    }

    @FXML
    private void btnTambah(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdmin,"formTambahEditMahasiswa");
    }

    @FXML
    private void btnUbah(ActionEvent event) throws Exception {
        MahasiswaData mahasiswa = new MahasiswaData();
        Mahasiswa temp = mahasiswa.getMahasiswa(tvMahasiswa.getSelectionModel().getSelectedItem().getId());
        
        this.mahasiswaEdit = temp;
        MahasiswaEdit.getInstace(this.mahasiswaEdit);
        
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdmin,"formTambahEditMahasiswa");
    }

    @FXML
    private void btnHapus(ActionEvent event) throws SQLException, Exception {
        MahasiswaData mahasiswa = new MahasiswaData();
        mahasiswa.delete(tvMahasiswa.getSelectionModel().getSelectedItem().getId());
        showMahasiswa();
    }

    @FXML
    private void btnUser(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdmin,"dashAdminDosen");
    }

    @FXML
    private void btnMakul(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdmin,"dashAdminMakul");
    }

    @FXML
    private void btnJadwal(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdmin,"dashAdminJadwal");
    }

    @FXML
    private void btnPresensi(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdmin,"dashAdminPresensi");
    }

    @FXML
    private void btnKelas(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdmin,"dashAdminKelas");
    }
}
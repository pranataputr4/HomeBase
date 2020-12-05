package com.rpl.homebase;

import com.rpl.homebase.data.MakulData;
import com.rpl.homebase.model.Makul;
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
public class DashAdminMakulController implements Initializable {

    private User sessionUser;
    private Makul makulEdit;
    
    @FXML
    private AnchorPane dashAdminMakul;
    @FXML
    private TableColumn<Makul, Integer> colID;
    @FXML
    private TableColumn<Makul, String> colKode;
    @FXML
    private TableColumn<Makul, String> colMakul;
    @FXML
    private TableColumn<Makul, String> colDosen;
    @FXML
    private Label lblAdmin;
    @FXML
    private TableView<Makul> tvMakul;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserSession session = UserSession.getInstace(this.sessionUser);
        this.sessionUser = session.getUser();
        
        MakulEdit edit = MakulEdit.getInstace(this.makulEdit);
        edit.cleanMakulSession();
        
        lblAdmin.setText(sessionUser.getName());
        
        try {
            showMakul();
        } catch (SQLException ex) {
            Logger.getLogger(DashAdminMakulController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void showMakul() throws SQLException{
        MakulData makulData = new MakulData();
        ObservableList<Makul> list = makulData.getDataMakulList();
        
        colID.setCellValueFactory(new PropertyValueFactory<Makul, Integer>("id"));
        colKode.setCellValueFactory(new PropertyValueFactory<Makul, String>("kode"));
        colMakul.setCellValueFactory(new PropertyValueFactory<Makul, String>("nama"));
        colDosen.setCellValueFactory(new PropertyValueFactory<Makul, String>("dosen"));
        
        tvMakul.setItems(list);
    }

    @FXML
    private void click(MouseEvent event) {
    }

    @FXML
    private void btnLogout(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminMakul,"login");
        UserSession session = UserSession.getInstace(this.sessionUser);
        session.cleanUserSession();
    }

    @FXML
    private void btnTambah(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminMakul,"formTambahEditMakul");
    }

    @FXML
    private void btnUbah(ActionEvent event) throws Exception {
        MakulData makul = new MakulData();
        Makul temp = makul.getMakul(tvMakul.getSelectionModel().getSelectedItem().getId());
        
        this.makulEdit = temp;
        MakulEdit.getInstace(this.makulEdit);
        
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminMakul,"formTambahEditMakul");
    }

    @FXML
    private void btnHapus(ActionEvent event) throws SQLException {
        MakulData makulData = new MakulData();
        makulData.delete(tvMakul.getSelectionModel().getSelectedItem().getId());
        showMakul();
    }

    @FXML
    private void btnUser(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminMakul,"dashAdminDosen");
    }

    @FXML
    private void btnMahasiswa(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminMakul,"dashAdmin");
    }

    @FXML
    private void btnMakul(ActionEvent event) {
    }

    @FXML
    private void btnJadwal(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminMakul,"dashAdminJadwal");
    }

    @FXML
    private void btnPresensi(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminMakul,"dashAdminPresensi");
    }

    @FXML
    private void btnKelas(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminMakul,"dashAdminKelas");
    } 
}
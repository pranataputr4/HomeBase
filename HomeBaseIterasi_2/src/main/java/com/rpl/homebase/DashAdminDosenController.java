package com.rpl.homebase;

import com.rpl.homebase.data.UserData;
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
public class DashAdminDosenController implements Initializable {

    private User sessionUser;
    private User userEdit;
    
    @FXML
    private AnchorPane dashAdminDosen;
    @FXML
    private TableColumn<User, String> colNama;
    @FXML
    private TableColumn<User, String> colGender;
    @FXML
    private TableColumn<User, String> colBidang;
    @FXML
    private Label lblAdmin;
    @FXML
    private TableView<User> tvDosen;
    @FXML
    private TableColumn<?, ?> colID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserSession session = UserSession.getInstace(this.sessionUser);
        this.sessionUser = session.getUser();
        
        UserEdit edit = UserEdit.getInstace(this.userEdit);
        edit.cleanUserSession();
        
        lblAdmin.setText(sessionUser.getName());
        
        try {
            showUser();
        } catch (Exception ex) {
            Logger.getLogger(DashAdminDosenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void showUser() throws Exception{
        UserData userData = new UserData();
        ObservableList<User> list = userData.getDataUserList();
        
        colNama.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        colBidang.setCellValueFactory(new PropertyValueFactory<User, String>("bidang"));
        
        tvDosen.setItems(list);
    }  

    @FXML
    private void click(MouseEvent event) {
    }

    @FXML
    private void btnLogout(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminDosen,"login");
        UserSession session = UserSession.getInstace(this.sessionUser);
        session.cleanUserSession();
    }

    @FXML
    private void btnTambah(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminDosen,"formTambahEditUser");
    }

    @FXML
    private void btnUbah(ActionEvent event) throws Exception {
        UserData user = new UserData();
        User temp = user.getUser(tvDosen.getSelectionModel().getSelectedItem().getUserID());
        
        this.userEdit = temp;
        UserEdit.getInstace(this.userEdit);
        
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminDosen,"formTambahEditUser");
    }

    @FXML
    private void btnHapus(ActionEvent event) throws SQLException, Exception {
        UserData userData = new UserData();
        userData.delete(tvDosen.getSelectionModel().getSelectedItem().getUserID());
        showUser();
    }

    @FXML
    private void btnUser(ActionEvent event) {
    }

    @FXML
    private void btnMahasiswa(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminDosen,"dashAdmin");
    }

    @FXML
    private void btnMakul(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminDosen,"dashAdminMakul");
    }

    @FXML
    private void btnJadwal(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminDosen,"dashAdminJadwal");
    }

    @FXML
    private void btnPresensi(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminDosen,"dashAdminPresensi");
    }

    @FXML
    private void btnKelas(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(dashAdminDosen,"dashAdminKelas");
    }
}
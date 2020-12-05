package com.rpl.homebase;

import com.rpl.homebase.data.UserData;
import com.rpl.homebase.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pranata
 */
public class FormTambahEditUserController implements Initializable {

    private User userEdit;
    
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtNama;
    @FXML
    private RadioButton rbBoy;
    @FXML
    private ToggleGroup tgGender;
    @FXML
    private RadioButton rbGirl;
    @FXML
    private Button btnSimpan;
    @FXML
    private AnchorPane formUser;
    @FXML
    private TextField txtBidang;
    @FXML
    private TextField txtUsername;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserEdit edit = UserEdit.getInstace(this.userEdit);
        this.userEdit = edit.getUser();
        
        if(edit.getUser()!=null){
            setField();
        }
    }    
    
    public void setField(){
       txtUsername.setText(this.userEdit.getUsername());
       txtNama.setText(this.userEdit.getName());
       txtBidang.setText(this.userEdit.getBidang());
       txtPassword.setText(this.userEdit.getPassword());
       txtPassword.setDisable(true);
       if(this.userEdit.getGender().equals("Perempuan")){
           rbGirl.setSelected(true);
       }else{
           rbBoy.setSelected(true);
       }
       btnSimpan.setText("Edit");
    }
    
    public void insert() throws Exception{
        String gender = "";
        if(rbBoy.isSelected()){
            gender = rbBoy.getText();
        }
        else{
            gender = rbGirl.getText();
        }
        
        if(txtUsername.getText().equals("") || 
                txtPassword.getText().equals("") || 
                txtNama.getText().equals("") || 
                txtPassword.equals("")||
                tgGender.getSelectedToggle()==null||
                txtBidang.equals("")){
            MessageDialog.infoBox("Field is empty", "Insert Failed", "Failed");
        }
        else{
            UserData userData = new UserData();
            userData.insert(txtUsername.getText(), txtPassword.getText(), txtNama.getText(), gender, txtBidang.getText());
            MessageDialog.infoBox("You have been add : " +txtNama.getText(), "Add User Success", "Success");
            SwitchPane sp = new SwitchPane();
            sp.switchPane(formUser,"dashAdminDosen");
        }
    }
    
    public void update() throws Exception{
        String gender = "";
        if(rbBoy.isSelected()){
            gender = rbBoy.getText();
        }
        else{
            gender = rbGirl.getText();
        }
        
        if(txtUsername.getText().equals("") || txtNama.getText().equals("") || txtBidang.getText().equals("") || tgGender.getSelectedToggle()==null){
            MessageDialog.infoBox("Field is empty", "Update User Failed", "Failed");
        }else{
            UserData userData = new UserData();
            userData.edit(this.userEdit.getUserID(),txtUsername.getText(), txtNama.getText(), gender, txtBidang.getText());
            SwitchPane sp = new SwitchPane();
            MessageDialog.infoBox("You have been update " + txtNama.getText() , "Update User Success", "Success");
            sp.switchPane(formUser,"dashAdminDosen");
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formUser,"dashAdminDosen");
        UserEdit edit = UserEdit.getInstace(this.userEdit);
        edit.cleanUserSession();
    }

    @FXML
    private void btnSimpan(ActionEvent event) throws Exception {
        UserEdit edit = UserEdit.getInstace(this.userEdit);
        this.userEdit = edit.getUser();
        
        if(edit.getUser()!=null){
            update();
        }else{
            insert();
        }
        
    }
}
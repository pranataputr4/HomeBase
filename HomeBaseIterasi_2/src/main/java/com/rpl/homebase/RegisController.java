package com.rpl.homebase;

import com.rpl.homebase.data.UserData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class RegisController implements Initializable {

    @FXML
    private AnchorPane regis;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfPass;
    @FXML
    private TextField txtNama;
    @FXML
    private RadioButton rbBoy;
    @FXML
    private ToggleGroup tgGender;
    @FXML
    private RadioButton rbGirl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRegis(ActionEvent event) throws Exception {
        String gender = "";
        if(rbBoy.isSelected()){
            gender = rbBoy.getText();
        }
        else{
            gender = rbGirl.getText();
        }
        if(txtUsername.getText().equals("") || txtPassword.getText().equals("") || txtNama.getText().equals("") || txtPassword.equals("")||tgGender.getSelectedToggle()==null){
            MessageDialog.infoBox("Field is empty", "Registration Failed", "Failed");
        }
        else if(!txtPassword.getText().equals(txtConfPass.getText())){
              MessageDialog.infoBox("Password does not match", "Registration Failed", "Failed");     
        
        }
        else{
            UserData userData = new UserData();
            userData.signUp(txtUsername.getText(), txtPassword.getText(), txtNama.getText(), gender, "admin");
            MessageDialog.infoBox("You have been regis : " +txtNama.getText(), "Registration Success", "Success");
            SwitchPane sp = new SwitchPane();
            sp.switchPane(regis,"login");
        }
    }

    @FXML
    private void btnLogin(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(regis,"login");
    }
}
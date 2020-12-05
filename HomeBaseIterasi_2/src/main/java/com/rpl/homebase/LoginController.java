package com.rpl.homebase;

import com.rpl.homebase.data.UserData;
import com.rpl.homebase.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pranata
 */
public class LoginController implements Initializable {

    private User sessionUser;
    
    @FXML
    private AnchorPane login;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRegis(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(login,"regis");
    }

    @FXML
    private void btnLogin(ActionEvent event) throws Exception {
        if(txtUsername.getText().equals("") || txtPassword.getText().equals("")){
            MessageDialog.infoBox("Field is empty", "Login Failed", "Failed");
        }else{
            UserData user = new UserData();
            User temp = user.getUser(txtUsername.getText(), txtPassword.getText());
            if(temp==null){
                MessageDialog.infoBox("User Not Found", "Login Failed", "Failed");
            }else{
                if(temp.getLevel().equals("admin")){
                    this.sessionUser = temp;
                    UserSession.getInstace(this.sessionUser);
                    MessageDialog.infoBox("Welcome "+temp.getName(), "Login Success", "Success");
                    SwitchPane sp = new SwitchPane();
                    sp.switchPane(login,"dashAdminDosen");
                }else{
                    this.sessionUser = temp;
                    UserSession.getInstace(this.sessionUser);
                    MessageDialog.infoBox("Welcome "+temp.getName(), "Login Success", "Success");
                    SwitchPane sp = new SwitchPane();
                    sp.switchPane(login,"dashDosen");
                }
                
            }
            
        }
    }
}
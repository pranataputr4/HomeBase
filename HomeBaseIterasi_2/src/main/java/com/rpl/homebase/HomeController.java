package com.rpl.homebase;

import com.rpl.homebase.data.UserData;
import com.rpl.homebase.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Doni
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnLogin(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(home,"login");
    }

    @FXML
    private void btnSignUp(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(home,"regis");
    }
}
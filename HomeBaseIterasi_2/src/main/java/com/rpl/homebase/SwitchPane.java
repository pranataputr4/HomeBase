package com.rpl.homebase;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Doni
 */
public class SwitchPane {
    protected void switchPane(AnchorPane border, String name)throws IOException{
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource(name+".fxml"));
            border.getChildren().setAll(pane);
        }catch(IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(""+ex);
        }
    }
}
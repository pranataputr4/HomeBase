package com.rpl.homebase;

import javafx.scene.control.Alert;

/**
 *
 * @author Doni
 */
public class MessageDialog {
    protected static void infoBox(String infoMessage, String titleBar, String headerMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.show();
    }
}
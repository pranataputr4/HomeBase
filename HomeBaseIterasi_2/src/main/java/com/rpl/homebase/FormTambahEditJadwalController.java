package com.rpl.homebase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pranata
 */
public class FormTambahEditJadwalController implements Initializable {

    @FXML
    private AnchorPane formJadwal;
    @FXML
    private Label lblKodeMakul;
    @FXML
    private Label lblDosen;
    @FXML
    private Button btnSimpan;
    @FXML
    private ComboBox<?> comboKelas;
    @FXML
    private ComboBox<String> comboHari;
    @FXML
    private Label lblMakul;
    @FXML
    private ComboBox<String> comboMulai;
    @FXML
    private ComboBox<String> comboAkhir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboHari.setItems(FXCollections.observableArrayList(
                "Senin", 
                "Selasa",
                "Rabu",
                "Kamis",
                "Jumat",
                "Sabtu"
        ));
        comboMulai.setItems(FXCollections.observableArrayList(
                "7.30",
                "10.30",
                "12.30",
                "14.30",
                "16.30"
        ));
        comboAkhir.setItems(FXCollections.observableArrayList(
                "10.20",
                "12.20",
                "14.20",
                "16.20"
        ));
    }    


    @FXML
    private void btnCancel(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formJadwal,"dashAdminJadwal");
    }

    @FXML
    private void btnSimpan(ActionEvent event) {
    }

    @FXML
    private void selectKelas(ActionEvent event) {
    }

    @FXML
    private void selectHari(ActionEvent event) {
    }
}
package com.rpl.homebase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Doni
 */
public class FormTambahMahasiswaKelasController implements Initializable {

    @FXML
    private AnchorPane formTambahMahasiswa;
    @FXML
    private TableView<?> tvMahasiswa;
    @FXML
    private TableColumn<?, ?> colNim;
    @FXML
    private TableColumn<?, ?> colNama;
    @FXML
    private TableColumn<?, ?> colGender;
    @FXML
    private TableColumn<?, ?> colJurusan;
    @FXML
    private Label lblNamaKelas1;
    @FXML
    private TextField txtSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnBack(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formTambahMahasiswa,"formKelasKuota");
    }

    @FXML
    private void btnTambah(ActionEvent event) {
    }

    @FXML
    private void btnSearch(ActionEvent event) {
    } 
}
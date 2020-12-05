package com.rpl.homebase;

import com.rpl.homebase.data.KelasKuotaData;
import com.rpl.homebase.model.KelasKuota;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Doni
 */
public class FormKelasKuotaController implements Initializable {

    @FXML
    private AnchorPane formKelasKuota;
    @FXML
    private Label lblNamaKelas;
    @FXML
    private Label lblMakul;
    @FXML
    private Label lblKode;
    @FXML
    private Label lblDosen;
    @FXML
    private TableColumn<KelasKuota, String> colNim;
    @FXML
    private TableColumn<KelasKuota, String> colNama;

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
        sp.switchPane(formKelasKuota,"dashAdminKelas");
    }

    @FXML
    private void btnTambah(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formKelasKuota,"formTambahMahasiswaKelas");
    }

    @FXML
    private void btnHapus(ActionEvent event) {
    }
}
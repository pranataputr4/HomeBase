package com.rpl.homebase;

import com.rpl.homebase.data.KelasData;
import com.rpl.homebase.data.MakulData;
import com.rpl.homebase.model.Makul;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Doni
 */
public class FormTambahEditKelasController implements Initializable {

    ArrayList<String> list = null;
    ArrayList<String> listID = null;
    ArrayList<String> listKode = null;
    ArrayList<String> listDosenID = null;
    ArrayList<String> listDosen = null;
    
    @FXML
    private AnchorPane formKelas;
    @FXML
    private TextField txtKelas;
    @FXML
    private ComboBox<String> comboMakul;
    @FXML
    private Label lblKodeMakul;
    @FXML
    private Label lblDosen;
    @FXML
    private Button btnSimpan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MakulData makul = new MakulData();
        try {
            list = makul.getMakulList();
            listID = makul.getMakulIDList();
            listKode = makul.getKodeMakulList();
            listDosen = makul.getDosenList();
            listDosenID = makul.getDosenIDList();
        } catch (SQLException ex) {
            Logger.getLogger(FormTambahEditKelasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        comboMakul.getItems().addAll(list);
    }    
    
    public void insert() throws Exception{
        
        if(txtKelas.getText().equals("") || comboMakul.getSelectionModel().getSelectedItem()==null){
            MessageDialog.infoBox("Field is empty", "Add Class Failed", "Failed");
        }else{
            KelasData kelasData = new KelasData();
            kelasData.insert(
                    txtKelas.getText(), 
                    listID.get(comboMakul.getSelectionModel().getSelectedIndex()), 
                    comboMakul.getSelectionModel().getSelectedItem(), 
                    listDosenID.get(comboMakul.getSelectionModel().getSelectedIndex()),
                    lblDosen.getText(),
                    lblKodeMakul.getText()
            );
            MessageDialog.infoBox("You just submit "+txtKelas.getText(), "Add Class Success", "Success");
            SwitchPane sp = new SwitchPane();
            sp.switchPane(formKelas,"dashAdminKelas");
        }
        
    }

    @FXML
    private void btnCancel(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formKelas,"dashAdminKelas");
    }

    @FXML
    private void btnSimpan(ActionEvent event) throws Exception {
        insert();
    }

    @FXML
    private void selectMakul(ActionEvent event) {
        lblKodeMakul.setText(listKode.get(comboMakul.getSelectionModel().getSelectedIndex()));
        lblDosen.setText(listDosen.get(comboMakul.getSelectionModel().getSelectedIndex()));
    }
}
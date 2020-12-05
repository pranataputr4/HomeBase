package com.rpl.homebase;

import com.rpl.homebase.data.MakulData;
import com.rpl.homebase.data.UserData;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pranata
 */
public class FormTambahEditMakulController implements Initializable {

    private Makul makulEdit;
    
    ArrayList<String> list = null;
    ArrayList<String> listId = null;
    
    @FXML
    private AnchorPane formMakul;
    @FXML
    private TextField txtKode;
    @FXML
    private TextField txtNama;
    @FXML
    private ComboBox<String> comboDosen;
    @FXML
    private Button btnSimpan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MakulEdit edit = MakulEdit.getInstace(this.makulEdit);
        this.makulEdit = edit.getMakul();
        
        UserData user = new UserData();
        
        try {
            list = user.getDosenList();
        } catch (SQLException ex) {
            Logger.getLogger(FormTambahEditMakulController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            listId = user.getIDDosenList();
        } catch (SQLException ex) {
            Logger.getLogger(FormTambahEditMakulController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        comboDosen.getItems().addAll(list);
        
        if(edit.getMakul()!=null){
            setField();
        }
        
    }
    
    public void setField(){
       txtKode.setText(this.makulEdit.getKode());
       txtNama.setText(this.makulEdit.getNama());
       
       comboDosen.setValue(this.makulEdit.getDosen());
       btnSimpan.setText("Edit");
    }

    public void insert() throws Exception{
        
        if(txtNama.getText().equals("") || txtKode.getText().equals("")||comboDosen.getSelectionModel().getSelectedItem()==null){
            MessageDialog.infoBox("Field is empty", "Add Course Failed", "Failed");
        }else{
            MakulData makulData = new MakulData();
            makulData.insert(txtKode.getText(), listId.get(comboDosen.getSelectionModel().getSelectedIndex()), comboDosen.getSelectionModel().getSelectedItem(), txtNama.getText());
            MessageDialog.infoBox("You just submit "+txtNama.getText(), "Add Course Success", "Success");
            SwitchPane sp = new SwitchPane();
            sp.switchPane(formMakul,"dashAdminMakul");
        }
        
    }
    
    public void update() throws Exception{
        
        if(txtKode.getText().equals("") || txtNama.getText().equals("")){
            MessageDialog.infoBox("Field is empty", "Update Course Failed", "Failed");
        }else{
            MakulData makulData = new MakulData();
            makulData.edit(this.makulEdit.getId(),txtKode.getText(), this.makulEdit.getDosenID(), this.makulEdit.getDosen(), txtNama.getText());
            SwitchPane sp = new SwitchPane();
            MessageDialog.infoBox("You have been update " + txtNama.getText() , "Update Course Success", "Success");
            sp.switchPane(formMakul,"dashAdminMakul");
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) throws IOException {
        MakulEdit edit = MakulEdit.getInstace(this.makulEdit);
        edit.cleanMakulSession();
        
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formMakul,"dashAdminMakul");
    }

    @FXML
    private void btnSimpan(ActionEvent event) throws Exception {
        MakulEdit edit = MakulEdit.getInstace(this.makulEdit);
        this.makulEdit = edit.getMakul();
        
        if(edit.getMakul()!=null){
            update();
        }else{
            insert();
        }
    }
}
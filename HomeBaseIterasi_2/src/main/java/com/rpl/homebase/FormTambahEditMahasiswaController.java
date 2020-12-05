package com.rpl.homebase;

import com.rpl.homebase.data.MahasiswaData;
import com.rpl.homebase.model.Mahasiswa;
import com.rpl.homebase.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pranata
 */
public class FormTambahEditMahasiswaController implements Initializable {

    private User sessionUser;
    private Mahasiswa mahasiswaEdit;
    
    @FXML
    private AnchorPane formMahasiswa;
    @FXML
    private TextField txtNim;
    @FXML
    private TextField txtNama;
    @FXML
    private RadioButton rbBoy;
    @FXML
    private ToggleGroup tgGender;
    @FXML
    private RadioButton rbGirl;
    @FXML
    private ComboBox<String> comboJurusan;
    @FXML
    private Button btnSimpan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        comboJurusan.setItems(FXCollections.observableArrayList(
                "Informatika", 
                "Management",
                "Biotek",
                "Sistem Informasi",
                "Akutansi"));
        
        UserSession session = UserSession.getInstace(this.sessionUser);
        this.sessionUser = session.getUser();
        
        MahasiswaEdit edit = MahasiswaEdit.getInstace(this.mahasiswaEdit);
        this.mahasiswaEdit = edit.getMahasiswa();
        
        if(edit.getMahasiswa()!=null){
            setField();
        }
        
        System.out.println(edit.getMahasiswa());
    }    

    @FXML
    private void btnCancel(ActionEvent event) throws IOException {
        SwitchPane sp = new SwitchPane();
        sp.switchPane(formMahasiswa,"dashAdmin");
        MahasiswaEdit edit = MahasiswaEdit.getInstace(this.mahasiswaEdit);
        edit.cleanMahasiswaSession();
    }
    
    public void setField(){
       txtNim.setText(this.mahasiswaEdit.getNim());
       txtNama.setText(this.mahasiswaEdit.getNama());
       if(this.mahasiswaEdit.getKelamin().equals("Perempuan")){
           rbGirl.setSelected(true);
       }else{
           rbBoy.setSelected(true);
       }
       comboJurusan.setValue(this.mahasiswaEdit.getJurusan());
       btnSimpan.setText("Edit");
    }
    
    public void insert() throws Exception{
        String gender = "";
        if(rbBoy.isSelected()){
            gender = rbBoy.getText();
        }
        else{
            gender = rbGirl.getText();
        }
        
        if(txtNim.getText().equals("") || txtNama.getText().equals("") || tgGender.getSelectedToggle()==null){
            MessageDialog.infoBox("Field is empty", "Add Student Failed", "Failed");
        }else{
            MahasiswaData mahasiswaData = new MahasiswaData();
            mahasiswaData.insert(txtNim.getText(), txtNama.getText(), gender, comboJurusan.getSelectionModel().getSelectedItem());
            SwitchPane sp = new SwitchPane();
            MessageDialog.infoBox("You have been add " + txtNama.getText() , "Add Student Success", "Success");
            sp.switchPane(formMahasiswa,"dashAdmin");
        }
    }
    
    public void update() throws Exception{
        String gender = "";
        if(rbBoy.isSelected()){
            gender = rbBoy.getText();
        }
        else{
            gender = rbGirl.getText();
        }
        
        if(txtNim.getText().equals("") || txtNama.getText().equals("") || tgGender.getSelectedToggle()==null){
            MessageDialog.infoBox("Field is empty", "Update Student Failed", "Failed");
        }else{
            MahasiswaData mahasiswaData = new MahasiswaData();
            mahasiswaData.edit(this.mahasiswaEdit.getId(),txtNim.getText(), txtNama.getText(), gender, comboJurusan.getSelectionModel().getSelectedItem());
            SwitchPane sp = new SwitchPane();
            MessageDialog.infoBox("You have been update " + txtNama.getText() , "Update Student Success", "Success");
            sp.switchPane(formMahasiswa,"dashAdmin");
        }
    }

    @FXML
    private void btnSimpan(ActionEvent event) throws Exception {
        MahasiswaEdit edit = MahasiswaEdit.getInstace(this.mahasiswaEdit);
        this.mahasiswaEdit = edit.getMahasiswa();
        
        if(edit.getMahasiswa()==null){
           insert(); 
        }else{
            update();
        }
        
    } 
}
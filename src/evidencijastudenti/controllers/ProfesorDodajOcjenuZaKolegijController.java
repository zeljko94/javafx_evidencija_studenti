
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.KolegijPregledDetalja;
import evidencijastudenti.helpers.StudentPregledOcjena;
import evidencijastudenti.models.Kolegij;
import evidencijastudenti.models.Ocjena;
import evidencijastudenti.models.User;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class ProfesorDodajOcjenuZaKolegijController implements Initializable {
    private User s = StudentPregledOcjena.getStudent();
    private Kolegij k = KolegijPregledDetalja.getKolegij();
    
    @FXML
    Label lblStudent;
    
    @FXML
    Label lblKolegij;
    
    @FXML
    Button btnDodaj;
    
    @FXML
    Button btnReset;
    
    @FXML
    Button btnNazad;
    
    @FXML
    ComboBox<Integer> cmbOcjena;
    
    @FXML
    TextField txtOpis;
    
    @FXML
    DatePicker datum;
    
    @FXML
    Label lblErrors;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblStudent.setText("Student: " + s.getIme() + " " + s.getPrezime());
        lblKolegij.setText("Kolegij: " + k.getNaziv());
        
        ArrayList<Integer> ocj = new ArrayList<>();
        ocj.add(1);
        ocj.add(2);
        ocj.add(3);
        ocj.add(4);
        ocj.add(5);

        cmbOcjena.setItems(FXCollections.observableArrayList(ocj));
    }    
    
    public void setErroText(String txt){
        lblErrors.setTextFill(Color.RED);
        lblErrors.setText(txt);
    }
    
    public void setSuccessText(String txt){
        lblErrors.setTextFill(Color.GREEN);
        lblErrors.setText(txt);
    }
    
    public void btnDodajOnClick(){
        Integer ocjena = cmbOcjena.getSelectionModel().getSelectedItem();
        String opis = txtOpis.getText();
        Date date = datum.getValue() != null ? Date.valueOf(datum.getValue()) : null;
        
        if(ocjena != null){
            if(date != null){
                Ocjena o = new Ocjena(ocjena, opis, date, s.getID(), k.getID());
                o.insert();
                
                setSuccessText("Ocjena uspješno unesena!");
            }
            else{
                setErroText("Unesite datum!");
            }
        }
        else{
            setErroText("Odaberite ocjenu!");
        }
    }
    
    public void btnResetOnClick(){
        cmbOcjena.getSelectionModel().select(null);
        txtOpis.setText("");
        datum.setValue(null);
    }
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - pregled studentovih ocjena za kolegij", btnDodaj.getParent(), "ProfesorPregledStudentovihOcjenaZaKolegij");
    }
    
}

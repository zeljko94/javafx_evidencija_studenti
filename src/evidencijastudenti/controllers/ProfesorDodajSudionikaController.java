
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.KolegijPregledDetalja;
import evidencijastudenti.models.Kolegij;
import evidencijastudenti.models.KolegijSudionik;
import evidencijastudenti.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

public class ProfesorDodajSudionikaController implements Initializable {

    @FXML
    Label lblDodajSudionika;
    
    @FXML
    Button btnDodaj;
    
    @FXML
    Button btnNazad;
    
    @FXML
    ComboBox<User> cmbStudent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Kolegij k = KolegijPregledDetalja.getKolegij();
        cmbStudent.setItems(FXCollections.observableArrayList(User.getSudioniciComboBoxData(k.getID())));
        
        cmbStudent.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User object) {
                return object.getIme() + " " + object.getPrezime() + " - " + object.getBrojIndeksa();
            }

            @Override
            public User fromString(String string) {
                return cmbStudent.getItems().stream().filter(ap -> 
                    (ap.getIme() + " " + ap.getPrezime() + " - " + ap.getBrojIndeksa()).equals(string)).findFirst().orElse(null);
            }
        });
        
        cmbStudent.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null){
                
            }
        });
    }    
    
    public void btnDodajOnClick(){
        Kolegij k = KolegijPregledDetalja.getKolegij();
        User u = cmbStudent.getSelectionModel().getSelectedItem();
        if(u != null){
            KolegijSudionik ks = new KolegijSudionik(u.getID(), k.getID());
            ks.insert();
            cmbStudent.setItems(FXCollections.observableArrayList(User.getSudioniciComboBoxData(k.getID())));
        }
    }
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - pregled detalja kolegija", btnDodaj.getParent(), "ProfesorMojKolegijDetails");
    }
}

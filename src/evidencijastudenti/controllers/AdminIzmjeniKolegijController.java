
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.EditKolegij;
import evidencijastudenti.models.Kolegij;
import evidencijastudenti.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;


public class AdminIzmjeniKolegijController implements Initializable {

    @FXML
    Button btnIzmjeni;
    
    @FXML
    Button btnReset;
    
    @FXML
    Button btnNazad;
    
    @FXML
    TextField txtNaziv;
    
    @FXML
    ComboBox<User> cmbPredavac;
    
    @FXML
    Label lblErrors;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<User> profesori = FXCollections.observableArrayList(User.getAllProfesors());
        cmbPredavac.setItems(profesori);
        
        
        Kolegij k = EditKolegij.getKolegij();
        if(k != null){
            txtNaziv.setText(k.getNaziv());
            
            int index = -1;
            int i=0;
            for(User u: profesori){
                if(u.getID() == k.getPredavacID())
                    index = i;
                i++;
            }
            if(index > -1){
                cmbPredavac.getSelectionModel().select(index);
            }
        }
        
        cmbPredavac.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User object) {
                return object.getIme() + " " + object.getPrezime();
            }

            @Override
            public User fromString(String string) {
                return cmbPredavac.getItems().stream().filter(ap -> 
                    (ap.getIme() + " " + ap.getPrezime()).equals(string)).findFirst().orElse(null);
            }
        });
        
        cmbPredavac.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null){
                
            }
        });
    }    
    
    public void setErroText(String txt){
        lblErrors.setTextFill(Color.RED);
        lblErrors.setText(txt);
    }
    
    public void setSuccessText(String txt){
        lblErrors.setTextFill(Color.GREEN);
        lblErrors.setText(txt);
    }
    
    public void btnIzmjeniOnClick(){
        String naziv = txtNaziv.getText();
        long predavacID = cmbPredavac.getSelectionModel().getSelectedItem() != null ? cmbPredavac.getSelectionModel().getSelectedItem().getID() : 0;
        
        if(!naziv.equals("")){
            if(predavacID != 0){
                Kolegij k = EditKolegij.getKolegij();
                
                k.setNaziv(naziv);
                k.setPredavacID(predavacID);
                k.update();
                
                setSuccessText("Izmjene uspješno spremljene!");
            }
            else{
                setErroText("Odaberite nositelja kolegija!");
            }
        }
        else{
            setErroText("Unesite naziv kolegija!");
        }
    }
    
    public void btnResetOnClick(){
        txtNaziv.setText("");
        cmbPredavac.getSelectionModel().select(null);
    }
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Admin - pregled kolegija", btnIzmjeni.getParent(), "AdminPregledKolegija");
    }  
    
}

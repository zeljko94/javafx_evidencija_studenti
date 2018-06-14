
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.LoggedUserSession;
import evidencijastudenti.models.Kolegij;
import evidencijastudenti.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;


public class ProfesorDodajKolegijController implements Initializable {

    
    @FXML
    Button btnDodaj;
    
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
        String naziv = txtNaziv.getText();
        
        if(!naziv.equals("")){
            Kolegij k = new Kolegij(naziv, LoggedUserSession.getUser().getID());
            k.insert();

            setSuccessText("Kolegij uspješno unesen!");
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
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - moji kolegiji", btnDodaj.getParent(), "ProfesorMojiKolegiji");
    }
}

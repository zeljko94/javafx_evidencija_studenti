
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.EditKolegij;
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


public class ProfesorIzmjeniMojKolegijController implements Initializable {

    @FXML
    Button btnIzmjeni;
    
    @FXML
    Button btnReset;
    
    @FXML
    Button btnNazad;
    
    @FXML
    TextField txtNaziv;
    
    @FXML
    Label lblErrors;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Kolegij k = EditKolegij.getKolegij();
        if(k != null){
            txtNaziv.setText(k.getNaziv());
        }
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
        
        if(!naziv.equals("")){
            Kolegij k = EditKolegij.getKolegij();

            k.setNaziv(naziv);
            k.setPredavacID(LoggedUserSession.getUser().getID());
            k.update();

            setSuccessText("Izmjene uspješno spremljene!");
        }
        else{
            setErroText("Unesite naziv kolegija!");
        }
    }
    
    public void btnResetOnClick(){
        txtNaziv.setText("");
    }
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - pregled kolegija", btnIzmjeni.getParent(), "ProfesorMojiKolegiji");
    }   
    
}

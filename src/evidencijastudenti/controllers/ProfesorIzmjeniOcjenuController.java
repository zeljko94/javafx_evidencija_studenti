package evidencijastudenti.controllers;

import evidencijastudenti.helpers.EditOcjena;
import evidencijastudenti.helpers.KolegijPregledDetalja;
import evidencijastudenti.helpers.LoggedUserSession;
import evidencijastudenti.helpers.StudentPregledOcjena;
import evidencijastudenti.models.Kolegij;
import evidencijastudenti.models.Ocjena;
import evidencijastudenti.models.User;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class ProfesorIzmjeniOcjenuController implements Initializable {

    private Ocjena o = EditOcjena.getOcjena();
    
    @FXML
    ComboBox<User> cmbStudent;
    
    @FXML
    ComboBox<Kolegij> cmbKolegij;
    
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
        
        ArrayList<Integer> ocj = new ArrayList<>();
        ocj.add(1);
        ocj.add(2);
        ocj.add(3);
        ocj.add(4);
        ocj.add(5);

        cmbOcjena.setItems(FXCollections.observableArrayList(ocj));
        
        cmbOcjena.getSelectionModel().select(o.getValue()-1);
        txtOpis.setText(o.getOpis());
        datum.setValue(LocalDate.of(o.getDatum().getYear(), o.getDatum().getMonth(), o.getDatum().getDate()));
        
        
        
        cmbStudent.setItems(FXCollections.observableArrayList(User.getProfesoroveUcenike(LoggedUserSession.getUser().getID())));
        cmbKolegij.setItems(FXCollections.observableArrayList(Kolegij.getProfesorMojiKolegiji(LoggedUserSession.getUser().getID())));
        
        cmbKolegij.getSelectionModel().select(Kolegij.getById(o.getKolegijID()));
        cmbStudent.getSelectionModel().select(User.getById(o.getStudentID()));
        
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
        
        
        cmbKolegij.setConverter(new StringConverter<Kolegij>() {
            @Override
            public String toString(Kolegij object) {
                return object.getNaziv();
            }

            @Override
            public Kolegij fromString(String string) {
                return cmbKolegij.getItems().stream().filter(ap -> 
                    (ap.getNaziv()).equals(string)).findFirst().orElse(null);
            }
        });
        
        cmbKolegij.valueProperty().addListener((obs, oldval, newval) -> {
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
    
    public void btnDodajOnClick(){
        User student = cmbStudent.getSelectionModel().getSelectedItem();
        Kolegij kolegij = cmbKolegij.getSelectionModel().getSelectedItem();
        Integer ocjena = cmbOcjena.getSelectionModel().getSelectedItem();
        String opis = txtOpis.getText();
        Date date = datum.getValue() != null ? Date.valueOf(datum.getValue()) : null;
        

        if(student != null){
            if(kolegij != null){
                if(ocjena != null){
                    if(date != null){
                        Ocjena o = EditOcjena.getOcjena();
                        o.setKolegijID(kolegij.getID());
                        o.setStudentID(student.getID());
                        o.setValue(ocjena);
                        o.setOpis(opis);
                        o.setDatum(date);
                        o.update();

                        PauseTransition pt = new PauseTransition(Duration.seconds(1));
                        pt.setOnFinished(e -> btnResetOnClick());
                        pt.play();

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
            else{
            setErroText("Odaberite kolegij!");
            }
        }
        else{
            setErroText("Odaberite studenta!");
        }
    }
    
    public void btnResetOnClick(){
        cmbStudent.getSelectionModel().select(null);
        cmbKolegij.getSelectionModel().select(null);
        cmbOcjena.getSelectionModel().select(null);
        txtOpis.setText("");
        datum.setValue(null);
        lblErrors.setText("");
    }
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - pregled ocjena", btnDodaj.getParent(), "ProfesorPregledOcjena");
    } 
    
}

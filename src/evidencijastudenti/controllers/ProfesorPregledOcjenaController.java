
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.EditOcjena;
import evidencijastudenti.helpers.LoggedUserSession;
import evidencijastudenti.models.Kolegij;
import evidencijastudenti.models.KolegijSudionik;
import evidencijastudenti.models.Ocjena;
import evidencijastudenti.models.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;



public class ProfesorPregledOcjenaController implements Initializable {

    @FXML
    ComboBox<User> cmbStudent;
    
    @FXML
    ComboBox<Kolegij> cmbKolegij;
    
    @FXML
    TableView<Ocjena> tblOcjene;
    
    @FXML
    TableColumn<Ocjena, Integer> tblColOcjena;
    
    @FXML
    TableColumn<Ocjena, String> tblColOpis;
    
    @FXML
    TableColumn<Ocjena, String> tblColDatum;
    
    @FXML
    TableColumn<Ocjena, String> tblColStudent;
    
    @FXML
    TableColumn<Ocjena, String> tblColKolegij;
    
    @FXML
    Button btnDodaj;
    
    @FXML
    Button btnBrisi;
    
    @FXML
    Button btnIzmjeni;
    
    @FXML
    Button btnNazad;
    
    public void filterUsers(long KolegijID){
        ArrayList<User> users = KolegijSudionik.getAllForKolegij(KolegijID);
        cmbStudent.setItems(FXCollections.observableArrayList(users));
    }
    
    public void filterOcjeneData(Kolegij kolegij, User student){
        ArrayList<Ocjena> ocjene = Ocjena.getByProfesorID(LoggedUserSession.getUser().getID());
        ArrayList<Ocjena> toRemove = new ArrayList<>();
                    toRemove = new ArrayList<>();
            if(student != null){
                for(Ocjena o: ocjene){
                    if(o.getStudentID() != student.getID())
                        toRemove.add(o);
                }
            }
            ocjene.removeAll(toRemove);
        
        if(kolegij != null){
            for(Ocjena o: ocjene){
                if(o.getKolegijID() != kolegij.getID())
                    toRemove.add(o);
            }
        

        }
        ocjene.removeAll(toRemove);
        tblOcjene.setItems(FXCollections.observableArrayList(ocjene));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Kolegij> cmbKolegijiData = new ArrayList<>();
        cmbKolegijiData.add(null);
        cmbKolegijiData.addAll(Kolegij.getProfesorMojiKolegiji(LoggedUserSession.getUser().getID()));
        cmbKolegij.setItems(FXCollections.observableArrayList(cmbKolegijiData));
        cmbKolegij.getSelectionModel().select(0);
        
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
            /*if(newval != null){
            }*/
            filterOcjeneData(newval, cmbStudent.getSelectionModel().getSelectedItem());
        });
        
        
        ArrayList<User> cmbStudentiData = new ArrayList<>();
        cmbStudentiData.add(null);
        cmbStudentiData.addAll(User.getProfesoroveUcenike(LoggedUserSession.getUser().getID()));
        cmbStudent.setItems(FXCollections.observableArrayList(cmbStudentiData));
        cmbStudent.getSelectionModel().select(null);
        
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
            /*if(newval != null){
            }*/
            filterOcjeneData(cmbKolegij.getSelectionModel().getSelectedItem(), newval);
        });
        
        
        tblColOcjena.setCellValueFactory(new PropertyValueFactory<Ocjena, Integer>("Value"));
        tblColOpis.setCellValueFactory(new PropertyValueFactory<Ocjena, String>("Opis"));
        tblColDatum.setCellValueFactory(new PropertyValueFactory<Ocjena, String>("DatumString"));
        tblColStudent.setCellValueFactory(new PropertyValueFactory<Ocjena, String>("NazivStudenta"));
        tblColKolegij.setCellValueFactory(new PropertyValueFactory<Ocjena, String>("NazivKolegija"));
        tblOcjene.setItems(FXCollections.observableArrayList(Ocjena.getByProfesorID(LoggedUserSession.getUser().getID())));
    }  
    
    public void btnDodajOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - dodaj ocjenu", btnDodaj.getParent(), "ProfesorDodajOcjenu");
    }
    
    public void btnBrisiOnClick(){
        Ocjena o = tblOcjene.getSelectionModel().getSelectedItem();
        if(o != null){
            o.delete();
            filterOcjeneData(cmbKolegij.getSelectionModel().getSelectedItem(), cmbStudent.getSelectionModel().getSelectedItem());
        }
    }
    
    public void btnIzmjeniOnClick(){
        Ocjena o = tblOcjene.getSelectionModel().getSelectedItem();
        if(o != null){
            EditOcjena.setOcjena(o);
            evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - izmjeni ocjenu", btnDodaj.getParent(), "ProfesorIzmjeniOcjenu");
        }
    }
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - Dashboard", btnDodaj.getParent(), "ProfesorDashboard");
    }
    
}

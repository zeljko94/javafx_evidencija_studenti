package evidencijastudenti.controllers;

import evidencijastudenti.helpers.EditOcjena;
import evidencijastudenti.helpers.LoggedUserSession;
import evidencijastudenti.models.Kolegij;
import evidencijastudenti.models.KolegijSudionik;
import evidencijastudenti.models.Ocjena;
import evidencijastudenti.models.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;


public class StudentPregledOcjenaController implements Initializable {
    
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
    Button btnNazad;
   
    
    public void filterOcjeneData(Kolegij kolegij){
        ArrayList<Ocjena> ocjene = Ocjena.getByStudentID(LoggedUserSession.getUser().getID());
        ArrayList<Ocjena> toRemove = new ArrayList<>();
                    toRemove = new ArrayList<>();
        
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
        cmbKolegijiData.addAll(Kolegij.getStudentMojiKolegiji(LoggedUserSession.getUser().getID()));
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
            filterOcjeneData(newval);
        });
        
               
        tblColOcjena.setCellValueFactory(new PropertyValueFactory<Ocjena, Integer>("Value"));
        tblColOpis.setCellValueFactory(new PropertyValueFactory<Ocjena, String>("Opis"));
        tblColDatum.setCellValueFactory(new PropertyValueFactory<Ocjena, String>("DatumString"));
        tblColStudent.setCellValueFactory(new PropertyValueFactory<Ocjena, String>("NazivStudenta"));
        tblColKolegij.setCellValueFactory(new PropertyValueFactory<Ocjena, String>("NazivKolegija"));
        tblOcjene.setItems(FXCollections.observableArrayList(Ocjena.getByStudentID(LoggedUserSession.getUser().getID())));
    }  

    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Dashboard", btnNazad.getParent(), "StudentDashboard");
    }   
    
}

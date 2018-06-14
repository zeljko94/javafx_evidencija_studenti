
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.EditOcjena;
import evidencijastudenti.helpers.KolegijPregledDetalja;
import evidencijastudenti.helpers.StudentPregledOcjena;
import evidencijastudenti.models.Kolegij;
import evidencijastudenti.models.Ocjena;
import evidencijastudenti.models.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ProfesorPregledStudentovihOcjenaZaKolegijController implements Initializable {

    private Kolegij k = KolegijPregledDetalja.getKolegij();
    private User u = StudentPregledOcjena.getStudent();
    
    @FXML
    Label lblTitle;
    
    @FXML
    TableView<Ocjena> tblOcjene;
    
    @FXML
    TableColumn<Ocjena, Integer> tblColOcjena;
    
    @FXML
    TableColumn<Ocjena, String> tblColOpis;
    
    @FXML
    TableColumn<Ocjena, String> tblColDatum;
    
    @FXML
    Button btnDodaj;
    
    @FXML
    Button btnIzmjeni;
    
    @FXML
    Button btnBrisi;
    
    @FXML
    Button btnNazad;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblTitle.setText(k.getNaziv() + " - " + u.getIme() + " " + u.getPrezime() + " " + u.getBrojIndeksa());
        
        
        tblColOcjena.setCellValueFactory(new PropertyValueFactory<Ocjena, Integer>("Value"));
        tblColOpis.setCellValueFactory(new PropertyValueFactory<Ocjena, String>("Opis"));
        tblColDatum.setCellValueFactory(new PropertyValueFactory<Ocjena, String>("DatumString"));
        tblOcjene.setItems(FXCollections.observableArrayList(Ocjena.getForStudentAndKolegij(u.getID(), k.getID())));
    }    
    
    public void btnDodajOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - dodaj ocjenu", btnDodaj.getParent(), "ProfesorDodajOcjenuZaKolegij");
    }
    
    public void btnIzmjeniOnClick(){
        Ocjena o = tblOcjene.getSelectionModel().getSelectedItem();
        if(o != null){
            EditOcjena.setOcjena(o);
            evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - izmjenu ocjenu", btnDodaj.getParent(), "ProfesorIzmjeniOcjenuZaKolegij");
        }
    }
    
    public void btnBrisiOnClick(){
        Ocjena o = tblOcjene.getSelectionModel().getSelectedItem();
        if(o != null){
            o.delete();
            tblOcjene.setItems(FXCollections.observableArrayList(Ocjena.getForStudentAndKolegij(u.getID(), k.getID())));
        }
    }
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - pregled detalja kolegija", btnDodaj.getParent(), "ProfesorMojKolegijDetails");
    }
    
}

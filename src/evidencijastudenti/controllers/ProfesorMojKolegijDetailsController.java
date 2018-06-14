
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.KolegijPregledDetalja;
import evidencijastudenti.helpers.StudentPregledOcjena;
import evidencijastudenti.models.Kolegij;
import evidencijastudenti.models.KolegijSudionik;
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

public class ProfesorMojKolegijDetailsController implements Initializable {

    @FXML
    TableView<User> tblSudionici;
    
    @FXML
    TableColumn<User, String> tblColIme;
    
    @FXML
    TableColumn<User, String> tblColPrezime;
    
    @FXML
    TableColumn<User, String> tblColUsername;
    
    @FXML
    TableColumn<User, String> tblColEmail;
    
    @FXML
    TableColumn<User, String> tblColBrojTelefona;
    
    @FXML
    TableColumn<User, String> tblColBrojIndeksa;
            
    @FXML
    Label lblTitle;
    
    @FXML
    Button btnDodajSudionika;
    
    @FXML
    Button btnBrisiSudionika;
    
    @FXML
    Button btnPregledOcjena;
    
    @FXML
    Button btnNazad;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Kolegij k = KolegijPregledDetalja.getKolegij();
        lblTitle.setText(k.getNaziv());
        
        ObservableList<User> data = FXCollections.observableArrayList(KolegijSudionik.getAllForKolegij(k.getID()));
        tblColIme.setCellValueFactory(new PropertyValueFactory<User, String>("Ime"));
        tblColPrezime.setCellValueFactory(new PropertyValueFactory<User, String>("Prezime"));
        tblColUsername.setCellValueFactory(new PropertyValueFactory<User, String>("Username"));
        tblColEmail.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        tblColBrojTelefona.setCellValueFactory(new PropertyValueFactory<User, String>("BrojTelefona"));
        tblColBrojIndeksa.setCellValueFactory(new PropertyValueFactory<User, String>("BrojIndeksa"));
        tblSudionici.setItems(data);
    }    
    
    public void btnDodajSudionikaOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - dodaj studenta na kolegij", btnNazad.getParent(), "ProfesorDodajSudionika");
    }
    
    public void btnBrisiSudionikaOnClick(){
        User u = tblSudionici.getSelectionModel().getSelectedItem();
        if(u != null){
            Kolegij k = KolegijPregledDetalja.getKolegij();
            KolegijSudionik.delete(u.getID(), k.getID());
            ObservableList<User> data = FXCollections.observableArrayList(KolegijSudionik.getAllForKolegij(k.getID()));
            tblSudionici.setItems(data);
        }
    }
    
    public void btnPregledOcjenaOnClick(){
        User s = tblSudionici.getSelectionModel().getSelectedItem();
        if(s != null){
            StudentPregledOcjena.setStudent(s);
            evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - pregled studentovih ocjena za odabrani kolegij", btnNazad.getParent(), "ProfesorPregledStudentovihOcjenaZaKolegij");
        }
    }
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - moji kolegiji", btnNazad.getParent(), "ProfesorMojiKolegiji");
    }
    
}

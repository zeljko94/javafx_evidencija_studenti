
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.EditKolegij;
import evidencijastudenti.helpers.KolegijPregledDetalja;
import evidencijastudenti.helpers.LoggedUserSession;
import evidencijastudenti.models.Kolegij;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProfesorMojiKolegijiController implements Initializable {

    
    @FXML
    TableView<Kolegij> tblMojiKolegiji;
    
    @FXML
    TableColumn<Kolegij, String> tblColNaziv;
    
    @FXML
    Button btnIzmjeni;
    
    @FXML
    Button btnBrisi;
    
    @FXML
    Button btnDodaj;
    
    @FXML
    Button btnNazad;
    
    @FXML
    Button btnPregledDetalja;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Kolegij> kolegiji = Kolegij.getProfesorMojiKolegiji(LoggedUserSession.getUser().getID());
        ObservableList<Kolegij> data = FXCollections.observableArrayList(kolegiji);
        tblColNaziv.setCellValueFactory(new PropertyValueFactory<Kolegij, String>("Naziv"));
        tblMojiKolegiji.setItems(data);
    }    
    
    public void btnIzmjeniOnClick(){
        Kolegij k = tblMojiKolegiji.getSelectionModel().getSelectedItem();
        if(k != null){
            EditKolegij.setKolegij(k);
            evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - izmjeni kolegij", btnDodaj.getParent(), "ProfesorIzmjeniMojKolegij");
        }
    }
    
    public void btnBrisiOnClick(){
        Kolegij k = tblMojiKolegiji.getSelectionModel().getSelectedItem();
        if(k != null){
            k.delete();
            tblMojiKolegiji.setItems(FXCollections.observableArrayList(Kolegij.getProfesorMojiKolegiji(LoggedUserSession.getUser().getID())));
        }
    }
    
    public void btnDodajOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - dodaj kolegij", btnDodaj.getParent(), "ProfesorDodajKolegij");
    }
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - Dashboard", btnDodaj.getParent(), "ProfesorDashboard");
    }
    
    public void btnPregledDetaljaOnClick(){
        Kolegij k = tblMojiKolegiji.getSelectionModel().getSelectedItem();
        if(k != null){
            KolegijPregledDetalja.setKolegij(k);
            evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - pregled detalja kolegija", btnDodaj.getParent(), "ProfesorMojKolegijDetails");
        }
    }
}

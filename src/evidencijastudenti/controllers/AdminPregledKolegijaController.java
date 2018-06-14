
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.EditKolegij;
import evidencijastudenti.models.Kolegij;
import evidencijastudenti.models.User;
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

public class AdminPregledKolegijaController implements Initializable {

    @FXML
    Button btnIzmjeni;
    
    @FXML
    Button btnBrisi;
    
    @FXML
    Button btnDodaj;
    
    @FXML
    TableView<Kolegij> tblKolegiji;
    
    @FXML
    TableColumn<Kolegij, String> tblColNaziv;
    
    @FXML
    TableColumn<Kolegij, String> tblColPredavac;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Kolegij> kolegiji = Kolegij.getAll();
        ObservableList<Kolegij> data = FXCollections.observableArrayList(kolegiji);
        tblColNaziv.setCellValueFactory(new PropertyValueFactory<Kolegij, String>("Naziv"));
        tblColPredavac.setCellValueFactory(new PropertyValueFactory<Kolegij, String>("Predavac"));
        tblKolegiji.setItems(data);
    }    
    
    public void btnIzmjeniOnClick(){
        Kolegij kolegij = tblKolegiji.getSelectionModel().getSelectedItem();
        if(kolegij != null){
            EditKolegij.setKolegij(kolegij);
            evidencijastudenti.EvidencijaStudenti.openWindow("Admin - izmjeni kolegij", btnDodaj.getParent(), "AdminIzmjeniKolegij");
        }
    }
    
    public void btnBrisiOnClick(){
        Kolegij kolegij = tblKolegiji.getSelectionModel().getSelectedItem();
        if(kolegij != null){
            kolegij.delete();
            tblKolegiji.setItems(FXCollections.observableArrayList(Kolegij.getAll()));
        }
    }
    
    public void btnDodajOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Admin - dodaj kolegij", btnDodaj.getParent(), "AdminDodajKolegij");
    }
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Admin - dashboard", btnDodaj.getParent(), "AdminDashboard");
    }
}

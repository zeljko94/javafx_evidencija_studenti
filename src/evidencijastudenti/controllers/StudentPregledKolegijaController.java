
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

public class StudentPregledKolegijaController implements Initializable {

    
    @FXML
    TableView<Kolegij> tblMojiKolegiji;
    
    @FXML
    TableColumn<Kolegij, String> tblColNaziv;
    
    @FXML
    TableColumn<Kolegij, String> tblColPredavac;
    
    @FXML
    Button btnNazad;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Kolegij> kolegiji = Kolegij.getStudentMojiKolegiji(LoggedUserSession.getUser().getID());
        ObservableList<Kolegij> data = FXCollections.observableArrayList(kolegiji);
        tblColNaziv.setCellValueFactory(new PropertyValueFactory<Kolegij, String>("Naziv"));
        tblColPredavac.setCellValueFactory(new PropertyValueFactory<Kolegij, String>("Predavac"));
        tblMojiKolegiji.setItems(data);
    }    
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Dashboard", btnNazad.getParent(), "StudentDashboard");
    }
    
}

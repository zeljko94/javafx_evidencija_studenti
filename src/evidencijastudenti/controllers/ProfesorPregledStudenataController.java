
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.EditProfesor;
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

public class ProfesorPregledStudenataController implements Initializable {
@FXML
    Button btnIzmjeni;
    
    @FXML
    Button btnBrisi;
    
    @FXML
    Button btnDodaj;
    
    @FXML
    Button btnNazad;
    
    @FXML
    TableView<User> tblStudenti;
    
    @FXML
    TableColumn<User, String> tblColIme; 
    
    @FXML
    TableColumn<User, String> tblColPrezime;
    
    @FXML
    TableColumn<User, String> tblColUsername;
            
    @FXML
    TableColumn<User, String> tblColEmail;
    
    @FXML
    TableColumn<User, String> tblColPhoneNr;
    
    @FXML
    TableColumn<User, String> tblColBrojIndeksa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<User> users = User.getAllUcenike();
        ObservableList<User> data = FXCollections.observableArrayList(users);
        tblColIme.setCellValueFactory(new PropertyValueFactory<User, String>("Ime"));
        tblColPrezime.setCellValueFactory(new PropertyValueFactory<User, String>("Prezime"));
        tblColUsername.setCellValueFactory(new PropertyValueFactory<User, String>("Username"));
        tblColEmail.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        tblColPhoneNr.setCellValueFactory(new PropertyValueFactory<User, String>("BrojTelefona"));
        tblColBrojIndeksa.setCellValueFactory(new PropertyValueFactory<User, String>("BrojIndeksa"));
        tblStudenti.setItems(data);
    }   
    
    public void btnDodajOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - Dodaj studenta", btnDodaj.getParent(), "ProfesorDodajStudenta");
    }
    
    public void btnIzmjeniOnClick(){
        User user = tblStudenti.getSelectionModel().getSelectedItem();
        if(user != null){
            EditProfesor.setUser(user);
            evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - izmjena studenta", btnDodaj.getParent(), "ProfesorIzmjeniStudenta");
        }
    }
    
    public void btnBrisiOnClick(){
        User user = this.tblStudenti.getSelectionModel().getSelectedItem();
        if(user != null){
            user.delete();
            ObservableList<User> data = FXCollections.observableArrayList(User.getAllUcenike());
            this.tblStudenti.setItems(data);
        }
    } 
    
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - dashboard", btnDodaj.getParent(), "ProfesorDashboard");
    }
    
}

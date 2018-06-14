
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.LoggedUserSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProfesorDashboardController implements Initializable {

    @FXML
    ImageView btnPregledStudenata;
    
    @FXML
    ImageView btnMojiKolegiji;
    
    @FXML
    ImageView btnPregledOcjena;
    
    @FXML
    ImageView btnPostavkeProfila;
    
    @FXML
    ImageView btnLogout;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    public void btnPregledStudenataOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnPregledStudenata);
    }
    
    public void btnPregledStudenataOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnPregledStudenata);
    }
    
    public void btnMojiKolegijiOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnMojiKolegiji);
    }
    
    public void btnMojiKolegijiOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnMojiKolegiji);
    }
    
        
    public void btnPregledOcjenaOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnPregledOcjena);
    }
    
    public void btnPregledOcjenaOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnPregledOcjena);
    }
    
    
    public void btnLogoutOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnLogout);
    }
    
    public void btnLogoutOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnLogout);
    }
    
    public void btnPostavkeProfilaOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnPostavkeProfila);
        
    }
    
    public void btnPostavkeProfilaOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnPostavkeProfila);
    }
    
    
    
    public void btnPregledStudenataOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - pregled studenata", btnLogout.getParent(), "ProfesorPregledStudenata");
    }
    
    public void btnMojiKolegijiOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - moji kolegiji", btnLogout.getParent(), "ProfesorMojiKolegiji");
    }
    
    public void btnPregledOcjenaOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - pregled ocjena", btnLogout.getParent(), "ProfesorPregledOcjena");
    }
    
    public void btnPostavkeProfilaOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - postavke profila", btnLogout.getParent(), "ProfesorProfileSettings");
    }
    
    public void btnLogoutOnClick(){
        LoggedUserSession.setUser(null);
        evidencijastudenti.EvidencijaStudenti.openWindow("Login", btnLogout.getParent(), "Login");
    }
    
}

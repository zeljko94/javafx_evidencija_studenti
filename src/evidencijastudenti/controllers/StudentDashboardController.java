
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.LoggedUserSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class StudentDashboardController implements Initializable {

    @FXML
    ImageView btnLogout;
    
    @FXML
    ImageView btnPostavkeProfila;
    
    @FXML
    ImageView btnPregledOcjena;
    
    @FXML
    ImageView btnMojiKolegiji;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void btnLogoutOnClick(){
        LoggedUserSession.setUser(null);
        evidencijastudenti.EvidencijaStudenti.openWindow("Login", btnLogout.getParent(), "Login");
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
    
    public void btnPostavkeProfilaOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnPostavkeProfila);
    }
    
    public void btnPostavkeProfilaOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnPostavkeProfila);
    }
    
    public void btnLogoutOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnLogout);
    }
    
    public void btnLogoutOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnLogout);
    }
    
    public void btnPostavkeProfilaOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Postavke profila", btnLogout.getParent(), "StudentPostavkeProfila");
    }
    
    public void btnPregledOcjenaOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Pregled ocjena", btnLogout.getParent(), "StudentPregledOcjena");
    }
    
    public void btnMojiKolegijiOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Pregled kolegija", btnLogout.getParent(), "StudentPregledKolegija");
    }
}

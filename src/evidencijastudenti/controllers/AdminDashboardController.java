
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.LoggedUserSession;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AdminDashboardController implements Initializable {
    
    @FXML
    ImageView btnPregledProfesora;
    
    @FXML
    ImageView btnPregledStudenata;
    
    @FXML
    ImageView btnPregledKolegija;
    
    @FXML
    ImageView btnLogout;
    
    @FXML
    ImageView btnProfileSettings;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void btnLogoutOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnLogout);
    }
    
    public void btnLogoutOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnLogout);
    }
    
    public void btnPregledStudenataOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnPregledStudenata);
    }
    
    public void btnPregledStudenataOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnPregledStudenata);
    }
    
    public void btnPostavkeProfilaOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnProfileSettings);
        
    }
    
    public void btnPostavkeProfilaOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnProfileSettings);
    }
    
    public void btnPregledProfesoraOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnPregledProfesora);
    }
    
    public void btnPregledProfesoraOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnPregledProfesora);
    }
    
    public void btnPregledKolegijaOnMouseLeave(){
        evidencijastudenti.EvidencijaStudenti.GlowOff(btnPregledKolegija);
    }
    
    public void btnPregledKolegijaOnMouseEnter(){
        evidencijastudenti.EvidencijaStudenti.GlowOn(btnPregledKolegija);
    }
    
    
    
    
    public void btnProfileSettingsOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Admin - Postavke profila", btnLogout.getParent(), "AdminPostavkeProfila");
    }
    
    
    public void btnPregledProfesoraOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Admin - Pregled profesora", btnLogout.getParent(), "AdminPregledProfesora");
    }
    
    public void btnPregledStudenataOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Admin - Pregled studenata", btnLogout.getParent(), "AdminPregledStudenata");
    }
    
        
    public void btnPregledKolegijaOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Admin - Pregled kolegija", btnLogout.getParent(), "AdminPregledKolegija");
    }
            
    public void btnLogoutOnClick(){
        LoggedUserSession.setUser(null);
        evidencijastudenti.EvidencijaStudenti.openWindow("Login", btnLogout.getParent(), "Login");
    }
}

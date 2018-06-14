/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.LoggedUserSession;
import evidencijastudenti.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author owner
 */
public class LoginController implements Initializable {

    @FXML
    TextField txtUsername;
    
    @FXML
    PasswordField txtPassword;
    
    @FXML
    Label lblErrorMsg;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtUsername.setText("profesor");
        txtPassword.setText("asd");
    }    
    
        public void btnLogin_onClick(ActionEvent e){
        String username = String.valueOf(txtUsername.getText());
        String password = String.valueOf(txtPassword.getText());
        
        if(!username.equals("")){
            if(!password.equals("")){
                User user = User.login(username, password);
                
                if(user != null){
                    LoggedUserSession.setUser(user);
                    if(user.getPrivilegije().equals("admin")){
                        evidencijastudenti.EvidencijaStudenti.openWindow("Admin - Dashboard", txtUsername.getParent(), "AdminDashboard");
                    }
                    else if(user.getPrivilegije().equals("profesor")){
                        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - Dashboard", txtUsername.getParent(), "ProfesorDashboard");
                    }
                    else if(user.getPrivilegije().equals("ucenik")){
                        evidencijastudenti.EvidencijaStudenti.openWindow("Student - Dashboard", txtUsername.getParent(), "StudentDashboard");
                    }
                }
                else{
                    lblErrorMsg.setText("Greška prilikom logiranja!");
                }
            }
            else{
                lblErrorMsg.setText("Unesite lozinku!");
            }
        }
        else{
            lblErrorMsg.setText("Unesite korisničko ime!");
        }
    }
    
    public void btnRegister_onClick(ActionEvent e){
        
    }
    
}

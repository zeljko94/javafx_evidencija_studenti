/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencijastudenti.controllers;

import evidencijastudenti.helpers.LoggedUserSession;
import evidencijastudenti.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author owner
 */
public class AdminProfileSettingsController implements Initializable {

   
@FXML
    Button btnDodaj;
    
    @FXML
    Button btnReset;
    
    @FXML
    Button btnNazad;
    
    @FXML
    javafx.scene.control.TextField txtIme;
    
    @FXML
    javafx.scene.control.TextField txtPrezime;
    
    @FXML
    javafx.scene.control.TextField txtUsername;
    
    @FXML
    javafx.scene.control.TextField txtEmail;
    
    @FXML
    javafx.scene.control.TextField txtPhoneNr;
    
    @FXML
    PasswordField txtPassword;
    
    
    @FXML
    PasswordField txtRePassword;
    
    
    @FXML
    Label lblErrors;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User u = LoggedUserSession.getUser();
        
        if(u != null){
            txtIme.setText(u.getIme());
            txtPrezime.setText(u.getPrezime());
            txtUsername.setText(u.getUsername());
            txtPassword.setText(u.getPassword());
            txtRePassword.setText(u.getPassword());
            txtEmail.setText(u.getEmail());
            txtPhoneNr.setText(u.getBrojTelefona());
        }
    }    
    
    public void setErroText(String txt){
        lblErrors.setTextFill(Color.RED);
        lblErrors.setText(txt);
    }
    
    public void setSuccessText(String txt){
        lblErrors.setTextFill(Color.GREEN);
        lblErrors.setText(txt);
    }
    
    
    public void btnDodajOnClick(){
        String ime = txtIme.getText();
        String prezime = txtPrezime.getText();
        String username = txtUsername.getText();
        String password = String.valueOf(txtPassword.getText());
        String rePassword = String.valueOf(txtRePassword.getText());
        String email = txtEmail.getText();
        String brojTelefona = txtPhoneNr.getText();
        
        if(!ime.equals("")){
            if(!prezime.equals("")){
                if(!username.equals("")){
                    if(!password.equals("")){
                        if(!rePassword.equals("")){
                            if(password.equals(rePassword)){
                                if(!email.equals("")){
                                    User u = LoggedUserSession.getUser();
        
                                    if(u != null){
                                        u.setIme(ime);
                                        u.setPrezime(prezime);
                                        u.setUsername(username);
                                        u.setPassword(password);
                                        u.setEmail(email);
                                        u.setBrojTelefona(brojTelefona);
                                        u.update();
                                    }
                                    
                                    setSuccessText("Izmjene uspješno spremljene!");
                                }
                                else{
                                    setErroText("Unesite email!");
                                }
                            }
                            else{
                                setErroText("Passwordi se ne podudaraju!");
                            }
                        }
                        else{
                            setErroText("Ponovite password!");
                        }
                    }
                    else{
                        setErroText("Unesite password!");
                    }
                }
                else{
                    setErroText("Unesite korisničko ime!");
                }
            }
            else{
                setErroText("Unesite prezime!");
            }
        }
        else{
            setErroText("Unesite ime!");
        }
    }
    
    public void btnResetOnClick(){
        txtIme.setText("");
        txtPrezime.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtRePassword.setText("");
        txtEmail.setText("");
        txtPhoneNr.setText("");
    }
    
        
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Dashboard", btnDodaj.getParent(), "ProfesorDashboard");
    } 
    
}

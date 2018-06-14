
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class ProfesorIzmjeniStudentaController implements Initializable {

    @FXML
    Button btnIzmjeni;
    
    @FXML
    Button btnReset;
    
    @FXML
    Button btnNazad;
    
    @FXML
    TextField txtIme;
    
    @FXML
    TextField txtPrezime;
    
    @FXML
    TextField txtUsername;
    
    @FXML
    TextField txtEmail;
    
    @FXML
    TextField txtBrojIndeksa;
    
    @FXML
    TextField txtPhoneNr;
    
    @FXML
    PasswordField txtPassword;
    
    
    @FXML
    PasswordField txtRePassword;
    
    @FXML
    Label lblErrors;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User profesor = EditProfesor.getUser();
        txtIme.setText(profesor.getIme());
        txtPrezime.setText(profesor.getPrezime());
        txtUsername.setText(profesor.getUsername());
        txtEmail.setText(profesor.getEmail());
        txtPassword.setText(profesor.getPassword());
        txtRePassword.setText(profesor.getPassword());
        txtPhoneNr.setText(profesor.getBrojTelefona());
        txtBrojIndeksa.setText(profesor.getBrojIndeksa());
    }    
    
    public void setErroText(String txt){
        lblErrors.setTextFill(Color.RED);
        lblErrors.setText(txt);
    }
    
    public void setSuccessText(String txt){
        lblErrors.setTextFill(Color.GREEN);
        lblErrors.setText(txt);
    }
    
    
    public void btnIzmjeniOnClick(){
        String ime = txtIme.getText();
        String prezime = txtPrezime.getText();
        String username = txtUsername.getText();
        String password = String.valueOf(txtPassword.getText());
        String rePassword = String.valueOf(txtRePassword.getText());
        String email = txtEmail.getText();
        String brojTelefona = txtPhoneNr.getText();
        String brojIndeksa = txtBrojIndeksa.getText();
        
        if(!ime.equals("")){
            if(!prezime.equals("")){
                if(!username.equals("")){
                    if(!password.equals("")){
                        if(!rePassword.equals("")){
                            if(password.equals(rePassword)){
                                if(!email.equals("")){
                                    User user = EditProfesor.getUser();
                                    if(user != null){
                                        user.setUsername(username);
                                        user.setPassword(password);
                                        user.setEmail(email);
                                        user.setIme(ime);
                                        user.setPrezime(prezime);
                                        user.setPrivilegije("ucenik");
                                        user.setBrojIndeksa(brojIndeksa);
                                        user.setBrojTelefona(brojTelefona);
                                        user.update();

                                        setSuccessText("Izmjene spremljene!");
                                    }
                                    else{
                                        setErroText("Greška prilikom izmjene studenta!");
                                    }
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
        txtBrojIndeksa.setText("");
    }
        
    public void btnNazadOnClick(){
        evidencijastudenti.EvidencijaStudenti.openWindow("Profesor - pregled studenata", btnIzmjeni.getParent(), "ProfesorPregledStudenata");
    }   
    
}

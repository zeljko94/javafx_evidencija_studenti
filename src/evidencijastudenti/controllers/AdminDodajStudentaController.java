
package evidencijastudenti.controllers;

import evidencijastudenti.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class AdminDodajStudentaController implements Initializable {
    @FXML
    Button btnDodaj;
    
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
    TextField txtPhoneNr;
    
    @FXML
    PasswordField txtPassword;
    
    
    @FXML
    PasswordField txtRePassword;
    
    @FXML
    TextField txtBrojIndeksa;
    
    @FXML
    Label lblErrors;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        String brojIndeksa = txtBrojIndeksa.getText();
        
        if(!ime.equals("")){
            if(!prezime.equals("")){
                if(!username.equals("")){
                    if(!password.equals("")){
                        if(!rePassword.equals("")){
                            if(password.equals(rePassword)){
                                if(!email.equals("")){
                                    User user = new User(username, password, email, ime, prezime, "ucenik", brojIndeksa, brojTelefona);
                                    user.insert();
                                    
                                    setSuccessText("Student uspješno unesen!");
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
        evidencijastudenti.EvidencijaStudenti.openWindow("Admin - pregled studenata", btnDodaj.getParent(), "AdminPregledStudenata");
    }
    
}

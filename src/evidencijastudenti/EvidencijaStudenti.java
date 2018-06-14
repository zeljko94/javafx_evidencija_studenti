/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencijastudenti;

import evidencijastudenti.controllers.LoginController;
import evidencijastudenti.models.Kolegij;
import evidencijastudenti.models.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class EvidencijaStudenti extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));;
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.setTitle("Početna");
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(EvidencijaStudenti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

    public static void openWindow(String title, Parent mainWindow, String path){
        //Parent mainWindow = btnPregledProfesora.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(EvidencijaStudenti.class.getClassLoader().getResource("evidencijastudenti/views/" + path + ".fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void GlowOn(ImageView img){
        Glow glow = new Glow();
        glow.setLevel(0.9);
        img.setEffect(glow);
    }
    
    public static void GlowOff(ImageView img){
        img.setEffect(null);
    }
    
}

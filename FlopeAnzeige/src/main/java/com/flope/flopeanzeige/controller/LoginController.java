package com.flope.flopeanzeige.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.flope.clientendpoint.ServerConnection;
import com.flope.converter.POJOtoJSONString;
import com.flope.flopeanzeige.User;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.websocket.DeploymentException;

/**
 * FXML Controller class
 *
 * @author Florian
 */
public class LoginController implements Initializable {
   
    
    //here elements from the FXML are added to the Controller 
   @FXML public Button EinLogbutton, pwresetbutton;
   @FXML public PasswordField Passwordfield;
   @FXML public TextField Usernamefield;
         public Label pwfaillabel;
         User user;
         private String enteredpw;

    public LoginController() throws IOException, SQLException {
        //this.user = new User();
    }
  
   @SuppressWarnings("UnusedAssignment")
   
    public String getenteredusername () throws IOException {
       
       String usernameeingabe = Usernamefield.getText(); 
       
       System.out.println(usernameeingabe);
        return usernameeingabe;
      
           
}
    
    public String getenteredpassword () throws IOException {
        
       String passwordeingabe = null; //Passwordfield.getText();
        System.out.println(passwordeingabe);
        return passwordeingabe;
    }
        
    
    public void incorrectcredentials (){
        
        pwfaillabel.setVisible(true);
        pwresetbutton.setVisible(true);
        System.out.println("Nutzername oder Passwort falsch!");
    }
    
    public void login(ActionEvent event) throws SQLException, IOException, URISyntaxException, DeploymentException{
     /*Über den Boolschen Wert correctcredentials soll mit Hilfe der OnMessageMethode Serverseitig nur in True oder false zurückgegeben werden
     dann sollte die Verdindung wieder gekappt werden. Vorher sollten am besten alle wichtigen Werte, wie Rechte etc. übertragen werden.*/
     Boolean correctcredentials;
       correctcredentials = false;
     user = new User();
     user.username = this.getenteredusername();
     enteredpw = Passwordfield.getText();
     user.setPasswort(enteredpw);
     
     System.out.println(user.username+"loginmethod");
     
     POJOtoJSONString conv = new POJOtoJSONString();
     String loginuser = conv.covertUSERtoJSON(user);
     
     //Login wird zum JSON-String vorne angefügt, um auf dem Server eine Unterscheidung machen zu können, an welche Methode die Daten angefügt werden sollen
     loginuser = "login "+loginuser;
     
     //Senden des JSON-User-Strings an den Server
     
     ServerConnection con = new ServerConnection();
     con.sendJSONStringtoServer(loginuser);
   // return correctcredentials;    
    }
        
        
        //OIlder code from working version without Glassfish Server. This here was a direct connection to the local database
        
  /*     if(user.userexistencetest()== true){
           
            user.setpassword(this.enteredpw);
            System.out.println(enteredpw);
            
            if(user.passwortcheck() == true) { ChangeScreen(event);}
            else {this.incorrectcredentials();}
       }
       
       else { this.incorrectcredentials();}
            
    }*/
    
  //Changes screen to whatever screen you want....more work here it is a change to mainview
    
    public void ChangeScreen(javafx.event.ActionEvent event) throws IOException {
        
        
        
         Parent MainView = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
         
         Scene MainScene = new Scene(MainView);
         
         
         //this line gets the stage information from the main class
         
         Stage Mainwindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
         MainScene.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
         Mainwindow.setScene(MainScene);
         Mainwindow.setFullScreen(true);
         Mainwindow.show();
         
    }
            
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    
    public void exitApplication(ActionEvent event) {
        System.out.println("App has been closed");
        Platform.exit();}

   

    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.flopeanzeige;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Florian
 */
/**
 * FXML Controller class
 * 
 * @author Florian
 */
public class SperrbildController {
    
    
    @FXML public Parent root1;
    @FXML public ImageView sperrbildtest;
    Bild bild;
    
    
    public SperrbildController(){
        
        
    }
    
    
   public void setsperrbildschirm () {
       
     System.out.println(bild.getsperrbild());
     sperrbildtest.setImage(bild.getsperrbild());
   }
   
   //To do für diese Methode muss noch ein Eingangsparameter definiert werden, der die URL der fxml-Datei enthält, um universell verschiedene Sperrbildschirme 
   //kreiren zu können.
   
   public void sperrbildschirmanzeigen() throws FileNotFoundException,IOException { 
       
       
         Parent root;
        root = FXMLLoader.load(getClass().getResource("/fxml/Sperrbildschirm.fxml"));
         Stage stage = new Stage();
         stage.setScene(new Scene(root));
         root.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
         stage.show();
         System.out.println(sperrbildtest);
       //  sperrbildtest.setImage(bild.getsperrbild());
       
       
       
       
        /*    Parent root = FXMLLoader.load(getClass().getResource("/anzeigeprojekt/Sperrbildschirm.fxml"));
            //Parent root = (Parent) fxmlLoader.load();
            Scene sperrscene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(sperrscene);
            stage.initModality(Modality.NONE);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Sperrbild");
            //this.setsperrbildschirm();
            stage.setFullScreen(true);
            stage.show();
          */  
    
        
            
        //catch (Exception e) {e.printStackTrace();  }
    
         System.out.println(bild.getsperrbild());
           
           Image sperrbild1 = bild.getsperrbild();
         System.out.println(sperrbildtest);
           //sperrbildtest.setImage(sperrbild1);
            
        ImageView test = new ImageView();
        test.setFitWidth(100);
        test.setPreserveRatio(true);
        test.setSmooth(true);
        test.setImage(bild.getsperrbild());
        Group root1 = new Group();
        
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(root1);
        HBox box = new HBox();
        root1.getChildren().add(box);
        box.getChildren().add(test);
        stage1.setTitle("ImageView");
        stage1.setWidth(415);
        stage1.setHeight(200);
        stage1.setScene(scene1); 
        stage1.sizeToScene(); 
        stage1.show();
           
        
    
}
   
    public void sperrbildschirmanzeigenscheduler(String fxml, String bildadresse) { 
       
       
         
        try {FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(""+fxml));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Sperrbild");
            stage.setScene(new Scene(root2)); 
            this.setsperrbildschirm();
            stage.setFullScreen(true);
            stage.show();
            
            ImageView test = new ImageView();
        test.setFitWidth(100);
        test.setPreserveRatio(true);
        test.setSmooth(true);
        test.setImage(bild.getsperrbild());
        Group root = new Group();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(root);
        HBox box = new HBox();
        root.getChildren().add(box);
        box.getChildren().add(test);
        stage1.setTitle("Sperrbild");
        stage1.setWidth(415);
        stage1.setHeight(200);
        stage1.setScene(scene1); 
        stage1.sizeToScene(); 
        stage1.show();
           
           
            
            
    }
        
   catch (IOException e) {  }
    
    
}
  public void setBildobject(Bild bildobjekt) {
        
        this.bild = bildobjekt;
    }   
}
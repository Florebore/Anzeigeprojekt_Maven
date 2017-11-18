
package com.flope.flopeanzeige;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Florian
 */
public class MainApplication extends Application {
    
    
    private Stage window;
  
    
    @Override
    public void start(Stage window) throws IOException{
       
     Parent root;
        root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        window.setScene(scene);
        window.show();
        
        
        
      
    }
      
    
    public void setPrimaryStage(Stage window) {
        this.window = window;
    }

    public Stage getPrimaryStage() {
        return this.window;
    }
    
    
    /**
     * @param args command line arguments
    */

    public static void main(String[] args) {
        launch(args);
    }
}

    

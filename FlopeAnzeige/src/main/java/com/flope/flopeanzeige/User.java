/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.flopeanzeige;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Florian
 */
public class User {
    
    
   @FXML public Button Einlogbutton, pwresetbutton;
    
    private String vorname;
    private String nachname;
    private String passwort;
    private String rolle;
    public String username;
    LoginController einlogco = new LoginController();
    Database_Controller datco = new Database_Controller();
 

   public User() throws IOException, SQLException{
       
        
     
        
        
        //passwort = datco.sqlquery("SELECT Ident FROM Database_Anzeige.UserDB WHERE Password = '"+einlogco.getenteredpassword()+"'");
   }
   
   
  
    public Boolean userexistencetest() throws SQLException, IOException {
        
       Boolean userexistence = null;
       String enteredusername;
       String databaseuser = null;
      
   
        try { enteredusername = this.username;
               System.out.println(username+"methoduserexistence");
              databaseuser = datco.getusernamefromdatabase(this.username);
        System.out.println(databaseuser+"returngetuserdatabase");
        if (!"0".equals(databaseuser) && enteredusername.equals(databaseuser)) { 
            
            userexistence = true;
            this.username = databaseuser;
            this.passwort = einlogco.getenteredpassword();
        }
        
        else {  userexistence = false;
		
        }}
	catch ( SQLException e ){}
       System.out.println(userexistence+"userexistence");
        return userexistence;  
        
}

        public Boolean passwortcheck() throws IOException, SQLException{
    
            Boolean passwortcorrect = false;
            String databasepasswort = datco.getpasswordfromdatabase(this.username);
            
            if (databasepasswort.equals(this.passwort) && !"0".equals(databasepasswort))
                
            {passwortcorrect = true;}
            
            else { passwortcorrect = false;
        }
            return passwortcorrect;


}

        public void setpassword(String passwort){
            
            this.passwort = passwort;
        }

        public String getpassword(){ return passwort;}






}
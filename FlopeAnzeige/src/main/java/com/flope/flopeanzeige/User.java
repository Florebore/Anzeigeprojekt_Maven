/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.flopeanzeige;


import com.flope.flopeanzeige.controller.LoginController;
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
    private int UserID;
    LoginController einlogco = new LoginController();
    
 

   public User() throws IOException, SQLException{
       
        
     
        
        
        //passwort = datco.sqlquery("SELECT Ident FROM Database_Anzeige.UserDB WHERE Password = '"+einlogco.getenteredpassword()+"'");
   }
   
   
  
   /* public Boolean userexistencetest() throws SQLException, IOException {
        
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


}*/

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

   
}
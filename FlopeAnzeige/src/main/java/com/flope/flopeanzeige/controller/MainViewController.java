/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.flopeanzeige.controller;

import JSONConverter.JSONtoPOJO;
import com.flope.flopeanzeige.controller.SperrbildController;
import JSONConverter.POJOtoJSON;
import com.flope.flopeanzeige.Bild;
import com.flope.flopeanzeige.Job;
import com.flope.flopeanzeige.Scheduler;
import com.flope.flopeanzeige.Sperrbildschirmjob;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Florian
 */
public class MainViewController implements Initializable {

    //MenuItem ist keine Subclass von Node und kann deswegen nicht auf Methoden z.B. get scene zugreifen, 
    //deswegen muss die Parentnode mit in den Controller aufgenommen werden
    @FXML Button sperrbildschirmsetzenbutton;
    @FXML Button sperrbildschirmanzeigenbutton;
    @FXML Parent root;
    @FXML JFXDatePicker datepickerstart;
    @FXML JFXTimePicker timepickerstart;
    @FXML Label datumuhrzeitlabel;
    @FXML JFXDatePicker datepickerend;
    @FXML JFXTimePicker timepickerend;
    @FXML Label datumuhrzeitlabelend;
    
    @FXML ImageView sperrbildpreviewfield;
    @FXML MenuItem abmeldebutton;
    
    SperrbildController sperrcontroller;
    Bild bild;
    
  
    
  
   public MainViewController() {
        
       
     
       
        }
    
    /**
     * Initializes the controller class.
     * @param event
     * @throws java.io.IOException
     */
    
    //Wechselt wieder zum Einlogbildschirm zurück
    
    public void ChangeScreen(ActionEvent event) throws IOException {
        
         Parent MainView = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
         
         Scene MainScene = new Scene(MainView);
         
         //this line gets the stage information from the main class
         
         Stage Mainwindow = (Stage) root.getScene().getWindow();
         MainScene.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
         Mainwindow.setScene(MainScene);
         Mainwindow.show();
         
    }
    
    
    /* in dieser Methode wird ein Dialogfeld zur Auswahl des Sperrbildes geöffnet ein neues Objekt Bild wird nach Auswahl erzeugt und mit Attributen versehen 
    (z.B. url bzw Path etc. und die Preview wird gesetzt. */
    
    public void setsperrbildschirmurlandimage(ActionEvent event) throws MalformedURLException, URISyntaxException {
        
        
    Sperrbildschirmjob sperrbildjob = new Sperrbildschirmjob();  
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Datei wählen");
        
        // with the extensionfilter different restrictions can be implemented i.e. filetype extensions
        
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.jpeg","*.jpg"));
    Window stagefilechooser;
    stagefilechooser = new Stage();
    bild = new Bild();
  
        
    File selectedimage = fileChooser.showOpenDialog(stagefilechooser);
    bild.setImagefile(selectedimage);
    System.out.println(bild.getImagefile().toPath()+ "bildfiletopath");
    
    // toURI.toURL sind nÃ¶tig fÃ¼r das richtige URL-Format fÃ¼r die Klasse Image
    //TODO URL des Bildes wird als Attribut des Jobs mit der Methode an einen möglichen JOb übergeben
    
    bild.setbildurl(selectedimage.toURI().toURL());
    
    // converts Image to String
    String imageurl = selectedimage.toURI().toURL().toString();
    bild.seturl(imageurl);
    System.out.println(imageurl);
    
    // new Path is created for setting sourcepath in class Bild and selectedimage is converted to Path object and path is stored in Bild object
    
    Path imagepath;
    imagepath = Paths.get(selectedimage.toURI());
    bild.setsourcepath(imagepath);
    System.out.println(bild.getsourcepath());
    
    //URL wird zur Klasse Image konvertiert; Zudem wird in der Klasse Bild das Attribut Sperrbild auf das ausgewählte Bild gesetzt;
    
    Image sperrbild = new Image(imageurl);
    bild.setsperrbild(sperrbild);
    
    // möglicher Listener auf das Attribut Sperrbild mit Hilfe von Lamba-Asudruck und der Klasse Property (siehe Java Dok)
    
  // bild.getimageproperty().addListener(e -> {});
   System.out.println(sperrbildpreviewfield);
   sperrbildpreviewfield.setImage(sperrbild);
   
   if(this.Datetimepickerlabelempty()==true) {datumuhrzeitlabel.setText(this.getdatetimestart().toString());
   sperrbildschirmanzeigenbutton.setDisable(false);}
   else{this.Datetimepickerlabelemptymitbild();}
   
   
    //datumuhrzeitlabel.setText(this.getdatestart().toString());
   System.out.println(sperrbild);
   System.out.println(bild.getimageproperty());
   System.out.println(bild.getsperrbild());
    }
    
    
    public void sperrbildschirmanzeigen(ActionEvent event) throws IOException, URISyntaxException, CloneNotSupportedException { 
    
       sperrcontroller = new SperrbildController();
       Image sperrbildreturn = bild.getsperrbild();
       bild.savepictofolder();
       Sperrbildschirmjob job = new Sperrbildschirmjob();
       System.out.println(job.gettimecreatedMillis());
       System.out.println(job.getcreatedLocalDatetime());
       System.out.println(this.getdatetimestart());
       job.settimestart(this.convertdatetimetomillis(this.getdatetimestart()));
       job.settimeend(this.convertdatetimetomillis(this.getdatetimeend()));
       System.out.println(job.gettimestart());
       Scheduler soleScheduler = null;
       System.out.println(soleScheduler);
       soleScheduler = Scheduler.getInstance();
               
      soleScheduler.addtowaitList(job);
      
      POJOtoJSON conv = new POJOtoJSON();
      JSONtoPOJO conv2 = new JSONtoPOJO();
      
      String JSONString = conv.convertoJSON(job);
      
      System.out.println("Konvertiert"+JSONString);
      
      Job job2 = conv2.convertJSONtoPOJO(JSONString);
      
      System.out.println(job2);
      System.out.println(job2.gettimestart());
      
    
              
       
      
       
        
        System.out.println(sperrbildreturn+"uebergabe");
        sperrcontroller.setBildobject(bild);
       
        sperrcontroller.sperrbildschirmanzeigen();
        
   
    
                           }
    
    //Methoden zum Auslesen der Startzeit
    
    public LocalDate getdatestart(){
       
        /*startdate and starttime is taken from the Date- and Timepicker. If the values are null the show-button is disabled */
        LocalDate startdate = null;
        
        startdate = datepickerstart.getValue();
        System.out.println(startdate);
        
       
        
        return startdate;
    }
    
    public LocalTime gettimestart(){
        
        LocalTime starttime = null;
        
       starttime = timepickerstart.getValue();
       System.out.println(starttime);
       return starttime;
    }
    
    
     public LocalDateTime getdatetimestart(){
      
        LocalDateTime datetimestart = null;
        
       LocalDate datestart = this.getdatestart();
       LocalTime timestart = this.gettimestart();
       if(datestart != null && timestart != null) {
       //date und time werden zu DateTime umgewandelt
       
       datetimestart = datestart.atTime(timestart);}
       
       return datetimestart;
    }
    
    //Methoden zum Auslesen von Endzeit
     
    public LocalDate getdateend(){
    
        LocalDate enddate = null;
        enddate = datepickerend.getValue();
    
    return enddate;}
    
    public LocalTime gettimeend(){
    
        LocalTime endtime = null;
        endtime = timepickerend.getValue();
    
    return endtime;}
   
    
    public LocalDateTime getdatetimeend(){
      
        LocalDateTime datetimeend = null;
        LocalDate dateend = this.getdateend();
        LocalTime timeend = this.gettimestart();
        
        if(dateend != null && timeend != null){
            datetimeend = dateend.atTime(timeend);
        }
      return datetimeend;
    }
    
   
    //Prüfung, ob die korrekte Zeit eingegeben wird. z.B. Zeit schon abgelaufen etc.
    
    public Boolean Datetimepickerlabelempty(){
       
        
        //Könnte man auch mit boolschen Variablen lösen, keine Ahnung was besser ist. Prüft alle Eventualitäten einer Falscheingabe ab oder auch kein Bild ausgeählt
        Boolean correctDatetime = false;
        if (datepickerstart.getValue() == null && timepickerstart.getValue() == null) {sperrbildschirmanzeigenbutton.setDisable(true);
    }
        else if (datepickerstart.getValue() == null && timepickerstart.getValue() != null) {datumuhrzeitlabel.setText("Bitte Datum auswählen!");
        sperrbildschirmanzeigenbutton.setDisable(true); }
        else if (timepickerstart.getValue() == null && datepickerstart.getValue() != null) {datumuhrzeitlabel.setText("Bitte Uhrzeit auswählen!");
        sperrbildschirmanzeigenbutton.setDisable(true);}
           
        else if ((datepickerstart.getValue() != null && timepickerstart.getValue() != null) && (this.getdatetimestart().isBefore(LocalDateTime.now())))
        {datumuhrzeitlabel.setText("Datum und Uhrzeit sind bereits vergangen!");
        sperrbildschirmanzeigenbutton.setDisable(true);
        } 
        else if ((datepickerstart.getValue() != null && timepickerstart.getValue() != null) && (this.getdatetimestart().isAfter(LocalDateTime.now()))){
        
       datumuhrzeitlabel.setText("Bitte Bild auswählen!");
       sperrbildschirmanzeigenbutton.setDisable(true);
               correctDatetime = true;
        
    } 
               return correctDatetime; 
                 }
    
    public Boolean Datetimepickerlabelemptymitbild(){
        
        Boolean correctDatetime1 = false;
        if (datepickerstart.getValue() == null && timepickerstart.getValue() == null) {sperrbildschirmanzeigenbutton.setDisable(true);
    }
        else if (datepickerstart.getValue() == null && timepickerstart.getValue() != null) {datumuhrzeitlabel.setText("Bitte Datum auswählen!");
        sperrbildschirmanzeigenbutton.setDisable(true); }
        else if (timepickerstart.getValue() == null && datepickerstart.getValue() != null) {datumuhrzeitlabel.setText("Bitte Uhrzeit auswählen!");
        sperrbildschirmanzeigenbutton.setDisable(true);}
           
        else if ((datepickerstart.getValue() != null && timepickerstart.getValue() != null) && (this.getdatetimestart().isBefore(LocalDateTime.now())))
        {datumuhrzeitlabel.setText("Datum und Uhrzeit sind bereits vergangen!");
        sperrbildschirmanzeigenbutton.setDisable(true);
        } 
        else if ((datepickerstart.getValue() != null && timepickerstart.getValue() != null) && (this.getdatetimestart().isAfter(LocalDateTime.now())) &&(bild.getsperrbild() != null)){
        
       datumuhrzeitlabel.setText(this.getdatetimestart().toString());
       sperrbildschirmanzeigenbutton.setDisable(false);
               correctDatetime1 = true;
        
    } 
               return correctDatetime1; 
                 }
    
    public Boolean Datetimepickerlabelendempty(){
       
        
        //Könnte man auch mit boolschen Variablen lösen, keine Ahnung was besser ist. Prüft alle Eventualitäten einer Falscheingabe ab oder auch kein Bild ausgeählt
        Boolean correctDatetimeend = false;
        if (datepickerend.getValue() == null && timepickerend.getValue() == null) {sperrbildschirmanzeigenbutton.setDisable(true);
    }
        else if (datepickerend.getValue() == null && timepickerend.getValue() != null) {datumuhrzeitlabelend.setText("Bitte Datum auswählen!");
        sperrbildschirmanzeigenbutton.setDisable(true); }
        else if (timepickerend.getValue() == null && datepickerend.getValue() != null) {datumuhrzeitlabelend.setText("Bitte Uhrzeit auswählen!");
        sperrbildschirmanzeigenbutton.setDisable(true);}
           
        else if ((datepickerend.getValue() != null && timepickerend.getValue() != null) && (this.getdatetimeend().isBefore(LocalDateTime.now())))
        {datumuhrzeitlabelend.setText("Datum und Uhrzeit sind bereits vergangen!");
        sperrbildschirmanzeigenbutton.setDisable(true);
        }
        
        else if ((datepickerend.getValue() != null && timepickerend.getValue() != null) && (this.getdatetimeend().isAfter(LocalDateTime.now()))&&(this.getdatetimeend().isBefore(this.getdatetimestart()))){
        
       datumuhrzeitlabelend.setText("Endzeit ist vor Startzeit!");
       sperrbildschirmanzeigenbutton.setDisable(true);
               correctDatetimeend = true;
        
    }
        
        else if ((datepickerend.getValue() != null && timepickerend.getValue() != null) && (this.getdatetimeend().isAfter(LocalDateTime.now()))&&(this.getdatetimeend().isAfter(this.getdatetimestart()))){
        
       datumuhrzeitlabelend.setText("Bitte Bild auswählen!");
       sperrbildschirmanzeigenbutton.setDisable(true);
               correctDatetimeend = true;
        
    } 
               return correctDatetimeend; 
                 }
    
    public Boolean Datetimepickerlabelendemptymitbild(){
        
        //Könnte man auch mit boolschen Variablen lösen, keine Ahnung was besser ist. Prüft alle Eventualitäten einer Falscheingabe ab oder auch kein Bild ausgeählt
        Boolean correctDatetimeend1 = false;
        if (datepickerend.getValue() == null && timepickerend.getValue() == null) {sperrbildschirmanzeigenbutton.setDisable(true);
    }
        else if (datepickerend.getValue() == null && timepickerend.getValue() != null) {datumuhrzeitlabelend.setText("Bitte Datum auswählen!");
        sperrbildschirmanzeigenbutton.setDisable(true); }
        else if (timepickerend.getValue() == null && datepickerend.getValue() != null) {datumuhrzeitlabelend.setText("Bitte Uhrzeit auswählen!");
        sperrbildschirmanzeigenbutton.setDisable(true);}
           
        else if ((datepickerend.getValue() != null && timepickerend.getValue() != null) && (this.getdatetimeend().isBefore(LocalDateTime.now())))
        {datumuhrzeitlabelend.setText("Datum und Uhrzeit sind bereits vergangen!");
        sperrbildschirmanzeigenbutton.setDisable(true);
        } 
        else if ((datepickerend.getValue() != null && timepickerend.getValue() != null) && (this.getdatetimeend().isAfter(LocalDateTime.now()))&&(this.getdatetimeend().isBefore(this.getdatetimestart()))){
        
       datumuhrzeitlabelend.setText("Endzeit ist vor Startzeit!");
       sperrbildschirmanzeigenbutton.setDisable(true);
               correctDatetimeend1 = true;
        
    }
        
        else if ((datepickerend.getValue() != null && timepickerend.getValue() != null) && (this.getdatetimeend().isAfter(LocalDateTime.now()))&&(this.getdatetimeend().isAfter(this.getdatetimestart()))){
        
       datumuhrzeitlabelend.setText(this.gettimeend().toString());
       sperrbildschirmanzeigenbutton.setDisable(true);
               correctDatetimeend1 = true;
        
    } 
    
               return correctDatetimeend1; 
                 }
              
    
    // Converts LocalDateTime to long milliseconds
    public long convertdatetimetomillis(LocalDateTime datetime){
        
    LocalDateTime localDT = datetime;
    long localDTInMilli = localDT.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      return localDTInMilli;
    }
    
    //Converts long milliseconds to LocalDateTime
    
    public LocalDateTime convertmillistoLocalDatetime(long millis){
    
    long milliseconds = millis;
    LocalDateTime cvDate = Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDateTime();

    return cvDate;}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Scheduler befindet sich normalerweise auf einem Server Hauptprogramm
      
        Task<Void> databasetask = new Task() {
            @Override
            protected Object call() throws Exception {
                
           //Reading data from the database and writing it to the program i.e. user data
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        // TODO EInlesen aller relevanten Daten aus der Datenbank bezüglich User z.B. Admin oder nicht etc...
        
        
        
        
    }    
    
}
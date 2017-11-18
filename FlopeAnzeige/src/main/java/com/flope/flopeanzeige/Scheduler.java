/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.flopeanzeige;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
*
 * @author Florian + Peter
 */

/*
Funktionalität der Klasse Scheduler
1)Scheduler wird aufgerufen, sobald ein neuer Job erstellt wird 
a)Job wird in WarteListe einsortiert
b)Check im Hintergrund prüft ob Job "dran" ist und falls ja startet ihn und ruft 2 auf
2)Abgearbeitete Jobs
a)Job wird in ExecutedListe einsortiert
b) in DB wird ExecutedListe abgespeichert
c) es wird geprüft ob ein recurring-Tag enthalten ist, falls ja ruft er 3 auf
3)Recurring Jobs
a) er prüft, ob "Endzeit" erreicht ist, falls ja, wird Job von Liste gelöscht 
b) falls nein wird starttime auf neuen Wert gesetzt
c) Aufruf von 1a 
*/

/*Klasse Scheduler wird hier als Singleton implementiert, um sicherzustellen, dass nur ein Scheduler 
instanziert werden kann, ToDo threadsichere Implementierung des Schedulers

*/


/*another way would be to use enums
enum Scheduler {
INSTANCE;

}

+*/
public class Scheduler implements Comparable<Job> {  
  
 private final LinkedList<Job> Jobswaitforexecution = new LinkedList<>();
 private final LinkedList<Job> Jobsrunning = new LinkedList<>();
 long checkIntervall = 60000; //alle 60 Sekunden sollte gecheckt werden, da Zeit vom User auch minutenweise angegeben werden kann

    //volatile makes sure that double check locking works and no half initialised object are accessed by threads
    
    private static volatile Scheduler soleScheduler = null;
  
    
    //Todo threadsicherheit des Singleton_patterns gewährleisten
    
    private Scheduler() throws CloneNotSupportedException{
        
        //Runtimeexception is created here to stop Reflection to create another instance
        
    if(soleScheduler != null){throw new RuntimeException("Cannot create Scheduler, use getInstance()...");}
    //creation
    System.out.println("Scheduler wird erzeugt...");
    
            
            
    
    }
  
   /*lazy creation of a Singleton / synchronized is added to the getInstance()-method to prevent multiple threads to create more Scheduler
    When a thread calls a synchronized method, another thread has to wait until the first one is fininshed....to boost performance the synchronized block is moved 
    to the if statement execution block*/    
  public static Scheduler getInstance() throws CloneNotSupportedException{
    // Check 1  
    if (soleScheduler== null){
          synchronized(Scheduler.class){
    //Double Check locking: Check 2
          if(soleScheduler == null){
          soleScheduler = new Scheduler();}}
      }
      return soleScheduler;
  }    
  
  /*
   * zum Hinzufügen zu einer sortierten linkedList könnte auch besser der listIterator benutzt werden
   * (https://stackoverflow.com/questions/18144820/inserting-into-sorted-linkedlist-java) - da aber Kosten keine Rolle spielen,
   * ist es erstmal nicht notwendig
   */
    
  public void addtowaitList(Job e){
   
   
  

   //ToDO funktioniert nur beim ersten Mal da timestart noch nicht festgelegt ist. Muss noch im MainViewController festgelegt werden

      
          
          if (Jobswaitforexecution.isEmpty()) {
              Jobswaitforexecution.add(e);
          } else if (Jobswaitforexecution.get(0).gettimestart () > e.gettimestart ()) {
              Jobswaitforexecution.add(0, e);
          } else if (Jobswaitforexecution.get(Jobswaitforexecution.size() - 1).gettimestart () < e.gettimestart ()) {
              Jobswaitforexecution.add(Jobswaitforexecution.size(), e);
          } else {
              int i = 1;
              while (Jobswaitforexecution.get(i).gettimestart () < e.gettimestart ()) {
                  i++;
              }
              Jobswaitforexecution.add(i, e);
          }
          
      }
   
   
  

 
  

  
  public void addtorunList(Job e){
  
  
  Jobsrunning.add(e);
  
  
  }

/*
 * 
 * die Methode backgroundTask() prüft, ob die Zeit für eine Anzeige gekommen ist
 * über die Methode vergleich(), wenn die Zeit da ist, zeigt er den Job an
 * Platform.runLater() wird gebraucht um eine JavaFX-Veränderung aus dem Hintergrund zu starten,
 * da JavaFX-Elemente eigentlich nur vom Main-JavaFX-Thread gestartet werden können
 */
  
  public void backgroundTask() 
  {
     
     
      
      while (true) {
          try {
              Platform.runLater(() -> {
                  boolean comparison = vergleich();
                  if (comparison == true){SperrbildController sperrbildschirm = new SperrbildController();
                      try {
                          sperrbildschirm.sperrbildschirmanzeigen();
                      } catch (IOException ex) {
                          Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                      }
}
              });

              Thread.sleep(checkIntervall);
          }
          catch (InterruptedException e) {}
      }
      
     

      
          

          }
  
  
  //ToDo vergleich() mit einem Vergleich der aktuellen zeit mit der ersten Job.getstarttime() in der LinkedList coden
  //Methode compareTo (Job o) mit verwursten, sonst meckert er, da implemts comparable nicht verwendet wird
    
  public boolean vergleich(){
      boolean comparison = false;
      long nowms = System.currentTimeMillis();
      long nextJobStartms = Jobswaitforexecution.peekFirst ().gettimestart ();
      
      if (nextJobStartms-checkIntervall<=nowms &&nowms<= nextJobStartms+checkIntervall)
      {comparison = true;}
      return comparison;   
  }
  
  @ Override
  public int compareTo ( Job o )
  {
      // TODO Auto-generated method stub
      return 0;
  }
      
  
    
              
         
  
  //Methode threading() erstellt einen Backgroundthread
  
  public void  threading()
  {
      // Create a Runnable
      Runnable task = new Runnable()
      {
          @Override
          public void run()
          {
              backgroundTask();
          }
      };

      // Run the task in a background thread
      Thread backgroundThread = new Thread(task);
      // Terminate the running thread if the application exits
      backgroundThread.setDaemon(true);
      // Start the thread
      backgroundThread.start();
  }}
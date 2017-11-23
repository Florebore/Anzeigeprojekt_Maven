/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.flopeanzeige;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

/**
 *
 * @author Florian
 */
public class Sperrbildschirmjob extends Job implements Serializable {
    
    private String fxmllocation;
    private String imagelocation;
    private String displaynumber;

    
    public Sperrbildschirmjob(){
    
    }
    
    


    public Sperrbildschirmjob(String fxmllocation, String imagelocation, String displaynumber, long timecreatedMillis, LocalDateTime timecreatedLocalDatetime,long timestart, long timeend, Boolean beendet, String Wiederholung) throws URISyntaxException {
        
        super(timecreatedMillis, timecreatedLocalDatetime, timestart, timeend, beendet);
        this.fxmllocation = fxmllocation;
        this.imagelocation = imagelocation;
        this.displaynumber = displaynumber;
        this.Wiederholung = Wiederholung;
    }
    


public void setfxmllocation(String fxmlloc){
        
this.fxmllocation = fxmlloc;        

}

public String getfxmllocation(){
return this.fxmllocation;}

public void setdisplaynumber(String number){
    
    this.displaynumber = number;
   
}

public String getdisplaynumber(){
return this.displaynumber;
}

public String getimagelocation(){
    return this.imagelocation;
}

public void setimagelocation(String imagelocation){
this.imagelocation = imagelocation;}
        
}
;
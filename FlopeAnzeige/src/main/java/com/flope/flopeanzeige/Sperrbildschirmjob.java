/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.flopeanzeige;

import java.io.Serializable;
import java.net.URISyntaxException;

/**
 *
 * @author Florian
 */
public class Sperrbildschirmjob extends Job implements Serializable {
    
    private String fxmllocation;
    private String imagelocation;
    private String displaynumber;
    
public Sperrbildschirmjob() throws URISyntaxException{}
    


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
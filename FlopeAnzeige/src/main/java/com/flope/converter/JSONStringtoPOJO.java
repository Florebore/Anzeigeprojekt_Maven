/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flope.flopeanzeige.Sperrbildschirmjob;
import com.flope.flopeanzeige.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
//TODO eventuell ein Singleton aus den beiden Convertern machen, damit man ihn aus jeder Klasse aufrufen kann
public class JSONStringtoPOJO {
    
    ObjectMapper mapper = new ObjectMapper();
    
    public Sperrbildschirmjob convertJSONStringtoPOJOSperrbildschirmjob(String JSON) {
        
     Sperrbildschirmjob job = null;
        try {
            job = mapper.readValue(JSON,Sperrbildschirmjob.class);
        } catch (IOException ex) {
            Logger.getLogger(JSONStringtoPOJO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return job;
    
    }
    
    public User convertJSONStringtoPOJOUSER(String JSON){
        
        User user = null;
        
        try {
            user = mapper.readValue(JSON,User.class);
        }
        catch (IOException ex) {
            Logger.getLogger(JSONStringtoPOJO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    
}

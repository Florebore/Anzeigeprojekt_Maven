/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flope.flopeanzeige.Job;
import com.flope.flopeanzeige.Sperrbildschirmjob;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class JSONtoPOJO {
    
    ObjectMapper mapper = new ObjectMapper();
    
    public Sperrbildschirmjob convertJSONtoPOJO(String JSON) {
        
     Sperrbildschirmjob job = null;
        try {
            job = mapper.readValue(JSON,Sperrbildschirmjob.class);
        } catch (IOException ex) {
            Logger.getLogger(JSONtoPOJO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return job;
    
    }
    
}

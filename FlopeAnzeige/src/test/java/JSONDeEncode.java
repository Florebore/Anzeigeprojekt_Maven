/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import JSONConverter.JSONtoPOJO;
import JSONConverter.POJOtoJSON;
import com.flope.flopeanzeige.Job;
import com.flope.flopeanzeige.Scheduler;
import com.flope.flopeanzeige.Sperrbildschirmjob;
import java.net.URISyntaxException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Florian
 */
public class JSONDeEncode {
    
    public Sperrbildschirmjob JSONDeEncode() throws URISyntaxException, CloneNotSupportedException {
        
       Sperrbildschirmjob job = new Sperrbildschirmjob();
       System.out.println(job.gettimecreatedMillis());
//       System.out.println(job.getcreatedLocalDatetime());
       System.out.println(job.gettimestart());
       Scheduler soleScheduler = null;
       System.out.println(soleScheduler);
       soleScheduler = Scheduler.getInstance();
               
      soleScheduler.addtowaitList(job);
      
      POJOtoJSON conv = new POJOtoJSON();
      JSONtoPOJO conv2 = new JSONtoPOJO();
      
      String JSONString = conv.convertoJSON(job);
      
      System.out.println("Konvertiert"+JSONString);
      
      Sperrbildschirmjob job2 = conv2.convertJSONtoPOJO(JSONString);
      
      System.out.println(job2);
      
      return job2;
      
    }
    
}

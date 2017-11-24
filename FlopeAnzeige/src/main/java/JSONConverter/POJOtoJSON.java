
package JSONConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.flope.flopeanzeige.Job;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class POJOtoJSON {
    
    ObjectMapper mapper = new ObjectMapper().enable((SerializationFeature.INDENT_OUTPUT));
    
    public String convertoJSON (Job job){
        
        
        
        
       String JSONString = null;
        try {
            JSONString = mapper.writeValueAsString(job);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(POJOtoJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    return JSONString;
    }
    
}
//Convert to timestamp use following code 

//mapper.registerModule(new JavaTimeModule());
// mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
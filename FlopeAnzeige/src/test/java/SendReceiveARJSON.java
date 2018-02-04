/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.flope.clientendpoint.ServerConnection;
import com.flope.converter.POJOtoJSONString;
import com.flope.flopeanzeige.User;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.websocket.DeploymentException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Florian
 */
public class SendReceiveARJSON {
    
    public SendReceiveARJSON() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
       @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
   @Test
   public void SendReceiveJSON() throws IOException, SQLException, URISyntaxException, DeploymentException{
       
       String userparcel = null; 
       
       User user = new User();
       user.setPasswort("test");
       user.setUsername("test");
       user.setUserID(1);
       POJOtoJSONString psc = new POJOtoJSONString();
       userparcel = psc.covertUSERtoJSON(user);
       System.out.println(userparcel);
        ServerConnection con = new ServerConnection();
        con.sendJSONStringtoServer(userparcel);
       
       
   }
   

    


   
   
   }

 
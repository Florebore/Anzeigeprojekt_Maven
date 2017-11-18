/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.flope.clientendpoint.WebserverEndpoint;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import org.junit.Test;


/**
 *
 * @author Florian
 */
    

public class TestConnection {
    
    @Test
    public void sendMessage() throws URISyntaxException, DeploymentException, IOException{
        
        WebserverEndpoint endpoint = new WebserverEndpoint();
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(endpoint,new URI("ws://localhost:8080/Flope_WebSocketServer/endpoint"));
        endpoint.sendMessage("test");
    }
    
    
}
    


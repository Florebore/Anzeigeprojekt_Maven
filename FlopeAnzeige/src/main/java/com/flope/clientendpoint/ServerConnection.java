/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.clientendpoint;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author Florian
 */
public class ServerConnection {
        
        private final URI uri;
        private final String Stringuri;
        private final WebSocketEndpointClient wsec;
     
       
    
        public ServerConnection() throws URISyntaxException{
            
            this.Stringuri = "ws://localhost:8080/Anzeigeprojekt_Server/websocket";
            this.uri = new URI(Stringuri);
            wsec = null;
        
        
        }
        
        public ServerConnection(URI uri,WebSocketEndpointClient endpoint) throws URISyntaxException
        { this.Stringuri = "ws://localhost:8080/Anzeigeprojekt_Server/websocket";
            this.uri = new URI(Stringuri);
        
          this.wsec = endpoint;
        }
    
    
        public void sendJSONStringtoServer (String JSONString) throws DeploymentException, IOException, URISyntaxException{ 
            
            
            try {
            // open websocket
            final WebSocketEndpointClient clientEndPoint = new WebSocketEndpointClient(new URI(Stringuri));

            // add listener
          //  clientEndPoint.addMessageHandler((String message) -> {
          //      System.out.println(message);
           // });

            // send message to websocket
            clientEndPoint.sendMessage(JSONString);

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        }catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        }
        }
}
           



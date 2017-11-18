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
import javax.websocket.WebSocketContainer;


/**
 *
 * @author Florian
 */
public class ConnectionTest {
    
    
    public void sendMessage() throws URISyntaxException, DeploymentException, IOException{
        
        WebserverEndpoint endpoint = new WebserverEndpoint();
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(endpoint,new URI("ws://localhost:8080/Flope_WebSocketServer/endpoint"));
        endpoint.sendMessage("test");
    }
    
    
}

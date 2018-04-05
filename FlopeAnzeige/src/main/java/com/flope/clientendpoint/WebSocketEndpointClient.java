/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.clientendpoint;

import com.flope.flopeanzeige.User;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author Florian
 */
@ClientEndpoint
public class WebSocketEndpointClient {
    
    private Session session;
    
    
   public WebSocketEndpointClient (URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            Session connectToServer;
            connectToServer = container.connectToServer(this, endpointURI);
        } catch (IOException | DeploymentException e) {
            throw new RuntimeException(e);
        }
    }
    
   @OnOpen
   
   public void OnOpen(Session session) throws IOException {this.session = session;
       System.out.println(session.getId());
  // this.session.getBasicRemote().sendText(UserID);
   
   }
   
    @OnMessage
    public void onMessage(String message) {
     //   if (this.messageHandler != null) {
       //     this.messageHandler.handleMessage(message);
            System.out.println(message);
       
        }
    
    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

    /**
     * Message handler.
     *
     * @author Jiji_Sasidharan
     */
   }



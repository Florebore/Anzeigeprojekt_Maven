/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.clientendpoint;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
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
   // private MessageHandler messageHandler;
     
    
   public WebSocketEndpointClient (URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
   @OnOpen
   
   public void OnOpen(Session session) {this.session = session;}
   
    @OnMessage
    public void onMessage(String message) {
     //   if (this.messageHandler != null) {
       //     this.messageHandler.handleMessage(message);
            System.out.println(message);
       
        }
    

    /**
     * register message handler
     *
     * @param msgHandler
     */
   // public void addMessageHandler(MessageHandler msgHandler) {
     //   this.messageHandler = msgHandler;
   // }

    /**
     * Send a message.
     *
     * @param message
     */
    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

    /**
     * Message handler.
     *
     * @author Jiji_Sasidharan
     */
   }



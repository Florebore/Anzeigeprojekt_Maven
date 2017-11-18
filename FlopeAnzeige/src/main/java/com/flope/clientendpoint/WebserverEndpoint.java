/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.clientendpoint;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

/**
 *
 * @author Florian
 */
public class WebserverEndpoint extends Endpoint {
    
    private Session session;
    
    @Override
    public void onOpen(Session sn, EndpointConfig ec){this.session = sn;
    
    this.session.addMessageHandler((MessageHandler.Whole<String>) (String t) -> {
        System.out.println("Message =" + t);
    });}
            
            
    public void sendMessage(String message) throws IOException{
        
        try {this.session.getBasicRemote().sendText(message);}
   catch (IOException ex)
   {Logger.getLogger(WebserverEndpoint.class.getName()).log(Level.SEVERE,null,ex);}
    }
}

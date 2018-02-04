/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.clientendpoint;

/**
 *
 * @author Florian
 */
public class IncomingMessagesHandler {
    

    public void incometest(String IM){
        
       String IMcon[];
        IMcon = IM.split(" ", 2);
        
        switch(IMcon[0]){
            
            case "login": System.out.println("login");
            case "jobanfrage":
            
        }
    }


    
}

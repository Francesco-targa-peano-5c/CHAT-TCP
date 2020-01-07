/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcp;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Francesco Targa
 */
public class Servertcp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         

        int portaserver = 2000; 
        
        try{
            
            ServerSocket ss = new ServerSocket(portaserver);
            System.out.println("Server messo in ascolto sulla porta predefinita");

            while(true){
                
                socketWorker w;
                try {
                   
                    Socket newSocket = ss.accept();
                    
                    w = new socketWorker(newSocket);

                  
                    Thread t = new Thread(w);
                    
                    t.start();
                } catch (Exception e) {
                    System.out.println("Connessione NON riuscita con client: ");
                    System.exit(-1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error! Porta: " + portaserver + " non disponibile");
            System.exit(-1);
        }

        
    
    }
    
}


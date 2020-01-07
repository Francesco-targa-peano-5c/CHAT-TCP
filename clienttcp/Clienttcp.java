/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Francesco Targa
 */
public class Clienttcp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hostName = "localhost";
	int portNumber = 2000;
	try {
            
            InetAddress address = InetAddress.getByName(hostName);
			
            // creazione socket 
            Socket clientSocket = new Socket(address, portNumber);
		
            // visualizza istruzioni
            
			
            // connessione concorrente al socket per ricevere i dati da Server
            Ascoltatore l;
            try {
                l = new Ascoltatore(clientSocket);
                Thread t = new Thread(l);
                t.start();
            } catch (Exception e) { System.out.println("Connessione NON riuscita con server: "); }
		
            // connessione al socket (in uscita client --> server)
            PrintWriter out =  new PrintWriter(clientSocket.getOutputStream(), true);
			
            // connessione allo StdIn per inserire il testo dalla linea di comando
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            
            //leggi da linea di comando il testo da spedire al Server
            System.out.print(">"); //visualizza il prompt
            while ((userInput = stdIn.readLine()) != null) {
            	// scrittura del messaggio da spedire nel socket 
		out.println(userInput);
                System.out.println("Messaggio spedito al server: " + userInput);
                System.out.print(">"); //visualizza il prompt
            }
            // chiusura socket
            clientSocket.close();
            System.out.println("connessione terminata!");
	}
        catch (IOException e) { System.out.println("Connessione terminata dal server: "); e.printStackTrace(); }
    }
    }
    


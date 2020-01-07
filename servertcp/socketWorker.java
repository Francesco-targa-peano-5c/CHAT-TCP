/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcp;

import java.net.*;
import java.io.*;

/**
 *
 * @author Francesco Targa
 */
public class socketWorker implements Runnable {

    private static final MessageManager gestoreMessaggi = new MessageManager();
    private Socket client;
    private PrintWriter out = null;

    socketWorker(Socket client) {
        this.client = client;
        gestoreMessaggi.addClient(this);
        System.out.println("Connesso con: " + client);
    }

    public void messaggioReceived(String m) {
        this.gestoreMessaggi.sendNewMessaggio(m);
    }

    public void sendMessaggio(String messaggio) {

        out.println("Server: " + messaggio);

    }

    public void run() {

        BufferedReader in = null;
        try {

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Errore: in|out fallito");
            System.exit(-1);
        }

        String linea = "";
        int clientPort = client.getPort();
        while (linea != null) {
            try {

                linea = in.readLine();

                messaggioReceived(linea);

                System.out.println(clientPort + ">> " + linea);
            } catch (IOException e) {
                System.out.println("lettura da socket fallito");
                System.exit(-1);
            }
        }
        try {
            client.close();
            System.out.println("connessione con client: " + client + " terminata!");
        } catch (IOException e) {
            System.out.println("Errore connessione con client: " + client);
        }
    }
}

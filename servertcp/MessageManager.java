/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcp;

import java.util.ArrayList;

/**
 *
 * @author Francesco Targa
 */
public class MessageManager {
   
    private String ultimomex;
    
    private ArrayList<socketWorker> listaw = new ArrayList<>();
    
    void addClient(socketWorker worker) {
        this.listaw.add(worker);
    }
    
    
    void removeClient(socketWorker worker) {
        this.listaw.remove(worker);
    }
    
   
    synchronized void sendNewMessaggio(String m) {
        
        this.ultimomex = m;
        
        for (socketWorker worker: this.listaw) {
            worker.sendMessaggio(this.ultimomex);
        }
    }
    
}


interface InviaMessaggio {
    
    public void sendMessaggio(String m);
}


interface RiceviMessaggio { 

    
    public void messaggioReceived(String m);
}

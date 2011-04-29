package br.ufal.ic.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Marcos Paulo
 */
public class Server implements Runnable {
    
    private ServerSocket server;
    
    private ExecutorService executor;
    
    public Server(int port) {

        try {
            server = new ServerSocket(port);
            
            executor = Executors.newFixedThreadPool(100);
            
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public void run() {
        
    }
    
    
    
}

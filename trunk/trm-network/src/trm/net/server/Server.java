package trm.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
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
        
        while(true) {
            try {
                Socket socket = server.accept();
                executor.submit(new ServerTask(socket));
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    
    
}

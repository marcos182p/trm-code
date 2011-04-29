package br.ufal.ic.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Marcos Paulo
 */
public class UserNet {
    
    private UserInf inf;
    
    private Socket socket;
    
    private Sender sender;
    
    public UserNet(UserInf inf, Socket socket) throws IOException {
       
        this.sender = new SenderImpl(new PrintWriter(socket.getOutputStream()));
        this.inf = inf;
        this.socket = socket;
    }
    
    public void sendMessage(Message message) throws IOException {
        sender.send(message);
    }
    
}

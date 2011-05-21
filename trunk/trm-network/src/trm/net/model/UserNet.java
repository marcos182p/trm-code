package trm.net.model;

import trm.net.model.protocol.MessageClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import trm.net.util.MessageFactory;
import trm.net.util.MessageFactoryImpl;

/**
 *
 */
public class UserNet {
    
    private UserInf inf;
    private Socket socket;
    
    private Sender sender;
    private MessageFactory messageFactory;
    
    public UserNet(UserInf inf, Socket socket) throws IOException {
       
        this.sender = new SenderImpl(new PrintWriter(socket.getOutputStream()));
        this.messageFactory = new MessageFactoryImpl();
        this.inf = inf;
        this.socket = socket;
    }
    
    public void sendMessage(MessageClient message) throws IOException {
        sender.send(messageFactory.generateMessageClient(message));
    }
    
}

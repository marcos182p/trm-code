package trm.net.model;

import trm.net.model.protocol.RequestClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import trm.net.util.ParserMessage;
import trm.net.util.ParserMessageImpl;

/**
 *
 */
public class UserNet {
    
    private UserInf inf;
    private Socket socket;
    
    private Sender sender;
    private ParserMessage messageFactory;
    
    public UserNet(UserInf inf, Socket socket) throws IOException {
       
        this.sender = new SenderImpl(new PrintWriter(socket.getOutputStream()));
        this.messageFactory = new ParserMessageImpl();
        this.inf = inf;
        this.socket = socket;
    }
    
    public void sendMessage(RequestClient message) throws IOException {
        sender.send(messageFactory.convertMessageRequest(message));
    }
    
}

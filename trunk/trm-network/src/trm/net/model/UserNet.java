package trm.net.model;

import trm.net.model.protocol.RequestClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import trm.net.util.SenderFactory;

/**
 *
 */
public class UserNet {
    
    private UserInf inf;
    private Socket socket;
    
    private Sender<RequestClient> sender;
    
    public UserNet(UserInf inf, Socket socket) throws IOException {
       
        this.sender = SenderFactory.createSenderClient(new PrintWriter(socket.getOutputStream()));
        this.inf = inf;
        this.socket = socket;
    }

    public void sendMessage(RequestClient message) throws IOException {
        sender.send(message);
    }

}

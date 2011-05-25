package trm.net.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import trm.net.model.Sender;
import trm.net.model.protocol.RequestClient;
import trm.net.util.SenderFactory;

/**
 *
 * @author Marcos Paulo
 */
public class ClientTask implements Runnable {

    private String nickName;
    private Socket socket;
    private Sender<RequestClient> sender;

    public ClientTask(String nickName, Socket socket) throws IOException {
        this.socket = socket;
        this.nickName = nickName;
        this.sender = SenderFactory.createSenderClient(new PrintWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        
        
    }

    public void sendRequest(RequestClient request) throws IOException {
        sender.send(request);
    }
}

package trm.net.client;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.net.Socket;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Random;
import trm.core.lps.StackUtil;
import trm.net.model.InvalidMessageException;
import trm.net.model.Receiver;
import trm.net.model.Sender;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;
import trm.net.model.protocol.ResponseServer;
import static trm.net.util.ReceiverFactory.*;
import static trm.net.util.SenderFactory.*;
import static java.util.logging.Level.*;
import static java.util.logging.Logger.*;

/**
 * @author TRM
 * @version 0.99
 */
public class ClientTask implements Runnable {
    
    private long nextId;
    
    private String nickName;
    
    private Socket socket;
    
    private Sender<RequestClient> sender;
    private Receiver<ResponseServer> receiver;
    
    private Map<RequestType, List<Listener>> listenersMap;
    
    private InteractionListener stackManager;
    
    public ClientTask(String nickName, Socket socket) throws IOException {

        this.nickName = nickName;

        this.socket = socket;

        this.sender = createSenderClient(socket);
        this.receiver = createReceiverServer(socket);

        listenersMap = new EnumMap<RequestType, List<Listener>>(RequestType.class);

        nextId = new Random().nextInt();
        
        stackManager = new InteractionListener(this);
    }

    public Sender<RequestClient> getSender() {
        return this.sender;
    }

    @Override
    public void run() {

        while (!socket.isClosed()) {
            try {
                ResponseServer response = receiver.receive();
                notifyAll(response);

            } catch (InvalidMessageException ex) {
                //FIXME tratar erro de mensage errada.
                getLogger(ClientTask.class.getName()).log(SEVERE, null, ex);
            } catch (IOException ex) {
                getLogger(ClientTask.class.getName()).log(SEVERE, null, ex);
            }
        }
    }

    public void sendRequest(RequestClient request) throws IOException {
        request.id = nextId++;
        stackManager.register(request.id);
        sender.send(request);
    }

    /**
     * Registrar um listener que quer receber notificação quando uma resposta
     * do servidor for uma resposta do tipo de requisição passada como parametro
     */
    public void subscribe(RequestType reply, Listener listener) {

        List<Listener> listeners = listenersMap.get(reply);

        if (listeners == null) {
            listeners = new ArrayList<Listener>();
            listenersMap.put(reply, listeners);
        }

        listeners.add(listener);
    }
    
    public void notifyAll(ResponseServer response) {
        List<Listener> listeners = listenersMap.get(response.getRequestType());

        if (listeners == null) return;

        for (Listener listener : listeners) {
            listener.update(response);
        }
    }

    public void close() throws Exception{
        System.out.println("entered");

        socket.shutdownInput();
        socket.shutdownOutput();
        sender.close();
        receiver.close();
        socket.close();

    }
    
   
}

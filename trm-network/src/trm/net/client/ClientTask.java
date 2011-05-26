package trm.net.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.net.Socket;
import java.util.EnumMap;
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
 *
 */
public class ClientTask implements Runnable {

    private String nickName;
    
    private Socket socket;
    
    private Sender<RequestClient> sender;
    private Receiver<ResponseServer> receiver;
    
    private Map<RequestType, List<Listener>> listenersMap;

    public ClientTask(String nickName, Socket socket) throws IOException {

        this.nickName = nickName;

        this.socket = socket;

        this.sender = createSenderClient(socket);
        this.receiver = createReceiverServer(socket);

        listenersMap = new EnumMap<RequestType, List<Listener>>(RequestType.class);
    }

    @Override
    public void run() {

        while (socket.isConnected()) {
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
}

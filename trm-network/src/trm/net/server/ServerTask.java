package trm.net.server;

import trm.net.server.game.PlayerServer;
import trm.net.server.game.GameManager;
import trm.net.model.InvalidMessageException;
import java.io.IOException;
import java.net.Socket;
import trm.net.model.Receiver;
import trm.net.model.Sender;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;
import trm.net.model.protocol.ResponseServer;
import trm.net.model.protocol.ResponseType;
import trm.net.util.MessageFactory;
import trm.net.util.MessageFactoryImpl;
import static trm.net.util.SenderFactory.*;
import static trm.net.util.ReceiverFactory.*;
import static java.util.logging.Level.*;
import static java.util.logging.Logger.*;

/**
 *
 * @author Marcos
 */
public class ServerTask implements Runnable {

    private PlayerServer player;
    
    private Socket socket;
    
    private Sender<ResponseServer> sender;
    private Receiver<RequestClient> receiver;
    
    private RequestHandler handler;
    
    private MessageFactory messageFactoy;
    

    public ServerTask(Socket socket) throws IOException {
        
        this.socket = socket;
        
        this.sender = createSenderServer(socket);
        this.receiver = createReceiverClient(socket);
        
        this.messageFactoy = new MessageFactoryImpl();
    }

    public PlayerServer getPlayer() {
        return player;
    }

    @Override
    public void run() {
        RequestClient request = null;
        ResponseServer response = null;
        
        try {
            
            //primeira mensagem tem que ser de login!o login sera o nome!
            //ele ficara nesse 'loop' enquanto não tiver logado com sucesso ou
            //envie uma mensage que solicite sua saida
            do {
                request = receiveRequest();
                response = login(request);

                sender.send(response);

            } while (!response.isAck() && !response.isCloseConnection());
            

            handler = new RequestHandlerImpl(this);
            
            while (!socket.isClosed() && !response.isCloseConnection()) {
                request = receiveRequest();
                response = handler.handle(request);

                sender.send(response);
            }
            
        } catch (IOException ex) {
            getLogger(ServerTask.class.getName()).log(SEVERE, null, ex);
        } finally {
            //TODO notificar sala de jogo casa ele esteja em alguma.
            close();
            System.out.println(">><<<<");
            GameManager.getPlayerManager().removePlayer(this);
        }
    }
    
    /**
     * Trata o recebimento de mensagens, levando em considerações erros nas
     * mensagens, se ouver um erro ele retorna uma requisição com o tipo
     * UNDEFINED
     */
    public RequestClient receiveRequest() throws IOException {

        RequestClient request = null;
        try {
            request = receiver.receive();
        } catch (InvalidMessageException ex) {
            getLogger(ServerTask.class.getName()).log(SEVERE, null, ex);
        }

        if (request == null) {
            request = new RequestClient(RequestType.UNDEFINED);
        }

        return request;
    }
    
    private void close() {
        try {
            sender.close();
            receiver.close();
            socket.close();
        } catch (IOException ex) {
            getLogger(ServerTask.class.getName()).log(SEVERE, null, ex);
        }
    }

    public void sendMessage(ResponseServer response) throws IOException {
        sender.send(response);
    }

    //metodos uteis-------------------------------------------------------------
    
    private ResponseServer login(RequestClient request) {

        ResponseServer response = null;

        GameManager playerManager = GameManager.getPlayerManager();
        String nickName = request.nickName;


        if (isValidNickName(nickName)) {

            player = playerManager.newPlayer(nickName);

            response = new ResponseServer(ResponseType.ACK, RequestType.LOGIN);
            response.player = player.getInf();

        } else {
            response = ResponseServer.createResponseErro(
                    "Nome de usuario não valido", RequestType.LOGIN);
        }
        return response;
    }

    private boolean isValidNickName(String nickName) {
        return nickName != null && !nickName.trim().equals("");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServerTask other = (ServerTask) obj;
        if (this.player != other.player && (this.player == null
                || !this.player.equals(other.player))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.player != null ? this.player.hashCode() : 0);
        return hash;
    }
}

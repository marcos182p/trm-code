package trm.net.server;

import trm.net.server.handler.UndefinedHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.net.model.Receiver;
import trm.net.model.Sender;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;
import trm.net.model.protocol.ResponseServer;
import trm.net.util.MessageFactory;
import trm.net.util.MessageFactoryImpl;
import trm.net.util.ReceiverFactory;
import trm.net.util.SenderFactory;

/**
 *
 * @author Marcos
 */
public class ServerTask implements Runnable {

    private PlayerServer player;
    private Socket socket;
    private Sender<ResponseServer> sender;
    private Receiver<RequestClient> receiver;
    private MessageFactory messageFactoy;
    private Map<RequestType, RequestHandler> handlers;

    public ServerTask(Socket socket) throws IOException {
        this.socket = socket;
        this.sender = SenderFactory.createSenderServer(new PrintWriter(socket.getOutputStream()));
        this.receiver = ReceiverFactory.createReceiverClient(new BufferedReader(
                new InputStreamReader(socket.getInputStream())));
        
        this.messageFactoy = new MessageFactoryImpl();
        

    }

    private void initHandlers() {
        //FIXME adicionar os tratatores!
        handlers = new EnumMap<RequestType, RequestHandler>(RequestType.class);

        handlers.put(RequestType.UNDEFINED, new UndefinedHandler(player));
    }

    public PlayerServer getPlayer() {
        return player;
    }

    //FIXME fazer tratamento de mensagens erradas!
    @Override
    public void run() {
        try {
            

            RequestClient message = null;
            RequestHandler handler = null;

            message = receiver.receive();

            //primeira mensagem tem que ser de login!o login sera o nome!
            handler = new LoginRequest();
            handler.handle(message);

            initHandlers();

            while (socket.isConnected()) {

                message = receiver.receive();

                handler = handlers.get(message.getRequestType());

                if (handler == null) {
                    handler = handlers.get(RequestType.UNDEFINED);
                }

                sender.send(handler.handle(message));
            }


        } catch (IOException ex) {
            Logger.getLogger(ServerTask.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //TODO notificar sala de jogo casa ele esteja em alguma.
        }
    }

    public void sendMessage(ResponseServer response) throws IOException {
        sender.send(response);
    }

    private class LoginRequest extends RequestHandler {

        public LoginRequest() {
            super(null);
        }

        @Override
        public ResponseServer handle(RequestClient message) {
            ResponseServer response = null;

            GameManager playerManager = GameManager.getPlayerManager();
            String nickName = message.getUserName();


            if (isValidNickName(nickName)) {

                player = playerManager.newPlayer(nickName);

                //FIXME ajeitar aqui
                response = messageFactoy.createResponseServer(null);

            } else {
                //FIXME terminar essa parte
                response = messageFactoy.createResponseServer(null);
            }




            return response;
        }

        //FIXME verificar se apelido é valido
        private boolean isValidNickName(String nickName) {
            return true;
        }
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

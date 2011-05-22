package trm.net.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.core.Player;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RQService;
import trm.net.model.protocol.ResponseServer;
import trm.net.util.MessageFactoryImpl;
import trm.net.util.MessageFactoy;
import trm.net.util.ParserMessage;
import trm.net.util.ParserMessageImpl;

/**
 *
 * @author Marcos
 */
public class ServerTask implements Runnable {
    
    private Player player;
    private Socket socket;
    private Sender sender;
    private ParserMessage parserMessage;
    private MessageFactoy messageFactoy;
    private Map<RQService, RequestHandler> handlers;
    
    public ServerTask(Socket socket) throws IOException {
        this.socket = socket;
        this.sender = new SenderImpl(new PrintWriter(socket.getOutputStream()));
        
        this.messageFactoy = new MessageFactoryImpl();
        this.parserMessage = new ParserMessageImpl();
        
        initHandlers();
    }
    
    private void initHandlers() {
        //FIXME adicionar os tratatores!
        handlers.put(RQService.LOGIN, new LoginRequest());
        handlers.put(RQService.UNDEFINED, new UndefinedRequest(this));
    }
    
    public Player getPlayer() {
        return player;
    }
    
    //FIXME fazer tratamento de mensagens erradas!
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            
            RequestClient message = null;
            RequestHandler handler = null;
            
            message = parserMessage.parserRequestClient(reader.readLine());
            
            //primeira mensagem tem que ser de login!o login sera o nome!
            handler = handlers.get(RQService.LOGIN);
            handler.handle(message);
            
            String line = null;
            
            while (socket.isConnected() && (line = reader.readLine()) != null) {
                
                message = parserMessage.parserRequestClient(line);
                
                handler = handlers.get(message.getRQService());
                
                if (handler == null) {
                    handler = handlers.get(RQService.UNDEFINED);
                }
                
                handler.handle(message);
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerTask.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //TODO notificar sala de jogo casa ele esteja em alguma.
        }
    }
    
    public void sendMessage(ResponseServer response) throws IOException {
        sender.send(parserMessage.convertResponseServer(response) + "\n");
    }
    
    private class LoginRequest extends RequestHandler {

        public LoginRequest() {
            super (null);
        }
        
        @Override
        public void handle(RequestClient message) {
            PlayerManager playerManager = PlayerManager.getPlayerManager();
            String nickName = message.getUserName();
            try {


                if (isValidNickName(nickName)) {

                    player = playerManager.newPlayer(nickName);

                    //FIXME ajeitar aqui
                    sendMessage(messageFactoy.createResponseServer(null));

                } else {
                    //FIXME terminar essa parte
                    sendMessage(null);
                    socket.close();
                }



            } catch (IOException ex) {
                Logger.getLogger(ServerTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //FIXME verificar se apelido é valido
        private boolean isValidNickName(String nickName) {
            return true;
        }
    
}
}

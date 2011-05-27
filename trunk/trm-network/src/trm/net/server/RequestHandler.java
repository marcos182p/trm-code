package trm.net.server;

import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;

/**
 *
 */
public abstract class RequestHandler {
    
    protected ServerTask serverTask;
    
    public RequestHandler(ServerTask player) {
        this.serverTask = player;
    }
    
    /**
     * Trata a mensagem de requisição do cliente e retorna uma resposta caso 
     * o servidor quera enviar uma resposta de confirmação ou de erro.
     * @param message mensagem do cliente
     * @return  mensagem resposta para o servidor enviar para o cliente
     */
    public abstract ResponseServer handle(RequestClient message);
}

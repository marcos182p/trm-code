package trm.net.server;

import java.util.List;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;
import trm.net.model.protocol.ResponseServer;
import trm.net.model.protocol.ResponseType;
import trm.net.server.game.GameManager;

public abstract class RequestHandler1 {
    
    private List<RequestType> types;
    
    protected ServerTask serverTask;
    
    protected GameManager gameManager;

    public RequestHandler1(ServerTask serverTask, List<RequestType> types) {
        
        this.types = types;
        
        this.serverTask = serverTask;
        
        gameManager = GameManager.getPlayerManager();
    }

    public ResponseServer handle(RequestClient request) {
        
        RequestType rqType = request.requestType;
        if (!types.contains(request.requestType)) {
            throw new RuntimeException("Tipo de requisição não tratavel.");
        }
        
        ResponseServer response = new ResponseServer(ResponseType.ACK, rqType);
        
        try {
            doExecute(request, response);
            
        } catch (Exception e) {
            response = new ResponseServer(ResponseType.ERRO, rqType);
            
            response.erroMessage = e.getMessage();
        }
        
        response.senderPlayer = serverTask.getPlayer().getInf();
        
        return response;
    }
    
    public abstract void doExecute(RequestClient request, ResponseServer response);
    
}

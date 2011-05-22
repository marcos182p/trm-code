package trm.net.model;

import trm.net.model.protocol.RequestClient;

/**
 *
 */
public abstract class RequestHandler {
    
    protected ServerTask serverTask;
    
    public RequestHandler(ServerTask serverTask) {
        this.serverTask = serverTask;
    }
    
    public abstract void handle(RequestClient message);
}

package trm.net.model;

import trm.net.model.protocol.RequestClient;

/**
 *
 */
class UndefinedRequest extends RequestHandler {

    public UndefinedRequest(ServerTask serverTask) {
        super (serverTask);
    }

    @Override
    public void handle(RequestClient message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

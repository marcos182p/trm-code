package trm.net.server;

import trm.core.Player;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;

/**
 *
 */
class UndefinedRequest extends RequestHandler {

    public UndefinedRequest(Player player) {
        super (player);
    }

    @Override
    public ResponseServer handle(RequestClient message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

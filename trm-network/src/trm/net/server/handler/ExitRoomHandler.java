package trm.net.server.handler;

import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;
import trm.net.server.PlayerServer;
import trm.net.server.RequestHandler;

/**
 *
 */
public class ExitRoomHandler extends RequestHandler {

    public ExitRoomHandler(PlayerServer player) {
        super(player);
    }

    @Override
    public ResponseServer handle(RequestClient message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

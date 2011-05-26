package trm.net.server.handler;

import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;
import trm.net.server.PlayerServer;
import trm.net.server.RequestHandler;
import trm.net.server.StatePlayer;

/**
 *
 */
public class EntryRoomHandler extends RequestHandler {
    
    public EntryRoomHandler(PlayerServer player) {
        super (player);
    }

    @Override
    public ResponseServer handle(RequestClient message) {
        if (player.getState().equals(StatePlayer.PLAYING)) {
            //ERRO!
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

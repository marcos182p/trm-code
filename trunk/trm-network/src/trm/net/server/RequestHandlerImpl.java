package trm.net.server;

import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;
import trm.net.model.protocol.ResponseServer;
import trm.net.server.PlayerServer;
import trm.net.server.RequestHandler;
import trm.net.server.StatePlayer;

/**
 *
 */
public class RequestHandlerImpl extends RequestHandler {
    
    public RequestHandlerImpl(PlayerServer player) {
        super (player);
    }

    @Override
    public ResponseServer handle(RequestClient message) {
        
        String userName = player.getInf().getNickName();
        RequestType requestType = message.getRequestType();
        
        
        
        switch (message.getRequestType()) {
            case ENTRY_ROOM:
            case EXIT_ROOM:
            case LIST_ROOMS:
            case LIST_STONES:
            case MOVE_STONE:
            case POST_MESSAGE:
            
        }
        
        if (player.getState().equals(StatePlayer.PLAYING)) {
            //ERRO!
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

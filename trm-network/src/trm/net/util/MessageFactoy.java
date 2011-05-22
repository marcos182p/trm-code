package trm.net.util;

import trm.net.model.protocol.ResponseType;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;

/**
 *
 * @author Marcos
 */
public interface MessageFactoy {
    
    RequestClient createRequestClient();
    
    ResponseServer createResponseServer(ResponseType responseType);
}

package trm.net.util;

import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;

/**
 *
 */
public interface ParserMessage {
    
    String convertMessageRequest(RequestClient message);
    
    String convertResponseServer(ResponseServer message);
    
    /**
     * 
     * @param message
     * @return
     * @throws RuntimeException caso a mensagem não seja valida.
     */
    RequestClient parserRequestClient(String message) throws RuntimeException;
    
    ResponseServer parserRequestServer(String message) throws RuntimeException;
}

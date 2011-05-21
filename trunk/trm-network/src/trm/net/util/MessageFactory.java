package trm.net.util;

import trm.net.model.protocol.MessageClient;
import trm.net.model.protocol.MessageServer;

/**
 *
 */
public interface MessageFactory {
    
    String generateMessageClient(MessageClient message);
    
    String generateMessageServer(MessageServer message);
    
    /**
     * 
     * @param message
     * @return
     * @throws RuntimeException caso a mensagem não seja valida.
     */
    MessageClient parserMessageClient(String message) throws RuntimeException;
    
    MessageServer parserMessageServer(String message) throws RuntimeException;
}

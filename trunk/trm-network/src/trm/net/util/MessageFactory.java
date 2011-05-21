package trm.net.util;

import trm.net.model.protocol.MessageClient;

/**
 *
 */
public interface MessageFactory {
    
    String generateMessage(MessageClient message);
    
    /**
     * 
     * @param message
     * @return
     * @throws RuntimeException caso a mensagem não seja valida.
     */
    MessageClient parserMessage(String message) throws RuntimeException;
}

package trm.net.util;

import trm.net.model.Message;

/**
 *
 */
public interface MessageFactory {
    
    String generateMessage(Message message);
    
    /**
     * 
     * @param message
     * @return
     * @throws RuntimeException caso a mensagem não seja valida.
     */
    Message parserMessage(String message) throws RuntimeException;
}

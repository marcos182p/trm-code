package trm.net.util;

import trm.net.model.InvalidMessageException;

/**
 * @author TRM
 * @version 0.99
 */
public interface ParserMessage<Message> {
    
    String buildMessage(Message message);
    
    /**
     * 
     * @param message
     * @return
     * @throws RuntimeException caso a mensagem não seja valida.
     */
    Message parseMessage(String message) throws InvalidMessageException;
}

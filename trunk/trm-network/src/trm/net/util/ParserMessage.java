package trm.net.util;

/**
 *
 */
public interface ParserMessage<Message> {
    
    String buildMessage(Message message);
    
    /**
     * 
     * @param message
     * @return
     * @throws RuntimeException caso a mensagem não seja valida.
     */
    Message parseMessage(String message) throws RuntimeException;
}

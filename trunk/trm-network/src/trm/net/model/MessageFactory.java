package trm.net.model;

/**
 *
 */
public interface MessageFactory {
    
    String generatorMessage(Message message);
    
    /**
     * 
     * @param message
     * @return
     * @throws RuntimeException caso a mensagem não seja valida.
     */
    Message parserMessage(String message) throws RuntimeException;
}

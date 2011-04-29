package br.ufal.ic.model;

/**
 *
 * @author Marcos Paulo
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

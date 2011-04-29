package br.ufal.ic.model;

import java.io.IOException;

/**
 *
 * @author Marcos Paulo
 */
public interface Sender {
    
    void send(Message message) throws IOException;
    
    void close() throws IOException;
}

package trm.net.model;

import java.io.IOException;

/**
 * @author TRM
 * @version 0.99
 */
public interface Sender<Message> {
    
    void send(Message message) throws IOException;
    
    void close() throws IOException;
}

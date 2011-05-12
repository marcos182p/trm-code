package trm.net.model;

import java.io.IOException;

/**
 *
 */
public interface Sender {
    
    void send(Message message) throws IOException;
    
    void close() throws IOException;
}

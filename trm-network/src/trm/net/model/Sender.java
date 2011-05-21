package trm.net.model;

import java.io.IOException;

/**
 *
 */
public interface Sender {
    
    void send(String message) throws IOException;
    
    void close() throws IOException;
}

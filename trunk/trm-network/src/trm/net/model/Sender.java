package trm.net.model;

import trm.net.model.protocol.MessageClient;
import java.io.IOException;

/**
 *
 */
public interface Sender {
    
    void send(MessageClient message) throws IOException;
    
    void close() throws IOException;
}

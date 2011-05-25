package trm.net.model;

import java.io.IOException;

/**
 *
 */
public interface Receiver<Message> {

    Message receive() throws IOException;

    void close() throws IOException;
}

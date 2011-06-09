package trm.net.model;

import java.io.IOException;

/**
 * @author TRM
 * @version 0.99
 */
public interface Receiver<Message> {

    Message receive() throws IOException, InvalidMessageException;

    void close() throws IOException;
}

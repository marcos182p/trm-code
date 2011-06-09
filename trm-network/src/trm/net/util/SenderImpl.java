package trm.net.util;

import java.io.IOException;
import java.io.PrintWriter;
import trm.net.model.Sender;

/**
 * @author TRM
 * @version 0.99
 */
public class SenderImpl<Message> implements Sender<Message> {

    private PrintWriter writer;
    private ParserMessage<Message> parserMessage;

    public SenderImpl(PrintWriter writer, ParserMessage<Message> parser) {
        this.writer = writer;
        parserMessage = parser;
    }

    @Override
    public void send(Message message) throws IOException {
        writer.println(parserMessage.buildMessage(message));
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}

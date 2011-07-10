package trm.net.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String msg = parserMessage.buildMessage(message);
//        Logger.getLogger(SenderImpl.class.getName()).log(Level.INFO, "send {0}", msg);
        writer.println(msg);
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}

package trm.net.util;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import trm.net.model.Sender;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;

/**
 *
 */
public class SenderFactory {

    public static Sender<RequestClient> createSenderClient(PrintWriter writer) {

        ParserMessage<RequestClient> parserMessage = createRequestGsonParser();
        Sender<RequestClient> sender = new SenderImpl<RequestClient>(writer, parserMessage);
        return sender;
    }

    public static Sender<ResponseServer> createSenderServer(PrintWriter writer) {
        ParserMessage<ResponseServer> parserMessage = new GsonParser<ResponseServer>(new TypeToken<ResponseServer>() {
        });
        Sender<ResponseServer> sender = new SenderImpl<ResponseServer>(writer, parserMessage);
        return sender;
    }

    private static GsonParser<RequestClient> createRequestGsonParser() {
        return new GsonParser<RequestClient>(new TypeToken<RequestClient>() {
        });
    }
}

class SenderImpl<Message> implements Sender<Message> {

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

package trm.net.util;

import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import trm.net.model.Receiver;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;

/**
 *
 * @author Marcos Paulo
 */
public class ReceiverFactory {

    public static Receiver<RequestClient> createReceiverClient(BufferedReader reader) {
        ParserMessage<RequestClient> parserMessage = new GsonParser<RequestClient>(new TypeToken<RequestClient>() {
        });
        ReceiverImpl<RequestClient> receiver = new ReceiverImpl<RequestClient>(reader, parserMessage);
        return receiver;
    }

    public static Receiver<ResponseServer> createReceiverServer(BufferedReader reader) {
        ParserMessage<ResponseServer> parserMessage = new GsonParser<ResponseServer>(new TypeToken<ResponseServer>() {
        });
        Receiver<ResponseServer> receiver = new ReceiverImpl<ResponseServer>(reader, parserMessage);
        return receiver;
    }
}

class ReceiverImpl<Message> implements Receiver<Message> {

    private BufferedReader reader;
    private ParserMessage<Message> parserMessage;

    public ReceiverImpl(BufferedReader reader, ParserMessage<Message> parser) {
        this.reader = reader;
        parserMessage = parser;
    }

    @Override
    public Message receive() throws IOException {
        return parserMessage.parseMessage(reader.readLine());
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}

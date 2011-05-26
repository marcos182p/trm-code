package trm.net.util;

import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import trm.net.model.InvalidMessageException;
import trm.net.model.Receiver;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;

/**
 *
 * @author Marcos Paulo
 */
public class ReceiverFactory {

    public static Receiver<RequestClient> createReceiverClient(Socket socket)
            throws IOException {


        InputStreamReader input = new InputStreamReader(socket.getInputStream());
        BufferedReader reader = new BufferedReader(input);

        ParserMessage<RequestClient> parserMessage =
                new GsonParser<RequestClient>(new TypeToken<RequestClient>() {
        });

        return new ReceiverImpl<RequestClient>(reader, parserMessage);
    }

    public static Receiver<ResponseServer> createReceiverServer(Socket socket)
            throws IOException {

        InputStreamReader input = new InputStreamReader(socket.getInputStream());
        BufferedReader reader = new BufferedReader(input);

        ParserMessage<ResponseServer> parserMessage =
                new GsonParser<ResponseServer>(new TypeToken<ResponseServer>() {
        });

        return new ReceiverImpl<ResponseServer>(reader, parserMessage);
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
    public Message receive() throws IOException, InvalidMessageException {
        return parserMessage.parseMessage(reader.readLine());
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}

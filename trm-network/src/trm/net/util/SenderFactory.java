package trm.net.util;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import trm.net.model.Sender;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;

/**
 * @author TRM
 * @version 0.99
 */
public class SenderFactory {

    public static Sender<RequestClient> createSenderClient(Socket socket)
            throws IOException {

        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        ParserMessage<RequestClient> parserMessage =
                new GsonParser<RequestClient>(new TypeToken<RequestClient>() {
        });

        return new SenderImpl<RequestClient>(writer, parserMessage);
    }

    public static Sender<ResponseServer> createSenderServer(Socket socket) 
            throws IOException {

        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        ParserMessage<ResponseServer> parserMessage =
                new GsonParser<ResponseServer>(new TypeToken<ResponseServer>() {
        });

        return new SenderImpl<ResponseServer>(writer, parserMessage);
    }
}


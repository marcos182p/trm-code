package trm.net.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.net.model.InvalidMessageException;
import trm.net.model.Receiver;

public class ReceiverImpl<Message> implements Receiver<Message> {

    private BufferedReader reader;
    private ParserMessage<Message> parserMessage;

    public ReceiverImpl(BufferedReader reader, ParserMessage<Message> parser) {
        this.reader = reader;
        parserMessage = parser;
    }
    //resolver problema de fechamento de conexão:
    //http://stackoverflow.com/questions/151590/java-how-do-detect-a-remote-side-socket-close
    @Override
    public Message receive() throws IOException, InvalidMessageException {
        String line = reader.readLine();

        if (line == null) {
            throw new IOException("conexão fechada!");
        }
        
        Logger.getLogger(ReceiverImpl.class.getName()).log(Level.INFO, "receive {0}", line);

        return parserMessage.parseMessage(line);
    }
    @Override
    public void close() throws IOException {
        reader.close();
    }
}

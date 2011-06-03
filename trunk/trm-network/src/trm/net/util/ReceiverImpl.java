/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.net.util;

import java.io.BufferedReader;
import java.io.IOException;
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

        return parserMessage.parseMessage(line);
    }
    @Override
    public void close() throws IOException {
        reader.close();
    }
}

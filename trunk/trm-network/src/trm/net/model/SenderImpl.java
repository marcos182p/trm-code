package trm.net.model;

import trm.net.model.protocol.MessageClient;
import trm.net.util.MessageFactory;
import trm.net.util.MessageFactoryImpl;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 */
public class SenderImpl implements Sender {

    private Writer writer;
    private MessageFactory messageFactory;

    public SenderImpl(Writer writer) {

        this.writer = writer;
        messageFactory = new MessageFactoryImpl();
    }

    @Override
    public void send(MessageClient message) throws IOException {
        writer.write(messageFactory.generateMessage(message) + "\n");
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }

//    public static void main(String[] args) throws IOException {
//        StringWriter builder = new StringWriter();
//        SenderImpl sender = new SenderImpl(builder);
//
//        sender.send(new MessageClient(10));
//        sender.send(new MessageClient(15));
//        
//        System.out.println(builder.toString());
//    }

}

package trm.net.util;

import trm.net.model.protocol.MessageClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import trm.net.model.protocol.MessageServer;

/**
 *
 */
public class MessageFactoryImpl implements MessageFactory {

    private final Gson gson;
    private final Type TYPE_CLIENT;
    private final Type TYPE_SERVER;

    public MessageFactoryImpl() {
        
        gson = new GsonBuilder().create();
        TYPE_CLIENT = new TypeToken<MessageClient>() {
        }.getType();
        TYPE_SERVER = new TypeToken<MessageClient>() {
        }.getType();
    }

    @Override
    public String generateMessageClient(MessageClient message) {
        return gson.toJson(message);
    }

    @Override
    public String generateMessageServer(MessageServer message) {
        return gson.toJson(message);
    }
    
    @Override
    public MessageClient parserMessageClient(String message) throws RuntimeException {
        return (MessageClient) gson.fromJson(message, TYPE_CLIENT);
    }

    @Override
    public MessageServer parserMessageServer(String message) throws RuntimeException {
        return (MessageServer) gson.fromJson(message, TYPE_SERVER);
    }

}

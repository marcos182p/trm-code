package trm.net.util;

import trm.net.model.protocol.MessageClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 *
 */
public class MessageFactoryImpl implements MessageFactory {

    private final Gson gson;
    private final Type TYPE;

    public MessageFactoryImpl() {
        gson = new GsonBuilder().create();
        TYPE = new TypeToken<MessageClient>() {
        }.getType();
    }

    @Override
    public String generateMessage(MessageClient message) {
        return gson.toJson(message);
    }

    @Override
    public MessageClient parserMessage(String message) throws RuntimeException {
        return (MessageClient) gson.fromJson(message, TYPE);
    }
}

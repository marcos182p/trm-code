package trm.net.util;

import trm.net.model.Message;
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
        TYPE = new TypeToken<Message>() {
        }.getType();
    }

    @Override
    public String generateMessage(Message message) {
        return gson.toJson(message);
    }

    @Override
    public Message parserMessage(String message) throws RuntimeException {
        return (Message) gson.fromJson(message, TYPE);
    }
}

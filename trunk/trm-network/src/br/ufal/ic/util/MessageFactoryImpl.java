package br.ufal.ic.util;

import br.ufal.ic.model.Message;
import br.ufal.ic.model.MessageFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 *
 * @author Marcos Paulo
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
    public String generatorMessage(Message message) {
        return gson.toJson(message);
    }

    @Override
    public Message parserMessage(String message) throws RuntimeException {
        return (Message) gson.fromJson(message, TYPE);
    }
}

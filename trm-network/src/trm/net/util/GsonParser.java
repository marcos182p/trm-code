package trm.net.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import trm.net.model.InvalidMessageException;
import trm.net.model.protocol.RequestType;

/**
 * @author TRM
 * @version 0.99
 */
public class GsonParser<Message> implements ParserMessage<Message> {

    private GsonUtil<Message> gsonUtil;

    public GsonParser(TypeToken<Message> typeToken) {
        gsonUtil = new GsonUtil<Message>(typeToken);
    }

    @Override
    public String buildMessage(Message message) {
        return gsonUtil.convertMessage(message);
    }

    @Override
    public Message parseMessage(String message) throws InvalidMessageException {
        
        Message result = null;
        
        try {
            result = gsonUtil.parserRequestClient(message);

        } catch (JsonParseException e) {
            throw new InvalidMessageException(e);
        }
        
        return result;
    }
}

class GsonUtil<Message> {

    private Gson gson;
    private Type typeClass;

    public GsonUtil(TypeToken<Message> typeToken) {
        
  
        gson = new GsonBuilder().registerTypeAdapter(RequestType.class, new MyEnumTypeAdapter()).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        
        typeClass = typeToken.getType();
    }

    public String convertMessage(Message message) {
        return gson.toJson(message);
    }

    public Message parserRequestClient(String message) throws JsonParseException {

        return (Message) gson.fromJson(message, typeClass);
    }
}
class MyEnumTypeAdapter
        implements JsonSerializer<RequestType>, JsonDeserializer<RequestType> {

    @Override
    public JsonElement serialize(RequestType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.name());
    }

    @Override
    public RequestType deserialize(JsonElement json, Type classOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return RequestType.valueOf(json.getAsString().toUpperCase());
    }
}
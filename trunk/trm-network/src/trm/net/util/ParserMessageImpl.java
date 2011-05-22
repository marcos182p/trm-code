package trm.net.util;

import trm.net.model.protocol.RequestClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import trm.net.model.protocol.ResponseServer;

/**
 *
 */
public class ParserMessageImpl implements ParserMessage {

    private final Gson gson;
    private final Type TYPE_CLIENT;
    private final Type TYPE_SERVER;

    public ParserMessageImpl() {
        
        gson = new GsonBuilder().create();
        TYPE_CLIENT = new TypeToken<RequestClient>() {
        }.getType();
        TYPE_SERVER = new TypeToken<RequestClient>() {
        }.getType();
    }

    @Override
    public String convertMessageRequest(RequestClient message) {
        return gson.toJson(message);
    }

    @Override
    public String convertResponseServer(ResponseServer message) {
        return gson.toJson(message);
    }
    
    @Override
    public RequestClient parserRequestClient(String message) throws RuntimeException {
        return (RequestClient) gson.fromJson(message, TYPE_CLIENT);
    }

    @Override
    public ResponseServer parserRequestServer(String message) throws RuntimeException {
        return (ResponseServer) gson.fromJson(message, TYPE_SERVER);
    }

}

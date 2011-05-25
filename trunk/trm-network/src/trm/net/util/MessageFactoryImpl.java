package trm.net.util;

import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;
import trm.net.model.protocol.ResponseType;

/**
 *
 */
public class MessageFactoryImpl implements MessageFactory {

    public MessageFactoryImpl() {
    }

    @Override
    public RequestClient createRequestClient() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResponseServer createResponseServer(ResponseType responseType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

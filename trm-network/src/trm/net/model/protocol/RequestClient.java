package trm.net.model.protocol;

import trm.net.util.ParserMessage;
import trm.net.util.ParserMessageImpl;

/**
 *
 */
public class RequestClient {
    
    private String userName;
    private String roomGame;
    private RQService service;
    private MessageType messageType;

    public RequestClient() {
    }
    
    public RequestClient(String userName, String roomGame, MessageType messageType) {
        this.userName = userName;
        this.messageType = messageType;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public String getRoomGame() {
        return roomGame;
    }
    
    public RQService getRQService() {
        return service;
    }
    
    public MessageType getMessageType() {
        return messageType;
    }
    
    public static void main(String[] args) {
        ParserMessage messageFactory = new ParserMessageImpl();
        
        RequestClient message = new RequestClient("marcos", "", MessageType.REQUEST_SERVICE);
        
        String m = messageFactory.convertMessageRequest(message);
        
        RequestClient message2 =  messageFactory.parserRequestClient(m);
        System.out.println(message2.getUserName());
        System.out.println(m);
        
        
        
    }

}

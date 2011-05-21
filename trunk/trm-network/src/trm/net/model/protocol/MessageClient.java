package trm.net.model.protocol;

import trm.net.util.MessageFactory;
import trm.net.util.MessageFactoryImpl;

/**
 *
 */
public class MessageClient {
    
    private String userName;
    private String roomGame;
    private RQServiceType serviceType;
    private MessageType messageType;

    public MessageClient() {
    }
    
    public MessageClient(String userName, String roomGame, MessageType messageType) {
        this.userName = userName;
        this.messageType = messageType;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public String getRoomGame() {
        return roomGame;
    }
    
    public RQServiceType getServiceType() {
        return serviceType;
    }
    
    public MessageType getMessageType() {
        return messageType;
    }
    
    public static void main(String[] args) {
        MessageFactory messageFactory = new MessageFactoryImpl();
        
        MessageClient message = new MessageClient("marcos", "", MessageType.REQUEST_SERVICE);
        
        String m = messageFactory.generateMessageClient(message);
        
        MessageClient message2 =  messageFactory.parserMessageClient(m);
        System.out.println(message2.getUserName());
        System.out.println(m);
        
        
        
    }

}

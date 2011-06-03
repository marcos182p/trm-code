package trm.net.model.protocol;

import com.google.gson.reflect.TypeToken;
import trm.core.SquareNumber;
import trm.core.Stone;
import trm.net.model.InvalidMessageException;
import trm.net.util.GsonParser;
import trm.net.util.ParserMessage;

/**
 *
 */
public class RequestClient {

    public static enum Position {

        LEFT, RIGHT
    }
    /**
     * apelido do usuario
     */
    private String userName;
    /**
     * numero da sala
     */
    private Long roomGame;
    /**
     * Peça que vai ser jogada no jogo
     */
    private Stone stone;
    /**
     * a posição que a peça vai ser jogada no jogo
     */
    private Position postionStone;
    /**
     * mensagem pra ser postada na sala do jogo
     */
    private String chatMessage;
    /**
     * cabeçalho que é usado quando o jogador quer criar uma sala, ele não
     * precisa entrar nessa sala
     */
    private String roomName;
    /**
     * tipo de requisição
     */
    private RequestType requestType;

    public RequestClient() {
    }

    public RequestClient(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestClient(String userName, Long roomGame, Stone stone,
            Position postionStone, String chatMessage, RequestType service) {
        this.userName = userName;
        this.roomGame = roomGame;
        this.stone = stone;
        this.postionStone = postionStone;
        this.chatMessage = chatMessage;
        this.requestType = service;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public Position getPostionStone() {
        return postionStone;
    }

    public Long getRoomGame() {
        return roomGame;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Stone getStone() {
        return stone;
    }

    public String getUserName() {
        return userName;
    }

    public static void main(String[] args) throws InvalidMessageException {
        ParserMessage<RequestClient> messageFactory = new GsonParser<RequestClient>(new TypeToken<RequestClient>() {
        });
//
        RequestClient message = new RequestClient("marcos", 1L, new Stone(SquareNumber.FIVE, SquareNumber.FIVE), Position.LEFT, null, RequestType.LIST_ROOMS);


        String m = messageFactory.buildMessage(message);
        System.out.println(m);

        RequestClient message2 = messageFactory.parseMessage("{\"user-name\":\"marcos\",\"room-game\":1,\"postion_stone\":\"LEFT\",\"request-type\":\"LOGIN\"}");
        
        System.out.println(message2.getRequestType());



    }
}

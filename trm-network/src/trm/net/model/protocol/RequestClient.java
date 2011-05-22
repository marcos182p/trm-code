package trm.net.model.protocol;

import trm.core.SquareNumber;
import trm.core.Stone;
import trm.net.util.ParserMessage;
import trm.net.util.ParserMessageImpl;

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
     * tipo de requisição
     */
    private RequestType requestType;

    public RequestClient() {
    }

    public RequestClient(String userName, Long roomGame) {
        this.userName = userName;
        this.roomGame = roomGame;
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

    public static void main(String[] args) {
        ParserMessage messageFactory = new ParserMessageImpl();

        RequestClient message = new RequestClient("marcos", 1L, new Stone(SquareNumber.ZERO, SquareNumber.ZERO), Position.LEFT, null, RequestType.LOGIN);


        String m = messageFactory.convertMessageRequest(message);

        RequestClient message2 = messageFactory.parserRequestClient(m);
        System.out.println(message2.getUserName());
        System.out.println(m);



    }
}

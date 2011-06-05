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

    public static enum Movement {

        PUT_LEFT, PUT_RIGHT, PASS
    }
    /**
     * apelido do usuario
     */
    public String userName;
    /**
     * numero da sala que o úsuario quer entrar
     */
    public Long roomId;
    /**
     * cabeçalho que é usado quando o jogador quer criar uma sala, ele não
     * precisa entrar nessa sala
     */
    public String newRoom;
    /**
     * Peça que vai ser jogada no jogo
     */
    public Stone stone;
    /**
     * a posição que a peça vai ser jogada no jogo
     */
    public Movement movement;
    /**
     * mensagem pra ser postada na sala do jogo
     */
    public String chatMessage;
    /**
     * tipo de requisição
     */
    public RequestType requestType;

    public RequestClient() {
    }

    public RequestClient(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestClient(String userName, Long roomId, String roomName,
            Stone stone, Movement postionStone, String chatMessage,
            RequestType requestType) {
        
        this.userName = userName;
        this.roomId = roomId;
        this.newRoom = roomName;
        this.stone = stone;
        this.movement = postionStone;
        this.chatMessage = chatMessage;
        this.requestType = requestType;
    }

    public static void main(String[] args) throws InvalidMessageException {
        ParserMessage<RequestClient> messageFactory = 
                new GsonParser<RequestClient>(new TypeToken<RequestClient>() {
        });

        RequestClient message = new RequestClient("marcos", 1L, null,
                new Stone(SquareNumber.FIVE, SquareNumber.FIVE), Movement.PUT_LEFT,
                null, RequestType.LIST_ROOMS);


        String m = messageFactory.buildMessage(message);
        System.out.println(m);

        RequestClient message2 = messageFactory.parseMessage("{\"user-name\":\"marcos\",\"room-game\":1,\"postion_stone\":\"LEFT\",\"request-type\":\"LOGIN\"}");

        System.out.println(message2.requestType);
    }
}

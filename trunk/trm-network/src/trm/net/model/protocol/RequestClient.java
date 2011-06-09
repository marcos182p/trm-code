package trm.net.model.protocol;

import trm.core.Movement;
import com.google.gson.reflect.TypeToken;
import trm.core.SquareNumber;
import trm.core.Stone;
import trm.net.model.InvalidMessageException;
import trm.net.util.GsonParser;
import trm.net.util.ParserMessage;

/**
 * @author TRM
 * @version 0.99
 */
public class RequestClient {

    /**
     * tipo de requisição
     */
    public RequestType requestType;
    /**
     * apelido do usuario
     */
    public String nickName;
    /**
     * numero da sala que o úsuario quer entrar ou cabeçalho que é usado quando 
     * o jogador quer criar uma sala, ele não precisa entrar nessa sala
     * >> a semantica desse atributo dependera do tipo requisição <<
     */
    public String room;
    /**
     * Movimento que o jogador quer fazer no jogo
     */
    public Movement movement;
    /**
     * mensagem pra ser postada na sala do jogo
     */
    public String chatMessage;

    public RequestClient() {
    }

    public RequestClient(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestClient(RequestType requestType, String nickName, String room, 
            Movement movement, String chatMessage) {
        this.nickName = nickName;
        this.room = room;
        this.movement = movement;
        this.chatMessage = chatMessage;
        this.requestType = requestType;
    }


    public static void main(String[] args) throws InvalidMessageException {
        ParserMessage<RequestClient> messageFactory = 
                new GsonParser<RequestClient>(new TypeToken<RequestClient>() {
        });

        RequestClient message = new RequestClient(RequestType.GET_ROOMS,
                "marcos", "salaTest", new Movement(
                new Stone(SquareNumber.FIVE, SquareNumber.FIVE),
                Movement.Action.PUT_LEFT), null);


        String m = messageFactory.buildMessage(message);
        System.out.println(m);

        RequestClient message2 = messageFactory.parseMessage(
                "{\"user-name\":\"marcos\",\"room-game\":1,\"postion_stone\":\"LEFT\",\"request-type\":\"LOGIN\"}");

        System.out.println(message2.requestType);
    }
}

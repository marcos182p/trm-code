package trm.net.model.protocol;

import java.util.List;
import trm.core.Player;
import trm.core.Stone;
import trm.net.server.game.RoomInf;

/**
 *
 */
public class ResponseServer {

    /**
     * se for ACK' essa resposta é de confirmação de uma requisição, se for
     * ERRO é uma mensagem avisando que ouve um erro no formato da mensage 
     * enviada pelo cliente podendo ser acompanhado por uma mensagem de texto
     * sobre o erro.
     */
    private ResponseType responseType;
    /**
     * tipo de requisição que gerou essa resposta
     */
    private RequestType requestType;
    /**
     * contem um texto descrevendo a confirmação da requisição, se ouver.
     */
    private String ackMessage;
    /**
     * contem um texto descrevendo o erro, se ouver.
     */
    private String erroMessage;
    /**
     * informações da sala de jogo
     */
    private List<RoomInf> rooms;
    /**
     * configuração do tabuleiro do jogo(ordenado)
     */
    private List<Stone> stones;
    /**
     * mensagem enviada por algum usuario
     */
    private String chatMessage;
    /**
     * jogador que enviou a mensagem
     */
    private Player senderPlayer;

    public ResponseServer() {
    }

    public ResponseServer(ResponseType responseType, RequestType requestType, String ackMessage, String erroMessage, List<RoomInf> rooms, List<Stone> stones, String chatMessage, Player senderPlayer) {
        this.responseType = responseType;
        this.requestType = requestType;
        this.ackMessage = ackMessage;
        this.erroMessage = erroMessage;
        this.rooms = rooms;
        this.stones = stones;
        this.chatMessage = chatMessage;
        this.senderPlayer = senderPlayer;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public String getErroMessage() {
        return erroMessage;
    }

    public Player getPlayer() {
        return senderPlayer;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public List<Stone> getStones() {
        return stones;
    }

    public boolean isAck() {
        return responseType.equals(ResponseType.ACK);
    }

    public boolean isCloseConnection() {
        return isAck() && requestType.equals(RequestType.CLOSE_CONNECTION);
    }

    public static ResponseServer createResponseServer(String message, Player senderPlayer) {
        return new ResponseServer(ResponseType.ACK, RequestType.POST_MESSAGE, null, null, null, null, message, senderPlayer);
    }
}

package trm.net.model.protocol;

import java.util.List;
import trm.core.Player;
import trm.core.Stone;

/**
 *
 */
public class ResponseServer {

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
    private Player player;
    /**
     * contem um texto descrevendo o erro.
     */
    private String erroMessage;
    /**
     * contem um texto informativo sobre o sucesso da requisição
     */
    private String ackMessage;
    /**
     * tipo de resposta do servidor
     */
    private ResponseType responseType;

    public ResponseServer() {
    }

    public ResponseServer(List<Stone> stones, String chatMessage, Player player,
            String erroMessage, String ackMessage, ResponseType responseType) {
        this.stones = stones;
        this.chatMessage = chatMessage;
        this.player = player;
        this.erroMessage = erroMessage;
        this.ackMessage = ackMessage;
        this.responseType = responseType;
    }

    public String getAckMessage() {
        return ackMessage;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public String getErroMessage() {
        return erroMessage;
    }

    public Player getPlayer() {
        return player;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public List<Stone> getStones() {
        return stones;
    }
}

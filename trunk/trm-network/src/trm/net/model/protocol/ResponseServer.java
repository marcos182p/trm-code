package trm.net.model.protocol;

import java.util.List;
import trm.core.Player;
import trm.core.PlayerInf;
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
     * contem um texto descrevendo o erro, se ouver.
     */
    public String erroMessage;
    /**
     * informações da sala de jogo
     */
    public List<RoomInf> rooms;
    /**
     * peças que estão na mão do jogador.
     */
    public List<Stone> handStones;
    /**
     * configuração do tabuleiro do jogo(ordenado)
     */
    public List<Stone> boardStones;
    /**
     * mensagem enviada por algum usuario
     */
    public String chatMessage;
    /**
     * jogador que enviou a mensagem
     */
    public PlayerInf senderPlayer;
    /**
     * jogador que ganhou a partida
     */
    public PlayerInf winnerPlyer;
    /**
     * Jogadores que estão no jogo.
     */
    public List<PlayerInf> playersInGame;
    /**
     * sala criada
     */
    public RoomInf newRoom;

    public ResponseServer() {
    }
    
    public ResponseServer(ResponseType responseType, RequestType requestType) {
        this.responseType = responseType;
        this.requestType = requestType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public boolean isAck() {
        return responseType.equals(ResponseType.ACK);
    }

    public boolean isCloseConnection() {
        return isAck() && requestType.equals(RequestType.CLOSE_CONNECTION);
    }

    public static ResponseServer createResponseServer(String message,
            Player senderPlayer) {
        ResponseServer response = new ResponseServer(ResponseType.ACK, RequestType.POST_MESSAGE);
        response.senderPlayer = senderPlayer.getInf();
        response.chatMessage = message;
        return response;
    }

    public static ResponseServer createResponseErro(String erroMessage, 
            RequestType type) {
        
        ResponseServer response = new ResponseServer(ResponseType.ERRO, type);
        response.erroMessage = erroMessage;
        return response;
    }
}

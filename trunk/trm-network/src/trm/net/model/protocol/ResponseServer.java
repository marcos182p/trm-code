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
    public ResponseType responseType;
    /**
     * tipo de requisição que gerou essa resposta
     */
    public RequestType requestType;
    /**
     * contem um texto descrevendo a confirmação da requisição, se ouver.
     */
    public String ackMessage;
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
    private List<PlayerInf> playersInGame;

    public ResponseServer() {
    }

    public ResponseServer(ResponseType responseType, RequestType requestType, 
            String ackMessage, String erroMessage, List<RoomInf> rooms,
            List<Stone> handStones, List<Stone> boardStones, String chatMessage,
            PlayerInf senderPlayer, PlayerInf winnerPlyer,
            List<PlayerInf> playersInGame) {
        
        this.responseType = responseType;
        this.requestType = requestType;
        this.ackMessage = ackMessage;
        this.erroMessage = erroMessage;
        this.rooms = rooms;
        this.handStones = handStones;
        this.boardStones = boardStones;
        this.chatMessage = chatMessage;
        this.senderPlayer = senderPlayer;
        this.winnerPlyer = winnerPlyer;
        this.playersInGame = playersInGame;
    }

    

    
    public boolean isAck() {
        return responseType.equals(ResponseType.ACK);
    }

    public boolean isCloseConnection() {
        return isAck() && requestType.equals(RequestType.CLOSE_CONNECTION);
    }

    public static ResponseServer createResponseServer(String message,
            Player senderPlayer) {
        return new ResponseServer(ResponseType.ACK, RequestType.POST_MESSAGE,
                null, null, null, null, null, message, senderPlayer.getInf(),
                null, null);
    }

    public static ResponseServer createErroResponse(final String er,
            RequestType type) {
        
        return new ResponseServer(ResponseType.ERRO, type, null, er, null,
                null, null, null, null, null, null);
    }
}

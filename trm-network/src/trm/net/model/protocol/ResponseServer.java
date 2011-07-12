package trm.net.model.protocol;

import java.util.List;
import trm.core.Movement;
import trm.core.Player;
import trm.core.PlayerInf;
import trm.core.Stone;
import trm.net.server.game.RoomInf;

/**
 * @author TRM
 * @version 0.99
 */
public class ResponseServer {
    /**
     * id da requisição do cliente
     */
    public long id;
    /**
     * se for ACK essa resposta é de confirmação de uma requisição, se for
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
     * peças que estão na mão do jogador ou configuração do tabuleiro do 
     * jogo(ordenado)
     * >> a semantica depende do tipo requisição  <<
     */
    public List<Stone> stones;
    /**
     * movimento feito pelo player
     */
    public Movement movement;
    /**
     * mensagem enviada por algum usuario
     */
    public String chatMessage;
    /**
     * jogador que enviou a mensagem ou jogador que ganhou a partida ou jogador
     * que fez o movimento
     * >> a semantica depende do tipo requisição  <<
     */
    public PlayerInf player;
    /**
     * jogadores que estão no jogo.
     */
    public List<PlayerInf> playersInGame;
    /**
     * pilha de execução do servidor
     */
    public List<StackTraceElement> stack;

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
        
        RequestType type = RequestType.PUT_MESSAGE;
        
        ResponseServer response = new ResponseServer(ResponseType.ACK, type);
        
        response.player = senderPlayer.getInf();
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

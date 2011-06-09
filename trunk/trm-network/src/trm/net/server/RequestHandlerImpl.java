package trm.net.server;

import java.util.logging.Logger;
import trm.net.server.game.GameAction;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;
import trm.net.model.protocol.ResponseType;
import trm.net.server.game.GameActionImpl;

/**
 * @author TRM
 * @version 0.99
 */
public class RequestHandlerImpl extends RequestHandler {
    
    private static final Logger LOGGER = Logger.getLogger(
            RequestHandlerImpl.class.getName());
    
    private GameAction gameAction;
    
    public RequestHandlerImpl(ServerTask player) {
        super(player);

        gameAction = new GameActionImpl(serverTask);
    }

    @Override
    public ResponseServer handle(RequestClient request) {
        
        ResponseServer response = new ResponseServer(ResponseType.ACK, 
                request.requestType);

        try {

            switch (request.requestType) {
                case ENTER_ROOM:
                    gameAction.enterRoom(request.room);
                    break;
                case EXIT_ROOM:
                    gameAction.exitRoom();
                    break;
                case START_GAME:
                    gameAction.startGame();
                    break;
                case END_GAME:
                    gameAction.endGame();
                    break;
                case GET_ROOMS:
                    response.rooms = gameAction.listRooms();
                    break;
                case GET_HAND:
                    response.stones = gameAction.getHandPlayer();
                    break;
                case GET_STONES:
                    response.stones = gameAction.getBoardStones();
                    break;
                case PUT_STONE:
                    gameAction.moveStone(request.movement);
                    response.movement = request.movement;
                    response.player = serverTask.getPlayer().getInf();
                    break;
                case PUT_MESSAGE:
                    gameAction.postMessage(request.chatMessage);
                    response.chatMessage = request.chatMessage;
                    break;
                case GET_PLAYERS:
                    response.playersInGame = gameAction.getPlayers();
                    break;
                case PUT_ROOM:
                    gameAction.createRoomGame(request.room);
                    break;
                case GET_WINNER:
                    response.player = gameAction.getWinner();
                    break;

                case UNDEFINED:
                    throw new RuntimeException("tipo de mensagem não "
                            + "reconhecida!");

                default:
                    throw new RuntimeException("tipo de mensagem: "
                            + request.requestType + " ainda não reconhecida!");
                    
            }
            
        } catch (Exception e) {
            response = new ResponseServer(ResponseType.ERRO, request.requestType);
            response.erroMessage = e.getMessage();
            //TODO colocar sistema de log.
            e.printStackTrace();
//            LOGGER.log(Level.WARNING, "" + response.erroMessage);
        }

        response.player = serverTask.getPlayer().getInf();
        
        return response;
    }

   
}

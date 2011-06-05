package trm.net.server;

import java.io.IOException;
import trm.net.server.game.RoomInf;
import trm.net.server.game.GameAction;
import trm.net.server.game.GameManager;
import java.util.ArrayList;
import java.util.List;
import trm.core.PlayerInf;
import trm.core.Stone;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestClient.Movement;
import trm.net.model.protocol.ResponseServer;
import trm.net.model.protocol.ResponseType;

/**
 *
 */
public class RequestHandlerImpl extends RequestHandler implements GameAction {

    private GameManager gameManager;

    public RequestHandlerImpl(ServerTask player) {
        super(player);

        gameManager = GameManager.getPlayerManager();
    }

    @Override
    public ResponseServer handle(RequestClient request) {
        
        ResponseServer response = new ResponseServer(ResponseType.ACK, 
                request.requestType);

        try {

            switch (request.requestType) {
                case ENTER_ROOM:
                    entryRoom(request.roomId);
                    break;
                case EXIT_ROOM:
                    exitRoom();
                    break;
                case LIST_ROOMS:
                    response.rooms = listRooms();
                    break;
                case LIST_HAND:
                    response.handStones = listHandPlayer();
                    break;
                case LIST_BOARD_STONES:
                    response.boardStones = listBoardStones();
                    break;
                case MOVE_STONE:
                    moveStone(request.movement, request.stone);
                    break;
                case POST_MESSAGE:
                    postMessage(request.chatMessage);
                    response.chatMessage = request.chatMessage;
                    break;
                case START_GAME:
                    startGame();
                    break;
                case END_GAME:
                    endGame();
                    break;
                case LIST_PLAYERS:
                    response.playersInGame = listPlayer();
                    break;
                case CREATE_ROOM:
                    response.newRoom = createRoomGame(request.newRoom);
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
        }
        
        response.senderPlayer = serverTask.getPlayer().getInf();

        return response;
    }

    @Override
    public void entryRoom(Long roomId) throws RuntimeException {
        gameManager.putPlayerRoom(serverTask, roomId);
    }

    @Override
    public void exitRoom() throws RuntimeException {
        gameManager.removePlayerRoom(serverTask);
    }

    @Override
    public List<RoomInf> listRooms() {
        return new ArrayList<RoomInf>(gameManager.findAllRooms());
    }

    @Override
    public List<Stone> listHandPlayer() {
      return gameManager.getHandPlayer(serverTask);
    }


    @Override
    public List<Stone> listBoardStones() throws RuntimeException {

        return gameManager.getBoardStones(serverTask);
    }

    @Override
    public void moveStone(Movement position, Stone stone) throws RuntimeException {

        gameManager.moveStone(position, stone, serverTask);
    }

    @Override
    public void postMessage(String message) {
        try {
            GameManager.getPlayerManager().postMessage(message, serverTask);
        } catch (IOException ex) {
            throw new RuntimeException("problema na conexão, tente novamente" +
                    "mais tarde");
        }
    }

    @Override
    public RoomInf createRoomGame(String roomName) {
        return gameManager.newRoomGame(roomName);
    }

    @Override
    public List<PlayerInf> listPlayer() {
        return gameManager.findPlayers(serverTask);
    }

    @Override
    public void startGame() {
        gameManager.startGame(serverTask);
    }

    @Override
    public void endGame() {
        gameManager.endGame(serverTask);
    }
}

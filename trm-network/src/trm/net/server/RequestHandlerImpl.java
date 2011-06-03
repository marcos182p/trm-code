package trm.net.server;

import java.io.IOException;
import trm.net.server.game.RoomInf;
import trm.net.server.game.RoomGame;
import trm.net.server.game.GameAction;
import trm.net.server.game.GameManager;
import java.util.ArrayList;
import java.util.List;
import trm.core.PlayerInf;
import trm.core.Stone;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestClient.Position;
import trm.net.model.protocol.RequestType;
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

        PlayerInf player = serverTask.getPlayer().getInf();

        RequestType requestType = request.requestType;
        requestType = requestType != null? requestType: RequestType.UNDEFINED;
        String ackMessage = null;
        String erroMessage = null;
        ResponseType responseType = ResponseType.ACK;
        List<Stone> handPlayer = null;
        List<Stone> boardStones = null;
        List<PlayerInf> players = null;
        List<RoomInf> rooms = null;
        String chatMessage = request.chatMessage;

        RoomInf newRoom = null;

        try {

            switch (requestType) {
                case ENTRY_ROOM:
                    entryRoom(request.roomId);
                    ackMessage = "Entrou na sala " + request.roomId + "!";
                    break;
                case EXIT_ROOM:
                    exitRoom();
                    ackMessage = "Saida da sala com sucesso!";
                    break;
                case LIST_ROOMS:
                    rooms = listRooms();
                    ackMessage = "Listagem de salas concluida com sucesso!";
                    break;
                case LIST_HAND:
                    handPlayer = listHandPlayer();
                    break;
                case LIST_BOARD_STONES:
                    boardStones = listBoardStones();
                    ackMessage = "Listagem de pedras concluida com sucesso!";
                    break;
                case MOVE_STONE:
                    moveStone(request.postionStone, request.stone);
                    ackMessage = "Pedra movida com sucesso!";
                    break;
                case POST_MESSAGE:
                    postMessage(chatMessage);
                    ackMessage = "Mensagem postada com sucesso!";
                    break;
                case START_GAME:
                    startGame();
                    break;
                case END_GAME:
                    endGame();
                    break;
                case LIST_PLAYERS:
                    players = listPlayer();
                    break;
                case CREATE_ROOM:
                    newRoom = createRoomGame(request.roomName);
                    break;
                case UNDEFINED:
                    throw new RuntimeException("tipo de mensagem não reconhecida!");
                default:
                    throw new RuntimeException("tipo de mensagem: " + requestType + " ainda não reconhecida!");
                    
            }

        } catch (Exception e) {

            erroMessage = e.getMessage();
            responseType = ResponseType.ERRO;
            e.printStackTrace();
        }

        return new ResponseServer(responseType, requestType, ackMessage, 
                erroMessage, rooms, handPlayer, boardStones, chatMessage,
                player, null,  players);
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
        List<RoomInf> roomInfs = new ArrayList<RoomInf>();

        for (RoomGame room : gameManager.findAllRooms()) {
            roomInfs.add(room.getRoomInf());
        }

        return roomInfs;
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
    public void moveStone(Position position, Stone stone) throws RuntimeException {

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

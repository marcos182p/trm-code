package trm.net.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.net.server.game.PlayerServer;
import trm.net.server.game.StatePlayer;
import trm.net.server.game.RoomInf;
import trm.net.server.game.RoomGame;
import trm.net.server.game.GameAction;
import trm.net.server.game.GameManager;
import java.util.ArrayList;
import java.util.List;
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

    public RequestHandlerImpl(ServerTask player) {
        super(player);
    }

    @Override
    public ResponseServer handle(RequestClient request) {

        PlayerServer player = serverTask.getPlayer();

        String userName = player.getInf().getNickName();
        RequestType requestType = request.getRequestType();
        String ackMessage = null;
        String erroMessage = null;
        ResponseType responseType = ResponseType.ACK;
        List<Stone> stones = null;
        List<RoomInf> rooms = null;
        String chatMessage = request.getChatMessage();


        try {

            switch (request.getRequestType()) {
                case ENTRY_ROOM:
                    entryRoom(request.getRoomGame());
                    ackMessage = "Entrou na sala " + request.getRoomGame() + "!";
                    break;
                case EXIT_ROOM:
                    exitRoom();
                    ackMessage = "Saida da sala com sucesso!";
                    break;
                case LIST_ROOMS:
                    rooms = listRooms();
                    ackMessage = "Listagem de salas concluida com sucesso!";
                    break;
                case LIST_STONES:
                    stones = listStones();
                    ackMessage = "Listagem de pedras concluida com sucesso!";
                    break;
                case MOVE_STONE:
                    moveStone(request.getPostionStone(), request.getStone());
                    ackMessage = "Pedra movida com sucesso!";
                    break;
                case POST_MESSAGE:
                    postMessage(chatMessage);
                    ackMessage = "Mensagem postada com sucesso!";
                    break;
                case UNDEFINED:
                    throw new RuntimeException("tipo de mensagem não reconhecida!");
            }

        } catch (Exception e) {

            erroMessage = e.getMessage();
            responseType = ResponseType.ERRO;
            e.printStackTrace();
        }

        return new ResponseServer(responseType, requestType, ackMessage, erroMessage, rooms, stones, chatMessage, player);
    }

    @Override
    public void entryRoom(Long roomId) throws RuntimeException {
        GameManager gameManager = GameManager.getPlayerManager();


        gameManager.putPlayerRoom(serverTask, roomId);
    }

    @Override
    public void exitRoom() throws RuntimeException {
        GameManager.getPlayerManager().removePlayerRoom(serverTask);

    }

    @Override
    public List<RoomInf> listRooms() {
        GameManager gameManager = GameManager.getPlayerManager();

        List<RoomInf> roomInfs = new ArrayList<RoomInf>();

        for (RoomGame room : gameManager.findAllRooms()) {
            roomInfs.add(room.getRoomInf());
        }

        return roomInfs;
    }

    @Override
    public List<Stone> listStones() throws RuntimeException {
        GameManager gameManager = GameManager.getPlayerManager();
        RoomGame room = gameManager.findRoomGameByPlayer(serverTask);

        if (room == null) {
            throw new RuntimeException("Usuario não está em uma sala.");
        }

        return room.getDominoesGame();
    }

    @Override
    public void moveStone(Position position, Stone stone) throws RuntimeException {

        GameManager gameManager = GameManager.getPlayerManager();

        gameManager.moveStone(position, stone, serverTask);
    }

    @Override
    public void postMessage(String message) {
        try {
            GameManager.getPlayerManager().postMessage(message, serverTask);
        } catch (IOException ex) {
            throw new RuntimeException("problema na conexão, tente novamente" + "mais tarde");
        }
    }
}

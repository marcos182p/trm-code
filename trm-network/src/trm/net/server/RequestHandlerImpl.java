package trm.net.server;

import java.util.ArrayList;
import java.util.List;
import trm.core.Stone;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestClient.Position;
import trm.net.model.protocol.RequestType;
import trm.net.model.protocol.ResponseServer;

/**
 *
 */
public class RequestHandlerImpl extends RequestHandler implements GameAction {
    
    public RequestHandlerImpl(ServerTask player) {
        super (player);
    }

    @Override
    public ResponseServer handle(RequestClient message) {
        
        PlayerServer player = serverTask.getPlayer();
        
        String userName = player.getInf().getNickName();
        RequestType requestType = message.getRequestType();
        
        try {

            switch (message.getRequestType()) {
                case ENTRY_ROOM:

                    entryRoom(message.getRoomGame());

                    break;
                case EXIT_ROOM:
                    break;
                case LIST_ROOMS:
                    
                    break;
                case LIST_STONES:

                    break;
                case MOVE_STONE:
                    
                    break;
                case POST_MESSAGE:
                    if (player.getState().equals(StatePlayer.PLAYING)) {
                        //ERRO!
                    }
                    break;

            }

        } catch (Exception e) {
        }
        
        return null;
    }

    @Override
    public void entryRoom(Long roomId) throws RuntimeException {
        GameManager gameManager = GameManager.getPlayerManager();
        RoomGame room = gameManager.findRoomById(roomId);
        
        gameManager.putPlayerRoom(serverTask, room);
        
    }

    @Override
    public void exitRoom() throws RuntimeException {
        GameManager.getPlayerManager().removePlayerRoom(serverTask);
    }

    @Override
    public List<RoomInf> listRooms() {
        GameManager gameManager = GameManager.getPlayerManager();
        
        List<RoomInf> roomInfs = new ArrayList<RoomInf>();
        
        for (RoomGame room: gameManager.findAllRooms()) {
            roomInfs.add(room.getRoomInf());
        }
        
        return roomInfs;
    }

    @Override
    public List<Stone> listStones() throws RuntimeException {
        GameManager gameManager = GameManager.getPlayerManager();
        RoomGame room = gameManager.findRoomGameByPlayer(serverTask);
        
        return room.getDominoesGame();
    }

    @Override
    public void moveStone(Position position, Stone stone) throws RuntimeException {
        
        GameManager gameManager = GameManager.getPlayerManager();
        RoomGame room = gameManager.findRoomGameByPlayer(serverTask);
        
        switch (position) {
            case LEFT:
                room.putLeft(stone, serverTask);
                break;
            case RIGHT:
                room.putRight(stone, serverTask);
                break;
        }
    }

    @Override
    public void postMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

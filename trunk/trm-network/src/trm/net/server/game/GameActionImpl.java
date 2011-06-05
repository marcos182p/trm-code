/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.net.server.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import trm.core.Movement;
import trm.core.PlayerInf;
import trm.core.Stone;
import trm.net.server.ServerTask;

/**
 *
 * @author Marcos
 */
public class GameActionImpl implements GameAction {
    
    private ServerTask serverTask;
    private GameManager gameManager;
    
    public GameActionImpl(ServerTask serverTask) {
        
        this.serverTask = serverTask;
        
        gameManager = GameManager.getPlayerManager();
    }
     @Override
    public void enterRoom(String roomName) throws RuntimeException {
        gameManager.putPlayerRoom(serverTask, roomName);
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
    public List<Stone> getHandPlayer() {
      return gameManager.getHandPlayer(serverTask);
    }

    @Override
    public List<Stone> getBoardStones() throws RuntimeException {

        return gameManager.getBoardStones(serverTask);
    }

    @Override
    public void moveStone(Movement movement) throws RuntimeException {

        gameManager.moveStone(movement, serverTask);
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
    public List<PlayerInf> getPlayers() {
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

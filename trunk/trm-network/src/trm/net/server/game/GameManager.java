package trm.net.server.game;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import trm.core.PlayerInf;
import trm.core.Stone;
import trm.net.model.protocol.RequestClient.Position;
import trm.net.server.ServerTask;

/**
 *
 * @author Marcos
 */
public class GameManager {

    private static GameManager instance = new GameManager();
    private long lastPlayerId = 0L;
    private long lastRoomGameId = 0L;
    private Set<PlayerServer> players;
    private Set<RoomGame> rooms;
    private Map<PlayerServer, RoomGame> roomsMap;

    private GameManager() {
        players = new HashSet<PlayerServer>();
        rooms = new HashSet<RoomGame>();
        roomsMap = new HashMap<PlayerServer, RoomGame>();

    }

    public static GameManager getPlayerManager() {
        return instance;
    }

    public PlayerServer newPlayer(String nickName) {
        PlayerServer player = new PlayerServer(
                new PlayerInf(lastPlayerId++, nickName));

        players.add(player);

        return player;
    }

    public RoomGame newRoomGame(String roomName) {
        RoomGame roomGame = new RoomGame(lastRoomGameId++, roomName);
        rooms.add(roomGame);
        return roomGame;
    }

    public Set<RoomGame> findAllRooms() {
        return Collections.unmodifiableSet(rooms);
    }
    
    public RoomGame findRoomById(Long id) {

        for (RoomGame room : rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }

        return null;
    }
    
    public RoomGame findRoomGameByPlayer(ServerTask player) {
        return roomsMap.get(player.getPlayer());
    }

    public void putPlayerRoom(ServerTask player, RoomGame roomGame) {

        if (!rooms.contains(roomGame)) {
            throw new RuntimeException("sala de jogo requerida não cadastrada");
        }

        if (!players.contains(player.getPlayer())) {
            throw new RuntimeException("jogador não cadastrado. reiniciar a"
                    + "conexão");
        }

        if (roomsMap.get(player.getPlayer()) != null) {
            throw new RuntimeException("jogador já cadastrado em uma sala de jogo");
        }

        roomGame.putServerTask(player);

        roomsMap.put(player.getPlayer(), roomGame);
    }

    public void removePlayerRoom(ServerTask player) {
        RoomGame room = roomsMap.get(player.getPlayer());

        if (room == null) {
            throw new RuntimeException("o usuario não podê sair da sala,"
                    + "pois não está cadastrado em nenhuma.");
        }
        
        room.removeServerTask(player);
        roomsMap.remove(player.getPlayer());
    }
    
     public void removePlayer(ServerTask player) {

        RoomGame room = roomsMap.get(player.getPlayer());
        
        if (room != null) {
             removePlayerRoom(player);
        }
        
        players.remove(player.getPlayer());

    }
    
    public void moveStone(Position position, Stone stone, ServerTask player){
        RoomGame room = findRoomGameByPlayer(player);
        
        if (room == null) {
            throw new RuntimeException("usario não pode mover a peça, "
                    + "pois não está em nenhuma sala.");
        }
        
        switch (position) {
            case LEFT:
                room.putLeft(stone, player);
                break;
            case RIGHT:
                room.putRight(stone, player);
                break;
        }
        
    }
    
    public void postMessage(String message, ServerTask serverTask) throws IOException {
        RoomGame room = findRoomGameByPlayer(serverTask);
        
        if (room == null) {
            throw new RuntimeException("usuario não pode postar mensagem,"
                    + "pois não está cadastrado em uma sala.");
        }
        
        room.broadcast(null, serverTask);
        //TODO terminar
    }
    
}

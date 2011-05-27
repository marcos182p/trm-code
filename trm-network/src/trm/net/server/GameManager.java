package trm.net.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import trm.core.PlayerInf;

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

    public boolean removePlayer(PlayerServer player) {

        RoomGame room = roomsMap.get(player);
        if (room != null) {
            
            for (ServerTask task : room.getServerTasks()) {
                if (task.getPlayer().equals(player)) {
                    roomsMap.get(player).removeServerTask(task);
                    break;
                }
            }
        }
        
        return players.remove(player);
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
            throw new RuntimeException("sala de jogo não cadastrada");
        }

        if (!players.contains(player.getPlayer())) {
            throw new RuntimeException("jogador não cadastrado");
        }

        if (roomsMap.get(player.getPlayer()) != null) {
            throw new RuntimeException("jogador já esta em uma sala de jogo");
        }

        roomGame.putServerTask(player);

        roomsMap.put(player.getPlayer(), roomGame);
    }

    public void removePlayerRoom(ServerTask player) {
        RoomGame room = roomsMap.get(player.getPlayer());

        if (room == null) {
            return;
        }
        
        room.removeServerTask(player);


    }
}

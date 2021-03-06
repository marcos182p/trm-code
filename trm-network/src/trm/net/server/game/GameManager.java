package trm.net.server.game;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import trm.core.PlayerInf;
import trm.core.Stone;
import trm.core.Movement;
import trm.core.lps.Event;
import trm.net.model.protocol.RequestType;
import trm.net.model.protocol.ResponseServer;
import trm.net.model.protocol.ResponseType;
import trm.net.server.ServerTask;

/**
 * @author TRM
 * @version 0.99
 */
public class GameManager {

    private static GameManager instance = new GameManager();
    private long lastPlayerId = 0L;
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

    public RoomInf newRoomGame(String roomName) {
        RoomGame roomGame = new RoomGame(roomName);
        
        if (rooms.contains(roomGame)) {
            throw new RuntimeException("Sala " + roomName + " ja criada.");
        }
        
        rooms.add(roomGame);

        return roomGame.getRoomInf();
    }

    public List<PlayerInf> findPlayers(ServerTask task) {
        RoomGame room = findRoomGameByPlayer(task);

        if (room == null) {
            throw new RuntimeException("Usuario não está em uma sala.Logo não"
                    + " pode listar usuarios de sala.");
        }
        return room.getPlayers();
    }

    public void startGame(ServerTask task) {
        RoomGame room = getRoomGame(task);
        room.startGame(task);

        ResponseServer response = new ResponseServer(ResponseType.ACK,
                RequestType.START_GAME);

        response.player = task.getPlayer().getInf();

        room.broadcast(response, task);
        

    }

    public void endGame(ServerTask task) {
        
        RoomGame room = getRoomGame(task);
        room.stopGame();

        ResponseServer response = new ResponseServer(ResponseType.ACK,
                RequestType.END_GAME);

        response.player = task.getPlayer().getInf();

        room.broadcast(response, task);

    }

    public Set<RoomInf> findAllRooms() {
        
        Set<RoomInf> roomInfs = new TreeSet<RoomInf>(new Comparator<RoomInf>() {

            @Override
            public int compare(RoomInf o1, RoomInf o2) {
                return o1.roomName.compareTo(o2.roomName);
            }
        });

        for (RoomGame room : rooms) {
            roomInfs.add(room.getRoomInf());
        }
        
        
        return roomInfs;
    }
    
    public Set<PlayerServer> findAllPlayers() {
        return players;
    }

    public RoomGame findRoomByName(String roomName) {

        for (RoomGame room : rooms) {
            if (room.getRoomGame().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    private RoomGame findRoomGameByPlayer(ServerTask player) {       
        return roomsMap.get(player.getPlayer());
    }

    public void putPlayerRoom(ServerTask player, String roomName) {
        RoomGame room = findRoomByName(roomName);

        if (room == null) {
            throw new RuntimeException("sala de jogo " + roomName
                    + " não cadastrada");
        }

        if (!players.contains(player.getPlayer())) {
            throw new RuntimeException("jogador não cadastrado. reiniciar a"
                    + "conexão");
        }

        if (roomsMap.get(player.getPlayer()) != null) {
            throw new RuntimeException("jogador já cadastrado em uma sala de "
                    + "jogo");
        }

        room.putServerTask(player);

        roomsMap.put(player.getPlayer(), room);

        ResponseServer response = new ResponseServer(ResponseType.ACK,
                RequestType.ENTER_ROOM);

        response.player = player.getPlayer().getInf();
        response.playersInGame = room.getPlayers();

        room.broadcast(response, player);
        
    }

    public void removePlayerRoom(ServerTask player) {
        RoomGame room = roomsMap.get(player.getPlayer());

        if (room == null) {
            throw new RuntimeException("o usuario não podê sair da sala,"
                    + "pois não está cadastrado em nenhuma.");
        }

        room.removeServerTask(player);
        roomsMap.remove(player.getPlayer());


        ResponseServer response = new ResponseServer(ResponseType.ACK,
                RequestType.EXIT_ROOM);

        response.player = player.getPlayer().getInf();
        response.playersInGame = room.getPlayers();

        if (room.getRoomInf().players == 0) {
            room.stopGame();
        }

        room.broadcast(response, player);
    }

    public void removePlayer(ServerTask player) {

        RoomGame room = roomsMap.get(player.getPlayer());
        
        if (room != null) {
            removePlayerRoom(player);
        } 
        
        players.remove(player.getPlayer());
    }

    public void moveStone(Movement movement, ServerTask player) {
        RoomGame room = findRoomGameByPlayer(player);
        
        if (room == null) {
            throw new RuntimeException("usario não pode mover a peça, "
                    + "pois não está em nenhuma sala.");
        }
        
        if (!room.isStarted()) {
            throw new RuntimeException("jogo não iniciado.");
        }
        
        if (movement == null) {
            throw new RuntimeException("movimento não especificado.");
        }
        
        if (movement.action == null) {
            throw new RuntimeException("ação não especificada");
        }
        
        ResponseServer movementResponse = new ResponseServer(ResponseType.ACK,
                RequestType.PUT_STONE);
        
        switch (movement.action) {
            case PUT_LEFT:
                room.putLeft(movement.stone, player);
                movement.stone = room.getBoardStones().get(0);
                break;
            case PUT_RIGHT:
                room.putRight(movement.stone, player);
                movement.stone = room.getBoardStones().get(
                        room.getBoardStones().size() - 1);
                break;
            case PASS:
                movement = new Movement(null, Movement.Action.PASS);
                room.putPass(player);
                break;
        }
        movementResponse.movement = movement;
        movementResponse.player = player.getPlayer().getInf();
        
        room.broadcast(movementResponse, player);

        PlayerInf winner = room.getWinner();
        
        if (winner != null) {
            room.stopGame();
            
            ResponseServer responseServer = new ResponseServer(ResponseType.ACK,
                    RequestType.END_GAME);

            responseServer.player = winner;
            
            room.broadcast(responseServer);
        }
    }

    public PlayerInf getWinner(ServerTask serverTask) {
        RoomGame room = findRoomGameByPlayer(serverTask);

        if (room == null) {
            throw new RuntimeException("usario não pode mover a peça, " +
                    "pois não está em nenhuma sala.");
        }
        
        return room.getWinner();
    }

    public void postMessage(String message, ServerTask serverTask) throws IOException {
        RoomGame room = findRoomGameByPlayer(serverTask);

        if (room == null) {
            throw new RuntimeException("usuario não pode postar mensagem," 
                    + "pois não está cadastrado em uma sala.");
        }

        ResponseServer response = ResponseServer.createResponseServer(message,
                serverTask.getPlayer());
        
        room.broadcast(response, serverTask);
        //TODO melhorar
        serverTask.getPlayer().notifyObservers(Event.MESSAGE);
        
    }

    public List<Stone> getHandPlayer(ServerTask serverTask) {
        RoomGame room = findRoomGameByPlayer(serverTask);

        if (room == null) {
            throw new RuntimeException("Usuario não está em uma sala.Logo não"
                    + " pode pegar as suas peças.");
        }
        
        return room.getHandPlayer(serverTask.getPlayer());
    }

    public List<Stone> getBoardStones(ServerTask serverTask) {
        RoomGame room = findRoomGameByPlayer(serverTask);

        if (room == null) {
            throw new RuntimeException("Usuario não está em uma sala. Logo não"
                    + " pode pegar as peças do tabuleiro.");
        }
        
        return room.getBoardStones();
    }

    private RoomGame getRoomGame(ServerTask player) {
        
        RoomGame room = findRoomGameByPlayer(player);

        if (room == null) {
            throw new RuntimeException("Usuario não está em uma sala.");
        }

        return room;
    }
    
}

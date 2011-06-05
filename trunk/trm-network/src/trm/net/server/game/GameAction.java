package trm.net.server.game;

import java.util.List;
import trm.core.PlayerInf;
import trm.core.Stone;
import trm.core.Movement;

/**
 * Ações que um jogador pode executar
 */
public interface GameAction {
    /**
     * entra em uma sala de jogo
     */
    void entryRoom(String roomName) throws RuntimeException;
    /**
     * sai da sala do jogo
     */
    void exitRoom() throws RuntimeException;
    /**
     * lista as salas do que tem para jogar
     */
    List<RoomInf> listRooms();
    /**
     * lista as peças da mão do jogador
     */
    List<Stone> listHandPlayer() throws RuntimeException;
    /**
     * lista as peças do tabuleiro do jogo
     */
    List<Stone> listBoardStones() throws RuntimeException;
    /**
     * Move uma peça no jogo
     */
    void moveStone(Movement position) throws RuntimeException;
    /**
     * posta uma mensagem no jogo
     */
    void postMessage(String message) throws RuntimeException;

    void startGame();

    void endGame();

    RoomInf createRoomGame(String roomName);

    List<PlayerInf> listPlayer();
}

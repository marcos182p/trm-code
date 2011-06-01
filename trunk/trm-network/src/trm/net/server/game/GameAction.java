package trm.net.server.game;

import java.util.List;
import trm.core.Stone;
import trm.net.model.protocol.RequestClient.Position;

/**
 * Ações que um jogador pode executar
 */
public interface GameAction {
    /**
     * entra em uma sala de jogo
     */
    void entryRoom(Long room) throws RuntimeException;
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
    void moveStone(Position position, Stone stone) throws RuntimeException;
    /**
     * posta uma mensagem no jogo
     */
    void postMessage(String message) throws RuntimeException;
}

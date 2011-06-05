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
    void enterRoom(String roomName) throws RuntimeException;
    /**
     * sai da sala do jogo
     */
    void exitRoom() throws RuntimeException;
    
    /**
     * Inicia o jogo na sala que o jogador estar
     */
    void startGame() throws RuntimeException;
    /**
     * finalisa o jogo na sala que o jogador estar
     */
    void endGame() throws RuntimeException;
    
    /**
     * lista as salas do que tem para jogar
     */
    List<RoomInf> listRooms();
    /**
     * lista as peças da mão do jogador
     */
    List<Stone> getHandPlayer() throws RuntimeException;
    /**
     * lista as peças do tabuleiro do jogo
     */
    List<Stone> getBoardStones() throws RuntimeException;
    /**
     * Move uma peça no jogo
     */
    void moveStone(Movement position) throws RuntimeException;
    /**
     * posta uma mensagem no jogo
     */
    void postMessage(String message) throws RuntimeException;
    /**
     * cria uma sala
     */
    RoomInf createRoomGame(String roomName);
    /**
     * lista os jogadores da sala do jogador, em ordem de jogada
     */
    List<PlayerInf> getPlayers();
}

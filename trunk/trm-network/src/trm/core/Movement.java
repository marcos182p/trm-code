package trm.core;


/**
 * Classe que representa uma jogado de um jogador
 */
public class Movement {

    public static enum Action {

        PUT_LEFT, PUT_RIGHT, PASS
    }

    /**
     * Peça que vai ser jogada no jogo
     */
    public Stone stone;
    /**
     * a posição que a peça vai ser jogada no jogo
     */
    public Action action;

    public Movement() {
    }

    public Movement(Stone stone, Action action) {
        this.stone = stone;
        this.action = action;
    }
}

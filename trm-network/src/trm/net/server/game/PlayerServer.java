package trm.net.server.game;

import trm.core.Player;
import trm.core.PlayerInf;

/**
 *
 */
public class PlayerServer extends Player {

    private StatePlayer state;

    public PlayerServer(PlayerInf inf) {
        super(inf);
        state = StatePlayer.NO_PLAYING;
    }

    public StatePlayer getState() {
        return state;
    }

    public void setState(StatePlayer state) {
        this.state = state;
        //FIXME noficar quando mudar de estado
    }
}

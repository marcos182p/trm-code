/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.view.game.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import trm.core.PlayerInf;
import trm.net.client.Listener;
import trm.net.model.protocol.ResponseServer;
import trm.view.game.utils.ListPanel;

/**
 *
 * @author Rafael
 */
public class PlayerList extends ListPanel implements Listener {

    private DefaultListModel model;
    private String currentPlayerComplement;
    private String waitingPlayerComplement;
    private static final String SEPARATOR = " - ";

    public PlayerList(String background) {
        super(background, "Players", new DefaultListModel());
        this.model = (DefaultListModel) getModel();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        currentPlayerComplement = "pensando";
        waitingPlayerComplement = "esperando";
    }

    public void setCurrentPlayerComplement(String s) {
        currentPlayerComplement = s;
    }

    public void setWaitingPlayerComplement(String s) {
        waitingPlayerComplement = s;
    }

    public void setPlayers(List<String> players) {
        model.clear();
        model.addElement(players.get(0) + SEPARATOR + currentPlayerComplement);
        for (int i = 1; i < players.size(); i++) {
            model.addElement(players.get(i) + SEPARATOR + waitingPlayerComplement);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(PlayerList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        repaint();
    }

    @Override
    public void update(ResponseServer response) {
        List<PlayerInf> inf = response.playersInGame;
        List<String> players = new ArrayList<String>();
        for (PlayerInf i : inf) {
            players.add(i.getNickName());
        }
        
        setPlayers(players);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.view.game.player;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import trm.view.game.utils.ListPanel;

/**
 *
 * @author Rafael
 */
public class PlayerList extends ListPanel{
    
    private DefaultListModel model;
    private String currentPlayerComplement;
    private String waitingPlayerComplement;
    private static final String SEPARATOR = " - ";
    
    public PlayerList(String background) {
        super(background, "Players", new DefaultListModel());
        this.model = (DefaultListModel)getModel();
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
    
    public void setPlayers(String... players) {
        model.clear();
        model.addElement(players[0] + SEPARATOR + currentPlayerComplement);
        for(int i = 1; i < players.length; i++) {
            model.addElement(players[i] + SEPARATOR + waitingPlayerComplement);
        }
        repaint();
    }
}

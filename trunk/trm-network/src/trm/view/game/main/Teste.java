/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.main;

import trm.core.SquareNumber;
import trm.view.game.utils.ResourceWindow;

/**
 *
 * @author rafanet
 */
public class Teste {
    public static void main(String[] args) throws Exception {

        ResourceWindow.setBG("board1.png");
        ResourceWindow.setPanelImage("wood");
        GameScreen screen = new GameScreen();
        screen.addPlayerPiece(SquareNumber.ZERO, SquareNumber.ZERO);
        screen.open();
        
    }
}

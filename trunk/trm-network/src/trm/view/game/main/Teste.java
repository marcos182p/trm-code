/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.main;

import trm.view.game.utils.ResourceWindow;

/**
 *
 * @author rafanet
 */
public class Teste {
    public static void main(String[] args) throws Exception {

        ResourceWindow.setBG("glass");
        ResourceWindow.setPanelImage("board2.png");
        GameScreen screen = new GameScreen("Marcos", "teste room");
        
        screen.open();
        
    }
}

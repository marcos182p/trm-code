/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.main;

import trm.view.game.login.LoginScreen;
import trm.view.game.utils.ResourceWindow;

/**
 *
 * @author rafanet
 */
public class Teste {
    public static void main(String[] args) throws Exception {

        String theme = "wood";
        ResourceWindow.setBG(theme + "bg");
        ResourceWindow.setPanelImage(theme + "panel");
        //GameScreen screen = new GameScreen("Rafael", "localhost", "teste room");
        LoginScreen screen = new LoginScreen();
        screen.open();
        
    }
}

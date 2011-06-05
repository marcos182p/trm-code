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
        GameScreen screen = new GameScreen("jogador 2", "teste room");
        screen.addPlayerPiece(SquareNumber.ZERO, SquareNumber.ZERO);
        screen.addPlayerPiece(SquareNumber.SIX, SquareNumber.ZERO);
        screen.addPlayerPiece(SquareNumber.FIVE, SquareNumber.THREE);
        screen.addPlayerPiece(SquareNumber.ONE, SquareNumber.ONE);
        screen.addPlayerPiece(SquareNumber.TWO, SquareNumber.FOUR);
        screen.addPlayerPiece(SquareNumber.THREE, SquareNumber.ZERO);
        screen.addPlayerPiece(SquareNumber.SIX, SquareNumber.SIX);
        screen.open();
        
    }
}

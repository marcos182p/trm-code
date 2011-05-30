/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import trm.core.SquareNumber;
import trm.core.Stone;

/**
 *
 * @author Rafael
 */
public class TestDraw extends JFrame{

    private BoardPanel bp;
    private ChatPanel chatPanel;

    public TestDraw() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bp = new BoardPanel("board1", 14, 14, Color.GREEN, Color.RED);
        chatPanel = new ChatPanel();
        config();
        pack();
    }

    private void config() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 15;
        c.ipady = 15;
        c.gridx = 0;
        c.gridy = 0;
        add(bp, c);
        c.gridy = 1;
        add(chatPanel,c);

        testStones();
    }

    private void testStones() {
        addStone(SquareNumber.SIX, SquareNumber.SIX, 6, 6, Orientation.EAST, true);
        addStone(SquareNumber.THREE, SquareNumber.SIX, 7, 5, Orientation.EAST, false);
        addStone(SquareNumber.FOUR, SquareNumber.THREE, 6, 4, Orientation.EAST, false);
        addStone(SquareNumber.SIX, SquareNumber.ONE, 7, 7, Orientation.EAST, false);
        addStone(SquareNumber.FOUR, SquareNumber.FOUR, 7,3, Orientation.EAST, true);
        addStone(SquareNumber.FOUR, SquareNumber.FIVE, 6, 2, Orientation.EAST, false);
        addStone(SquareNumber.FOUR, SquareNumber.FIVE, 7, 1, Orientation.EAST, false);
        addStone(SquareNumber.FOUR, SquareNumber.FIVE, 6,0, Orientation.EAST, false);
        addStone(SquareNumber.FOUR, SquareNumber.FIVE, 7,0, Orientation.SOUTH, false);
    }

    private void addStone(
            SquareNumber left, SquareNumber right,
            int row, int col,
            Orientation o, boolean player) {
            Stone s = new Stone(left, right);
            bp.addStone(s, row, col, o, player);
        
    }

    public void open() {
        setVisible(true);
    }

    public static void main(String[] args) throws Exception{
        TestDraw td = new TestDraw();
        td.open();
    }
}

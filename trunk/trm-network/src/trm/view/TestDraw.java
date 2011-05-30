/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

    private BoardPanel board;
    private ChatPanel chatPanel;
    private PlayerPanel playerPanel;

    public TestDraw() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color playerColor = Color.ORANGE;
        Color othersColor = Color.DARK_GRAY;
        board = new BoardPanel("board2", 14, 14, playerColor, othersColor);
        chatPanel = new ChatPanel();
        playerPanel = new PlayerPanel(playerColor);
        playerPanel.setPieces(new Stone(SquareNumber.SIX, SquareNumber.SIX));
        playerPanel.addPiece(new Stone(SquareNumber.SIX, SquareNumber.ONE));
        playerPanel.addPiece(new Stone(SquareNumber.FOUR, SquareNumber.FOUR));
        playerPanel.addPiece(new Stone(SquareNumber.THREE, SquareNumber.ONE));
        playerPanel.addPiece(new Stone(SquareNumber.ZERO, SquareNumber.TWO));
        playerPanel.addPiece(new Stone(SquareNumber.ONE, SquareNumber.ONE));
        playerPanel.addPiece(new Stone(SquareNumber.FIVE, SquareNumber.THREE));
        config();
        pack();
        setResizable(false);
    }

    private void config() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        add(board, c);
        c.gridy = 1;
        add(playerPanel, c);
        c.gridy = 2;
        add(chatPanel,c);
        

        //testStones();
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
        addStone(SquareNumber.FOUR, SquareNumber.FIVE, 7,0, Orientation.SOUTH, true);
    }

    private void addStone(
            SquareNumber left, SquareNumber right,
            int row, int col,
            Orientation o, boolean player) {
            Stone s = new Stone(left, right);
            board.addStone(s, row, col, o, player);
        
    }

    public void open() {
        setVisible(true);
    }

    public static void main(String[] args) throws Exception{
        TestDraw td = new TestDraw();
        td.open();
        td.testStones();
    }
}

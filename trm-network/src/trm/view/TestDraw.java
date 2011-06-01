/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

        setSize(800,800);
        Color playerColor = Color.ORANGE;
        Color othersColor = Color.DARK_GRAY;
        board = new BoardPanel("board1.png", 15, 15, playerColor, othersColor);
        chatPanel = new ChatPanel("board1.png");
        playerPanel = new PlayerPanel("board1.png", board, playerColor);
        playerPanel.setPieces(new Stone(SquareNumber.SIX, SquareNumber.SIX));
        playerPanel.addPiece(new Stone(SquareNumber.SIX, SquareNumber.ONE));
        playerPanel.addPiece(new Stone(SquareNumber.FOUR, SquareNumber.FOUR));
        playerPanel.addPiece(new Stone(SquareNumber.THREE, SquareNumber.ONE));
        playerPanel.addPiece(new Stone(SquareNumber.ZERO, SquareNumber.TWO));
        playerPanel.addPiece(new Stone(SquareNumber.ONE, SquareNumber.ONE));
        playerPanel.addPiece(new Stone(SquareNumber.FIVE, SquareNumber.THREE));
        config();
        //pack();
        setResizable(false);
    }

    private void config() {
        JPanel p = new AllPanel("wood.jpg");

        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        p.add(board, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        p.add(playerPanel, c);
        c.gridy = 2;
        
        p.add(chatPanel,c);
        getContentPane().add(p);
    }

    private void testStones() {
       Stone s = new Stone(SquareNumber.FIVE, SquareNumber.THREE);
       int n = 10;
       boolean player = true;
       for(int i = 1; i <= n; i++) {
            board.putStone(s, GameSide.RIGHT, StoneSide.UP, player);
            player = i%4 == 0;
       }
    }

    private void addStone(
            SquareNumber left, SquareNumber right,
            GameSide gameSide, StoneSide stoneSide, boolean player) {
        
            Stone s = new Stone(left, right);
            board.putStone(s, gameSide, stoneSide, player);
        
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

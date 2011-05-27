/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Color;
import javax.swing.JFrame;
import trm.core.SquareNumber;
import trm.core.Stone;

/**
 *
 * @author Rafael
 */
public class TestDraw extends JFrame{

    private DrawPanel dp = new DrawPanel();
    private Color[] playerColors = {Color.BLUE, Color.YELLOW};
    private int index = 0;

    public TestDraw() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        addPeca(SquareNumber.SIX, SquareNumber.SIX, 5, 5, Orientation.EAST);
        addPeca(SquareNumber.FOUR, SquareNumber.SIX, 4, 4, Orientation.SOUTH);
        addPeca(SquareNumber.THREE, SquareNumber.FOUR, 3, 3, Orientation.EAST);
        addPeca(SquareNumber.SIX, SquareNumber.THREE, 5, 7, Orientation.EAST);
        addPeca(SquareNumber.THREE, SquareNumber.THREE, 3, 8, Orientation.NORTH);
        addPeca(SquareNumber.THREE, SquareNumber.TWO, 2, 7, Orientation.WEST);
        add(dp);
        pack();
    }

    private void addPeca(
            SquareNumber left, SquareNumber right,
            int row, int col,
            Orientation o) {
        Stone s = new Stone(left, right);
        index= (index+1)%2;
        DominoView dv = new DominoView(s, row, col, o, playerColors[index]);
        dp.addDrawable(dv);
        
    }

    public void open() {
        setVisible(true);
    }

    public static void main(String[] args) {
        TestDraw td = new TestDraw();
        td.open();
    }
}

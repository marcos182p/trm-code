/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import javax.swing.JFrame;
import trm.core.SquareNumber;
import trm.core.Stone;

/**
 *
 * @author Rafael
 */
public class TestDraw extends JFrame{

    private DrawPanel dp = new DrawPanel();

    public TestDraw() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        addPeca(SquareNumber.SIX, SquareNumber.SIX, 5, 5, Orientation.WEST);
        add(dp);
        pack();
    }

    private void addPeca(
            SquareNumber left, SquareNumber right,
            int row, int col,
            Orientation o) {
        Stone s = new Stone(left, right);
        DominoView dv = new DominoView(s, row, col, o);
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

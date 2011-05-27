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


    public TestDraw() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        DrawPanel panel = new DrawPanel();
        Stone s = new Stone(SquareNumber.TWO, SquareNumber.FOUR);
        DominoView dv = new DominoView(s, 5, 5, Orientation.NORTH);
        panel.addDrawable(dv);
        add(panel);
        pack();
    }

    public void open() {
        setVisible(true);
    }

    public static void main(String[] args) {
        TestDraw td = new TestDraw();
        td.open();
    }
}

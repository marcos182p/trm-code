package trm.missile.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PositionPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel xLabel;
    private JTextField xField;
    private JLabel yLabel;
    private JTextField yField;

    public PositionPanel() {
        xLabel = new JLabel("X:");
        xField = new JTextField("");
        yLabel = new JLabel("Y:");
        yField = new JTextField("");
        setup();
    }

    private void setup() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 5, 5, 5);
        add(xLabel, c);
        c.weightx = 50;
        c.gridx = 1;
        add(xField, c);
        c.weightx = 1;
        c.gridx = 2;
        add(yLabel, c);
        c.weightx = 50;
        c.gridx = 3;
        add(yField, c);
    }
}

package trm.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author mpjms
 */
public class InfoPanel extends JPanel {

    private JLabel initialPosition;
    private JLabel finalPosition;
    private JLabel angle;
    private JLabel intensity;
    private JTextField initialField;
    private JTextField finalField;
    private JTextField angleField;
    private JTextField intensityField;
    private JSlider angleSlider;
    private JButton shoot;

    public InfoPanel() {
        initialPosition = new JLabel("Posição inicial");
        finalPosition = new JLabel("Posição final");
        angle = new JLabel("Angulo");
        intensity = new JLabel("Intensidade");

        initialField = new JTextField("xxxx");
        finalField = new JTextField("xxxx");
        angleField = new JTextField("xxxx");
        angleField.setEditable(false);
        intensityField = new JTextField("xxxx");

        angleSlider = new JSlider();
        angleSlider.setMinimum(0);
        angleSlider.setMaximum(360);
        angleSlider.addChangeListener(new AngleChangeListener(angleField, angleSlider));
        angleSlider.setValue(0);


        shoot = new JButton("Chute");

        init();

    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        //----------------------------------------------------------------------
        gbc.gridx = 1;

        gbc.gridy = gbc.gridy++;

        gbc.gridwidth = 2;

        add(initialPosition, gbc);

        gbc.gridy = gbc.gridy++;

        add(initialField, gbc);
        //----------------------------------------------------------------------

        gbc.gridy = gbc.gridy++;

        add(finalPosition, gbc);

        gbc.gridy = gbc.gridy++;

        add(finalField, gbc);

        //----------------------------------------------------------------------
        gbc.gridy = gbc.gridy++;

        add(angle, gbc);

        gbc.gridy = gbc.gridy++;

        gbc.gridwidth = 1;
        gbc.weightx = 1;

        add(angleSlider, gbc);

        gbc.gridx = 2;

        gbc.weightx = 0.6;

        add(angleField, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.gridwidth = 2;
        //----------------------------------------------------------------------
        gbc.gridy = gbc.gridy++;

        add(intensity, gbc);

        gbc.gridy = gbc.gridy++;

        add(intensityField, gbc);
        //----------------------------------------------------------------------

        gbc.gridy = gbc.gridy++;

        add(shoot, gbc);

        //----------------------------------------------------------------------

        gbc.gridy = gbc.gridy++;
        gbc.weighty = 50;
        add(new JPanel(), gbc);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(200, 300));
        frame.add(new InfoPanel());
        frame.setVisible(true);
    }
}

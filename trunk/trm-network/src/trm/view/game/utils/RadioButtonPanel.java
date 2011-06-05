package trm.view.game.utils;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class RadioButtonPanel extends BGPanel {

    /**Variavel que indica a posicao do campo em relacao ao label
     * no construtor, coloca o campo ao lado do label*/
    public static final int HORIZONTAL = 0;
    /**Variavel que indica a posicao do campo em relacao ao label
     * no construtor, coloca o campo abaixo do label*/
    public static final int VERTICAL = 1;
    private JLabel label;
    private List<JRadioButton> buttons;
    private int radioButtonsPosition;

    public RadioButtonPanel(String bg, String label, int radioButtonsPosition, String... radioButtonNames) {
        super(bg);
        this.radioButtonsPosition = radioButtonsPosition;
        this.buttons = new ArrayList<JRadioButton>();
        this.label = new JLabel(label);
        for (String name : radioButtonNames) {
            JRadioButton button = new JRadioButton(name);
            button.setBackground(new Color(0, 0, 0, 0));
            button.setOpaque(false);
            button.addActionListener(new RadioButtonListener(this, button));
            buttons.add(button);
        }
        setup();
    }

    public void clear() {
        for (JRadioButton b : buttons) {
            b.setSelected(false);
        }
    }

    public void select(JRadioButton button) {
        if (buttons.contains(button)) {
            button.setSelected(true);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (JRadioButton b : buttons) {
            b.setEnabled(enabled);
        }
    }

    public JRadioButton getRadioButton(String buttonName) {
        for (JRadioButton button : buttons) {
            if (button.getText().equals(buttonName)) {
                return button;
            }
        }
        return null;
    }

    public String getSelectedButtonName() {
        for (JRadioButton b : buttons) {
            if (b.isSelected()) {
                return b.getText();
            }
        }
        return null;
    }

    private void setup() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        add(label, c);
        c.weighty = 1;
        c.weightx = 1;
        int jumpX = 0;
        int jumpY = 0;
        if (radioButtonsPosition == VERTICAL) {
            jumpY = 1;
        } else if (radioButtonsPosition == HORIZONTAL) {
            jumpX = 1;
        }
        c.insets = new Insets(5, 0, 5, 0);
        for (JRadioButton button : buttons) {
            c.gridx += jumpX;
            c.gridy += jumpY;
            add(button, c);
        }

    }
}

package trm.view.game.utils;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Cria um Painel com um label e um comboBox
 */
public class ComboBoxPanel extends BGPanel {

    /**
     * Variavel que indica a posicao do campo em relacao ao label
     * no construtor, coloca o campo ao lado do label
     */
    public static final int BY_SIDE = 0;
    /**
     * Variavel que indica a posicao do campo em relacao ao label
     * no construtor, coloca o campo abaixo do label
     */
    public static final int BELOW = 1;
    private JLabel label;
    private JComboBox comboBox;
    private int comboBoxPosition;

    public ComboBoxPanel(String bg, String label, int comboBoxPosition) {
        super(bg);
        this.label = new JLabel(label);
        this.comboBox = new JComboBox();
        this.comboBoxPosition = comboBoxPosition;
        setup();
    }

    public void setContent(Collection<String> content) {
        for (String s : content) {
            comboBox.addItem(s);
        }
    }

    public String getSelected() {
        return (String) comboBox.getSelectedItem();
    }

    private void setup() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.insets = new Insets(5, 5, 5, 5);
        add(label, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        if (comboBoxPosition == BELOW) {
            c.gridy = 1;
        } else if (comboBoxPosition == BY_SIDE) {
            c.gridx = 1;
        }
        add(comboBox, c);
    }
}

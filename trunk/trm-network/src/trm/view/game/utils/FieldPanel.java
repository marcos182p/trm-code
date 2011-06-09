package trm.view.game.utils;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Cria um painel com um label e um campo de texto ao lado
 */
public class FieldPanel extends BGPanel {

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
    private JTextField field;
    private int fieldPosition;

    /**
     * Especifica a imagem de fundo, nome do label que antecede o field, numero 
     * de colunas, se o campo eh um campo de senha e portanto não mostra os 
     * caracteres da palabra, e a posicao do campo em relação ao label definida 
     * por constantes da classe
     */
    public FieldPanel(String bg, String label, int fieldColumns, 
            boolean isPassword, int fieldPosition) {
        super(bg);
        this.label = new JLabel(label);
        if (isPassword) {
            this.field = new JPasswordField(fieldColumns);

        } else {
            this.field = new JTextField(fieldColumns);
        }
        this.fieldPosition = fieldPosition;
        setup();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        field.setEnabled(enabled);
    }

    public void clear() {
        field.setText("");
    }

    public String getContent() {
        return field.getText();
    }

    public void setContent(String content) {
        this.field.setText(content);
    }

    public JTextField getField() {
        return this.field;
    }

    private void setup() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        add(label, c);
        c.weightx = 1;
        if (fieldPosition == BELOW) {
            c.gridy = 1;
        }
        add(field, c);
    }
}

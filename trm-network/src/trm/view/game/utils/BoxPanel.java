package trm.view.game.utils;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 * Cria um painel com um label
 * e uma caixa de texto em baixo
 */
public class BoxPanel extends BGPanel {

    private JLabel label;
    private JTextPane messageArea;

    public BoxPanel(String bg, String label) {
        super(bg);
        this.label = new JLabel(label);
        this.messageArea = new JTextPane();
        setup();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.messageArea.setEnabled(enabled);
    }

    public void clear() {
        messageArea.setText("");
    }

    public String getContent() {
        return messageArea.getText();
    }

    public void setContent(String content) {
        this.messageArea.setText(content);
    }

    private void setup() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JScrollPane pane = new JScrollPane(messageArea);
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridy = 0;
        add(label, c);
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.gridy = 1;
        add(pane, c);
    }
}

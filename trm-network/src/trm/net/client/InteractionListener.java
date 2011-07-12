package trm.net.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import trm.core.lps.StackUtil;
import trm.net.model.protocol.RequestType;
import trm.net.model.protocol.ResponseServer;

/**
 *
 * @author Marcos
 */
public class InteractionListener {

    private Map<Long, Stack> stacks;
    private GUIInteraction guiInteraction;

    public InteractionListener(ClientTask clientTask) {

        stacks = new HashMap<Long, Stack>();

        StackListener stackListener = new StackListener();

        clientTask.subscribe(RequestType.LOGIN, stackListener);
        clientTask.subscribe(RequestType.START_GAME, stackListener);
        clientTask.subscribe(RequestType.PUT_STONE, stackListener);
        clientTask.subscribe(RequestType.PUT_MESSAGE, stackListener);
        
        guiInteraction = new GUIInteraction();

    }

    public void register(Long requestId) {
        stacks.put(requestId, new Stack());
    }

    private class StackListener implements Listener {

        @Override
        public void update(ResponseServer response) {

            if (stacks.get(response.id) != null) {
                
                stacks.get(response.id).addAll(response.stack);

                Stack stack = stacks.remove(response.id);
                //fazer alguma coisa com a stack
                guiInteraction.textArea.append("nova interação:\n");
                for ( StackTraceElement element:stack.stack ) {
                    guiInteraction.textArea.append(element.toString() + "\n");
                }
            }
        }
    }

    private class Stack {

        public List<StackTraceElement> stack;

        private Stack() {
            stack = StackUtil.filterStack(new Exception().getStackTrace());
        }

        public void addAll(List<StackTraceElement> otherStack) {
            this.stack.addAll(0, otherStack);
        }
    }
    
    public static void main(String[] args) {
        
    }
}

class GUIInteraction extends JFrame {
    
    public JTextArea textArea;

    public GUIInteraction() {
        
        setLayout(new BorderLayout());
        
        textArea = new JTextArea(30, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(new Label("Interações:"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        pack();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

    }
    
    
    
}

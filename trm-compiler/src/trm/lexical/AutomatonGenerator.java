package trm.lexical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Marcos Paulo
 */
public class AutomatonGenerator {

    private BufferedReader reader;
    private Automaton automaton;

    public AutomatonGenerator(String path) {
        try {
            reader = new BufferedReader(new FileReader(new File(path)));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void generate() throws IOException {
        
        State initialState = new State(reader.readLine().trim());
        Set<State> finalStates = generateStates(reader.readLine().trim());
        
        Set<Transition> transitions = generateTransitions();


        automaton = new Automaton(initialState, new ArrayList<State>(finalStates));

        for (Transition transition : transitions) {
            automaton.addTransition(transition);
        }
    }

    public static void main(String[] args) {
        String temp = " A -> A, white ".trim();
        for(String string:temp.split(",")) {
            System.out.println(string);
        }

    }

    private Set<State> generateStates(String line) {
        Set<State> result = new HashSet<State>();
        for (String label : line.split(",")) {
            result.add(new State(label.trim()));
        }
        return result;
    }

    private Map<String, State> states = new HashMap<String, State>();
    
    private Set<Transition> generateTransitions() throws IOException {

        Set<Transition> result = new HashSet<Transition>();

        
        states.put(automaton.getStartState().getLabel(), automaton.getStartState());
        
        for (State state : automaton.getFinalStates()) {
            states.put(state.getLabel(), state);
        }
        
        String line = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            automaton.addTransition(generateTransition(line));

        }

        return result;
    }

    private Transition generateTransition(String line) {

        Transition transition = null;

        String[] temp = line.split(",");
        String[] labels = temp[0].split("->");


        //FIX ME melhorar isso!
        //gerando a origem e o destino.
        State source = states.get(labels[0].trim());

        if (source == null) {
            source = new State(labels[0].trim());
            states.put(labels[0].trim(), source);
        }
        State target = states.get(labels[1].trim());
        if (target == null) {
            target = new State(labels[1].trim());
            states.put(labels[1].trim(), target);
        }
        //fim da geração da origem e do destino.

        String rule = temp[1];

        return transition;
    }

}

package trm.lexical;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import trm.lexical.rules.Rule;

public class Automaton {

    private Map<State, Set<Transition>> automaton;
    private State startState;
    private List<State> finalStates;

    public Automaton(State startState, State... endStates) {

        this.startState = startState;
        this.finalStates = Arrays.asList(endStates);

        this.automaton = new HashMap<State, Set<Transition>>();
    }

    public Automaton(State startState, List<State> endStates) {

        this.startState = startState;
        this.finalStates = endStates;

        this.automaton = new HashMap<State, Set<Transition>>();
    }

    public Automaton(State start, State end) {

        this(start, new State[]{end});
    }

    public void addTransition(State source, State target, Rule rule) {

        addTransition(new Transition(source, target, rule));
    }

    public void addTransition(Transition transition) {

        if (!automaton.containsKey(transition.getSource())) {
            automaton.put(transition.getSource(), new HashSet<Transition>());
        }

        automaton.get(transition.getSource()).add(transition);
    }

    public Set<Transition> getTransitions(State state) {

        return automaton.get(state);
    }

    public State getStartState() {
        return startState;
    }

    public List<State> getFinalStates() {
        return finalStates;
    }

    /**
     * Procura uma transição que sai do estado 'source' e leia o caracter 'c'.
     * @param source estado fonte.
     * @param c caracter a ser lido.
     * @return uma transição que saia de source e que consiga ler o c.
     */
    //FIXME colocar nome de metodo mais sugestivo.
    public State nextState(State source, char c) {
        System.out.println(source.getLabel());
        Set<Transition> trasition = getTransitions(source);

        if (trasition != null) {

            for (Transition transition : getTransitions(source)) {
                if (transition.accept(c)) {
                    return transition.getTarget();
                }
            }
        }
        return null;
    }
}

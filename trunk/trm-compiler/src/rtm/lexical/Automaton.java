package rtm.lexical;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import rtm.lexical.rules.Rule;

public class Automaton {

    private Map<State, Set<Transition>> automaton;
    
    private State startState;
    private List<State> finalStates;
    
    public Automaton(State startState, State... endStates) {

        this.startState = startState;
        this.finalStates = Arrays.asList(endStates);
        
        this.automaton = new HashMap<State, Set<Transition>>();
    }

    public Automaton(State start, State end) {

        this(start, new State[]{end});
    }

    public void addTransition(State source, State target, Rule rule) {
        
        if (!automaton.containsKey(source)) {
            automaton.put(source, new HashSet<Transition>());
        }

        automaton.get(source).add(new Transition(source, target, rule));
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
    public Transition findTransition(State source, char c) {

        for (Transition transition : getTransitions(source)) {
            if (transition.accept(c)) {
                return transition;
            }
        }

        return null;
    }
}

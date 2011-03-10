package rtm.lexical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rtm.lexical.rules.Rule;

public class Automaton {

    private Map<Token, List<Transition>> automaton;
    
    private Token startState;
    private Token lastState;
    
    private Token currentState;
    
    private boolean halted = false;

    public Automaton(Token start) {
        this.automaton = new HashMap<Token, List<Transition>>();
        this.startState = start;
        this.currentState = start;
        this.lastState = null;
        this.halted = false;
        this.currentState.setValue("");
    }

    public void addTransition(Token source, Token target, Rule rule) {
        if (!automaton.containsKey(source)) {
            automaton.put(source, new ArrayList<Transition>());
        }
        automaton.get(source).add(new Transition(source, target, rule));
    }

    public void reset() {
        this.currentState = startState;
        this.lastState = null;
        this.halted = false;
        this.currentState.setValue("");
    }

    public Token getCurrentState() {
        return currentState;
    }

    public Token getLastState() {
        return lastState;
    }

    public boolean hasHalted() {
        return this.halted;
    }

    public String getCurrentValue() {
        return this.currentState.getValue();
    }

    public TokenClass getCurrentToken() {
        return this.currentState.getTokenClass();
    }

    private void checkStart() {
        if (currentState.equals(startState)) {
            reset();
        }
    }

    public void transition(char c) {
        halted = true;
        for (Transition r : automaton.get(currentState)) {
            if (r.accept(c)) {
                lastState = currentState;
                currentState = r.getTarget();
                halted = false;
                this.currentState.setValue(lastState.getValue() + c);
                checkStart();
                return;
            }
        }

    }
}

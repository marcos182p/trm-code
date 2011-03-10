package rtm.lexical;

/**
 * Classe que verifica se uma fita é reconhecida por um automato.
 */
public class Recognizer {

    private Automaton automaton;

    private char[] tape;
    
    private State currentState;
    private State lastState;
    
    private boolean recognized;

    public Recognizer(Automaton automaton, char[] tape) {

        this.automaton = automaton;
        
        this.lastState = null;
        this.currentState = automaton.getStartState();
        
        this.recognized = false;
    }

    /**
     * Verifica se a fita é reconhecida pelo automato.
     */
    public void run() {

        for (char c : tape) {
            transition(c);
        }

        if(automaton.getFinalStates().contains(currentState)) {
            recognized = true;
        }
    }

    public boolean isRecognized() {
        return recognized;
    }

    private void transition(char c) throws RuntimeException {
        
        Transition transition = automaton.findTransition(currentState, c);

        if (transition == null) {
            throw new RuntimeException("Impossivel ir para outro estado.");
        }

        lastState = currentState;
        currentState = transition.getTarget();

    }

}

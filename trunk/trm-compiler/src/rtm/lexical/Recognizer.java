package rtm.lexical;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que verifica se uma fita é reconhecida por um automato.
 */
public class Recognizer {

    private Automaton automaton;

    private char[] tape;
    
    private State currentState;

    private List<Token> tokens;
    
    private boolean recognized;

    public Recognizer(Automaton automaton, char[] tape) {

        this.automaton = automaton;

        this.tape = tape;
        
        this.recognized = false;

        this.tokens = new ArrayList<Token>();

        reset();
    }

    private void reset() {
        this.currentState = automaton.getStartState();
    }

    /**
     * Verifica se a fita é reconhecida pelo automato.
     */
    public void run() {

        StringBuilder word = new StringBuilder();

        for (char c : tape) {

            try {
                
                transition(c);
                word.append(c);
                
            } catch (TransitionException e) {
                
                if(!automaton.getFinalStates().contains(currentState)) {
                    throw new RuntimeException("Impossivel ler fita", e);
                }

                Token token = new Token(getTokenClass(currentState));
                token.setValue(word.toString().trim());

                tokens.add(token);

                word = new StringBuilder();
                reset();

            }
        }

        if(automaton.getFinalStates().contains(currentState)) {
            recognized = true;
        }
    }

    //FIXME terminar de implementar esse metodo.
    private TokenClass getTokenClass(State state) {
        
        TokenClass result = TokenClass.TK_UNDEFINED;

        switch(state.getDescription().getWordType()) {

            case WORD:
                //verificar se a palavra é reservada.  
                //caso não for, sera um identificador.
                break;
            case INTEGER_CTE:
                result = TokenClass.TK_INTEGER_CTE;
                break;

        }

        return result;
    }

    public boolean isRecognized() {
        return recognized;
    }

    private void transition(char c) {

        Transition transition = automaton.findTransition(currentState, c);

        if (transition == null) {
            throw new TransitionException("Transição para esse valor não "
                    + "encontrada.");
        }

        currentState = transition.getTarget();

    }

    private class TransitionException extends RuntimeException {

        public TransitionException(Throwable cause) {
            super(cause);
        }

        public TransitionException(String message, Throwable cause) {
            super(message, cause);
        }

        public TransitionException(String message) {

            super(message);
        }
    }

}

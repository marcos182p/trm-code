package trm.lexical;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import trm.lexical.rules.IRule;

/** Classe Automaton - Definir o formato do autômato
 * @author TRM
 * @version 0.99
 */
public class Automaton {

    private Map<State, Set<Transition>> automaton;
    private State startState;
    private List<State> finalStates;

    /** Construtor Automaton da Classe - Padrão
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  startState State - Estado inicial do autômato
     * @param  endStates State... - Array contendo os estados finais do
     * autômato
     */
    public Automaton(State startState, State... endStates) {

        this.startState = startState;
        this.finalStates = Arrays.asList(endStates);

        this.automaton = new HashMap<State, Set<Transition>>();
    }

    /** Construtor Automaton da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  startState State - Estado inicial do autômato
     * @param  endStates  List<State> - List<State> contendo os estados
     * finais do autômato
     */
    public Automaton(State startState, List<State> endStates) {

        this.startState = startState;
        this.finalStates = endStates;

        this.automaton = new HashMap<State, Set<Transition>>();
    }

    /** Construtor Automaton da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  start State - Estado inicial do autômato
     * @param  end State - Estado final do autômato
     */
    public Automaton(State start, State end) {

        this(start, new State[]{end});
    }

    /** Metodo que adiciona uma transição no autômato
     * @param  label String - Nome (label) da transição
     * @param  source State - Estado inicial da transição
     * @param  target State - Estado final (alvo) da transição
     * @param  rule IRule - Regra para transição de estado
     * @return void
     */
    public void addTransition(String label, State source, State target, IRule rule) {

        addTransition(new Transition(label, source, target, rule));
    }

    /** Metodo que adiciona uma transição no autômato
     * @param  transition Transition - Transição para ser adicionada
     * @return void
     */
    public void addTransition(Transition transition) {

        if (!automaton.containsKey(transition.getSource())) {
            automaton.put(transition.getSource(), new HashSet<Transition>());
        }

        automaton.get(transition.getSource()).add(transition);
    }

    /** Método que retorna as transições contidas
     * no autômato, partindo de um determinado
     * estado
     * @param  state State - Estado origem
     * @return Set<Transition> - Conjuntos de transições
     * que pertence ao autômato e possui o state como
     * estado de origem
     */
    public Set<Transition> getTransitions(State state) {

        return automaton.get(state);
    }

    /** Método que retorna o estado inicial do autômato
     * @return State - Estado inicial do autômato
     */
    public State getStartState() {
        return startState;
    }

    /** Método que retorna os estados finais do autômato
     * @return List<State> - Lista contendo os estados
     * finais do autômato
     */
    public List<State> getFinalStates() {
        return finalStates;
    }

    /** Método que procura uma transição que sai do estado
     * 'source' e permita a leitura do caracter 'c'.
     * @param  source State - Estado origem
     * @param  c char - Caracter a ser lido.
     * @return State - O estado alvo desta transição
     */
    public State nextTargetState(State source, char c) {
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

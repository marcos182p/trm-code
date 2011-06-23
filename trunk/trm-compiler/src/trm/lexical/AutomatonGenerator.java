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
import trm.lexical.rules.IRule;

/** Classe AutomatonGenerator - Classe que
 * cria o automato que reconhecerá a linguagem
 * dinamicamente, baseada em um arquivo.
 * (Em fase de implementação)
 * @author TRM
 * @version 0.99
 */
public class AutomatonGenerator {

    private BufferedReader reader;
    private Automaton automaton;
    private Map<String, State> states = new HashMap<String, State>();

    /** Construtor AutomatonGenerator da Classe -
     * Cria um buffer de leitura do arquivo baseado
     * na variavel path.
     * @param  path String - Caminho onde se localiza
     * o arquivo que define o autômato
     */
    public AutomatonGenerator(String path) {
        try {
            reader = new BufferedReader(new FileReader(new File(path)));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /** Método que gera o autômato automaticamente
     * @throws  IOException -  se algum erro de entrada
     * e saída ocorrer
     */
    public void generate() throws IOException {

        State initialState = new State(reader.readLine().trim());
        Set<State> finalStates = generateStates(reader.readLine().trim());

        automaton = new Automaton(initialState,
                new ArrayList<State>(finalStates));

        Set<Transition> transitions = generateTransitions();

        for (Transition transition : transitions) {
            automaton.addTransition(transition);
        }
    }

    /** Método que gera estados para o autômato automaticamente
     * @param  line String - Linha do arquivo de definição do
     * autõmato
     * @return Set<State> - Conjunto de estados gerados
     */
    private Set<State> generateStates(String line) {
        Set<State> result = new HashSet<State>();
        for (String label : line.split(",")) {
            result.add(new State(label.trim()));
        }
        return result;
    }

    /** Método que gera transições para o autômato automaticamente
     * @throws  IOException -  se algum erro de entrada
     * e saída ocorrer
     * @return Set<Transition> - Conjunto de transições gerados
     */
    private Set<Transition> generateTransitions() throws IOException {

        Set<Transition> result = new HashSet<Transition>();


        states.put(automaton.getStartState().getLabel(),
                automaton.getStartState());

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

    /** Método que gera transição para uma linha do arquivo
     * de definição do autômato
     * @param  line String - Linha do arquivo de definição do
     * autõmato
     * @return Transition - Transição gerada
     */
    private Transition generateTransition(String line) {

        String[] temp = line.split(",");
        String[] labels = temp[0].split("->");


        //FIXME melhorar isso!
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

        IRule rule = TransitionGenarator.createRule(temp[1]);

        //FIXME ver como gerar nomes automaticamente
        return new Transition("", source, target, rule);
    }
}

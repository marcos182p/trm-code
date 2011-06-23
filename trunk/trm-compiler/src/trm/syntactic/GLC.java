package trm.syntactic;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import trm.lexical.TokenClass;

/** Classe GLC - Responsável definir o formato da GLC da linguagem
 * @author TRM
 * @version 0.99
 */
public class GLC {

    private Variable initialElement;
    private Map<Variable, Set<Derivation>> derivations;
    private Set<Derivation> allDerivations;
    public Map<Variable, Set<Terminal>> follows;

    /** Construtor GLC da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro e inicializa as variáveis necessárias
     * @param  initialElement Variable - Simbolo inicial da grmática
     */
    public GLC(Variable initialElement) {

        this.initialElement = initialElement;
        this.derivations = new HashMap<Variable, Set<Derivation>>();
        this.allDerivations = new HashSet<Derivation>();
        this.follows = new HashMap<Variable, Set<Terminal>>();
    }

    /**
     * Metódo que deve ser chamado para atualizar os valores do follow
     */
    public void initGLC() {
        calculateFollow();
    }

    /** Método que seta o elemento inicial da gramática
     * @param initialElement Variable - Novo elemento inicial
     */
    public void setInitialElement(Variable initialElement) {
        this.initialElement = initialElement;
    }

    /** Método que adiciona uma derivação na GLC
     * @param derivation Derivation - Derivação a ser adicionada
     */
    public void addDerivation(Derivation derivation) {

        if (!derivations.containsKey(derivation.getSource())) {
            derivations.put(derivation.getSource(), new HashSet<Derivation>());
        }
        derivations.get(derivation.getSource()).add(derivation);
        this.allDerivations.add(derivation);
    }

    /** Método que remove uma derivação da GLC
     * @param derivation Derivation - Derivação a ser removida
     */
    public void removeDerivation(Derivation derivation) {
        if (derivations.containsKey(derivation.getSource())) {
            derivations.get(derivation.getSource()).remove(derivation);
            this.allDerivations.remove(derivation);
        }
    }

    /** Método que retorna o elemento inicial da gramática
     * @return Variable - Elemento inicial da gramática
     */
    public Variable getInitialElement() {
        return initialElement;
    }

    /** Método que retorna todas as derivações da gramática
     * @return Set<Derivation> - Set contendo as derivações da gramática
     */
    public Set<Derivation> getDerivations() {
        return Collections.unmodifiableSet(allDerivations);
    }

    /** Método que retorna as derivações da gramática que contenham uma
     * determinada variável
     * @param var Variable - Variável no qual a derivação dever conter
     * @return Set<Derivation> - Set contendo as derivações
     */
    public Set<Derivation> getDerivations(Variable var) {
        return derivations.get(var);
    }

    /** Método que calcula o first de uma determinada variável
     * @param var Variable - Variável no qual deseja saber o first
     * @return Set<Terminal> - Set contendo o first da variável
     */
    public Set<Terminal> first(Element var) {
        Set<Terminal> first = new HashSet<Terminal>();

        if (var instanceof Terminal) {
            first.add((Terminal) var);
            return first;
        }

        Set<Derivation> varDerivations = getDerivations((Variable) var);

        for (Derivation d : varDerivations) {
            if (!d.getTargets().isEmpty()) {
                Set<Terminal> terminals = new HashSet<Terminal>();
                for (Element e : d.getTargets()) {
                    terminals = first(e);
                    if (!terminals.isEmpty()) {
                        break;
                    }
                }
                first.addAll(terminals);
            }
        }
        return first;
    }

    /** Método que retorna o follow de uma determinada variável
     * @param var Variable - Variável no qual deseja saber o follow
     * @return Set<Terminal> - Set contendo o follow da variável
     */
    public Set<Terminal> follow(Variable var) {
        return follows.get(var);
    }

    /**
     * Método que calcula o follow de uma determinada variável
     */
    private void calculateFollow() {

        follows.clear();

        follows.put(initialElement, new HashSet<Terminal>());
        follows.get(initialElement).add(new Terminal(TokenClass.TK_EOF));

        boolean modified = true;
        while (modified) {
            modified = false;
            for (Derivation derivation : allDerivations) {

                List<Element> targets = derivation.getTargets();

                for (Element element : targets) {

                    if (element instanceof Terminal) {
                        continue;
                    }

                    Variable var = (Variable) element;

                    Set<Terminal> follow = follows.get(var);

                    if (follow == null) {
                        follow = new HashSet<Terminal>();
                        follows.put(var, follow);
                    }
                    int lastSize = follow.size();

                    int nextIndex = targets.indexOf(var) + 1;

                    if (nextIndex < targets.size()) {

                        follow.addAll(first(targets.get(nextIndex)));

                        if (targets.get(nextIndex) instanceof Variable) {

                            if (hasEmptyDerivation((Variable) targets.get(nextIndex))) {

                                if (follows.get(derivation.getSource()) != null) {

                                    follow.addAll(follows.get(derivation.getSource()));
                                }
                            }
                        }
                    } else {
                        if (follows.get(derivation.getSource()) != null) {
                            follow.addAll(follows.get(derivation.getSource()));
                        }
                    }


                    if (follow.size() != lastSize) {
                        modified = true;
                    }
                }

            }
        }

    }

    /** Método que verifica se uma variável tem derivação vazia
     * @param var Variable - Variável no qual deseja saber se tem derivação
     * vazia
     * @return boolean - true se tiver derivação vazia, false se não
     */
    public boolean hasEmptyDerivation(Variable var) {

        //Percorrer a lista de derivações
        for (Derivation derivation : getDerivations(var)) {

            //Caso exista retorna true
            if (derivation.getTargets().isEmpty()) {
                return true;
            }
        }
        //Caso não exista retorna false
        return false;
    }
}

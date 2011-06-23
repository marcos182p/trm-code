package trm.syntactic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Classe Derivation - Definir o formato de uma derivação da gramática
 * @author TRM
 * @version 0.99
 */
public class Derivation {

    private Variable source;
    private List<Element> targets;
    private StringBuilder label;

    /** Construtor Derivation da Classe -
     *  Chama o construtor padrão da classe Derivation e passa os
     * seus parâmetros
     * @param  source Variable - Variável de origem (não-terminal)
     * @param  targets Element... - Elementos alvo da derivação
     */
    public Derivation(Variable source, Element... targets) {
        this(source, Arrays.asList(targets));
    }

    /** Construtor Derivation da Classe - Padrão
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  source Variable - Variável de origem (não-terminal)
     * @param  targets List<Element> - Lista contendo os elementos alvo
     * da derivação
     */
    public Derivation(Variable source, List<Element> targets) {

        //Inicializa os atributos da classe
        this.source = source;
        this.targets = targets;

        label = new StringBuilder();
        label.append(source.getLabel());
        label.append(" ->");

        for (Element element : targets) {
            label.append(" ");
            label.append(element.getLabel());
        }

    }

    /** Método que retorna a variável de origem (não-terminal)
     * @return Variable - Variável de origem
     */
    public Variable getSource() {
        return source;
    }

    /** Método que retorna um lista contendo os elementos alvo
     * da derivação
     * @return List<Element> - Lista contendo os elementos alvo
     */
    public List<Element> getTargets() {
        return Collections.unmodifiableList(targets);
    }

    @Override
    /** Metodo que compara dois objetos e verifica
     * se são iguais (Derivation com outro objeto)
     * @param  obj Object - Objeto a ser comparado
     * @return boolean - Resultado da comparação. true se
     * forem iguais e else se forem diferentes.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Derivation other = (Derivation) obj;
        if (this.source != other.source && (this.source == null
                || !this.source.equals(other.source))) {
            return false;
        }
        if (this.targets != other.targets && (this.targets == null
                || !this.targets.equals(other.targets))) {
            return false;
        }
        return true;
    }

    @Override
    /** Metodo que retorna o indice na tabela Hash
     * @return int - Indice na tabela Hash
     */
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.source != null ? this.source.hashCode() : 0);
        hash = 79 * hash + (this.targets != null ? this.targets.hashCode() : 0);
        return hash;
    }

    @Override
    /** Metodo que retorna uma String da derivação
     * @return String - String representando a derivação,
     */
    public String toString() {
        return label.toString();
    }
}

package trm.lexical;

import trm.lexical.rules.IRule;

/** Classe Transition - Definir o formato de uma transição
 * @author TRM
 * @version 0.99
 */
public class Transition {

    private String label;
    private State source;
    private State target;
    private IRule rule;

    /** Construtor Transition da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  label String - Nome (label) da transição
     * @param  source State - Estado inicial da transição
     * @param  target State - Estado final (alvo) da transição
     * @param  rule IRule - Regra para transição de estado
     */
    public Transition(String label, State source, State target, IRule rule) {
        this.label = label;
        this.source = source;
        this.target = target;
        this.rule = rule;
    }

    /** Método que retorna o estado inicial da transição
     * @return State - Estado inicial da transição
     */
    public State getSource() {
        return source;
    }

    /** Método que retorna o estado final(alvo) da transição
     * @return State - Estado final(alvo) da transição
     */
    public State getTarget() {
        return target;
    }

    /** Método que retorna o nome da transição
     * @return String - Nome(label) da transição
     */
    public String getLabel() {
        return label;
    }

    /** Método que aceita um determinado caracterer
     * dependendo do resultado da avaliação da regra.
     * @return boolean - Resultado da avaliação da regra
     */
    public boolean accept(char c) {
        return rule.evaluate(c);
    }

    /** Metodo que compara dois objetos e verifica
     * se são iguais (Transition com outro objeto)
     * @param  obj Object - Objeto a ser comparado
     * @return boolean - Resultado da comparação. true se
     * forem iguais e else se forem diferentes.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Transition otherTransition = (Transition) obj;

        if (!this.label.equals(otherTransition.label)) {
            return false;
        }
        return true;
    }

    /** Metodo que retorna o indice na tabela Hash
     * @return int - Indice na tabela Hash
     */
    @Override
    public int hashCode() {
        return 79 * 7 + (this.label != null ? this.label.hashCode() : 0);
    }
}

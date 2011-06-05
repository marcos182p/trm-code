package trm.lexical;

/** Classe State - Definir o formato de um estado do automato
 * @author TRM
 * @version 0.99
 */
public class State {

    private String label;
    private StateDescription description;

    /** Construtor State da Classe - Padrão
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  label String - Nome(label) do estado
     * @param  description StateDescription - Descritor do estado
     */
    public State(String label, StateDescription description) {

        this.label = label;
        this.description = description;
    }

    /** Construtor State da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  label String - Nome(label) do estado
     */
    public State(String label) {
        this(label, new StateDescription());
    }

    /** Método que retorna o nome do estado do automato
     * @return String - Nome(label) do estado do automato
     */
    public String getLabel() {
        return label;
    }

    /** Método que retorna o descritor do estado do automato
     * @return StateDescription - Descritor do estado do automato
     */
    public StateDescription getDescription() {
        return description;
    }

    /** Metodo que seta o descritor do estado do automato
     * @param  description StateDescription - Novo valor do descritor
     * do estado do automato
     * @return void
     */
    public void setDescription(StateDescription description) {
        this.description = description;
    }

    /** Metodo que compara dois objetos e verifica
     * se são iguais (State com outro objeto)
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

        final State other = (State) obj;

        if (!this.label.equals(other.label)) {
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

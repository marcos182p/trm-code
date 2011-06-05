package trm.lexical;

/** Classe StateDescription - Descreve um estado do automato
 * @author TRM
 * @version 0.99
 */
public class StateDescription {

    private WordType wordType;

    /** Construtor StateDescription da Classe - Padrão
     * Inicializa os atributos da classe com os valores padrão
     * = WordType.UNDEFINED
     */
    public StateDescription() {
        wordType = WordType.UNDEFINED;
    }

    /** Construtor StateDescription da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  wordType WordType - Tipo de palavra que um determinado
     * estado representa
     */
    public StateDescription(WordType wordType) {

        this.wordType = wordType;
    }

    /** Método que retorna o tipo de palavra do estado
     * @return WordType - Tipo de palavra do estado
     */
    public WordType getWordType() {
        return wordType;
    }
}

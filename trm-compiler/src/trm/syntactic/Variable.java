package trm.syntactic;

/** Classe Variable - Extends Element -
 * Responsável definir um elemento variável da gramática
 * @author TRM
 * @version 0.99
 */
public class Variable extends Element {

    private String label;

    /** Construtor Variable da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  label String - Label da variável
     */
    public Variable(String label) {
        this.label = label;
    }

    /** Método que retorna o label do elemento variável
     * @return String - Label do elemento
     */
    public String getLabel() {
        return label;
    }

    @Override
    /** Metodo que retorna uma String da variável
     * @return String - String representando o simbolo variável,
     */
    public String toString() {
        return label;
    }
}

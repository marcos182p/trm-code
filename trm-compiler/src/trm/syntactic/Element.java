package trm.syntactic;

/** Classe Abstrata Element - Responsável definir um elemento da gramática
 * @author TRM
 * @version 0.99
 */
public abstract class Element {

    /** Método que retorna o label do elemento
     * @return String - Label do elemento
     */
    public abstract String getLabel();

    @Override
    /** Metodo que compara dois objetos e verifica
     * se são iguais (Element com outro objeto)
     * @param  obj Object - Objeto a ser comparado
     * @return boolean - Resultado da comparação. true se
     * forem iguais e else se forem diferentes.
     */
    public final boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Element)) {
            return false;
        }
        final Element other = (Element) obj;

        return this.getLabel().equals(other.getLabel());
    }

    @Override
    /** Metodo que retorna o indice na tabela Hash
     * @return int - Indice na tabela Hash
     */
    public int hashCode() {
        int hash = 7 * getLabel().hashCode();
        return hash;
    }
}

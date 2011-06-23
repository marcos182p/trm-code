package trm.syntactic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Classe PreditiveTable - Responsável definir a tabela de análise da gramática
 * @author TRM
 * @version 0.99
 */
public class PreditiveTable {

    private Map<Variable, Set<EntryTable>> preditiveTable;

    /** Construtor PreditiveTable da Classe -
     * Inicializa os atributos da classe
     */
    public PreditiveTable() {
        this.preditiveTable = new HashMap<Variable, Set<EntryTable>>();
    }

    /** Método que coloca uma entrada na tabela de análise
     * @param  terminal Terminal - simbolo terminal da entrada
     * @param  derivation Derivation - Derivação da entrada
     */
    public void putEntryTable(Terminal terminal, Derivation derivation) {

        //Pegar a origem da derivação
        Variable source = derivation.getSource();

        //Caso em que não contém a mesma entrada
        if (!preditiveTable.containsKey(source)) {
            //Adiciona
            preditiveTable.put(source, new HashSet<EntryTable>());
        }
        preditiveTable.get(source).add(new EntryTable(source, terminal, derivation));
    }

    /** Método que retorna a derivação de uma entrada na tabela de análise,
     * baseado em um simbolo terminal e a variável
     * @param  v Variable - Variável
     * @param  terminal Terminal - simbolo terminal
     * @return  Derivation - Derivação existente na tabela de análise que
     * leva a variável ao simbolo terminal
     */
    public Derivation getDerivation(Variable v, Terminal t) {

        //Percorrer a lista de entradas na tabela
        for (EntryTable entry : preditiveTable.get(v)) {
            if (entry.variable.equals(v) && entry.terminal.equals(t)) {
                return entry.derivation;
            }
        }
        return null;
    }

    /**
     * Método que imprime na tela a tabela de análise
     */
    public void printTable() {

        for (Variable element : preditiveTable.keySet()) {

            for (EntryTable entry : preditiveTable.get(element)) {
                System.out.println("[ " + entry.variable + " : " + entry.terminal + " ] = " + entry.derivation);
            }
        }
    }

    /**
     * Método que verifica se a linguagem é ambigua, isto é,
     * se houver duas entradas em um mesmo campo na tabela de ]
     * análise
     */
    public boolean isAmbiguous() {
        List<Terminal> terminals = new ArrayList<Terminal>();
        for (Variable v : preditiveTable.keySet()) {
            terminals.clear();
            for (EntryTable entry : preditiveTable.get(v)) {
                if (terminals.contains(entry.terminal)) {
                    return true;
                }
                terminals.add(entry.terminal);
            }
        }
        return false;
    }

    /** Classe EntryTable - Responsável definir uma entrada na tabela de análise
     * @author TRM
     * @version 0.99
     */
    private class EntryTable {

        public Variable variable;
        public Terminal terminal;
        public Derivation derivation;

        /** Construtor EntryTable da Classe -
         * Inicializa os atributos da classe com os valores recebidos
         * como parâmetro
         * @param  variable Variable - Variável de origem ex.: A ->
         * @param  terminal Terminal - Simbolo terminal destino ex.: -> a
         * @param  derivation Derivation - Derivação
         */
        public EntryTable(Variable variable, Terminal terminal, Derivation derivation) {

            //Se a origem da derivação não foi igual a variável
            if (!derivation.getSource().equals(variable)) {
                throw new IllegalArgumentException("A variável deve ser a fonte da derivacão!");
            }
            this.variable = variable;
            this.terminal = terminal;
            this.derivation = derivation;
        }
    }
}

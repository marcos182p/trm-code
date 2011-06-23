package trm.syntactic;

import java.util.HashSet;
import java.util.Set;

/** Classe TableGenerator - Classe que
 * cria a tabela preditiva dinamicamente,
 * baseado em uma descrição da grámatica (glc).
 * @author TRM
 * @version 0.99
 */
public class TableGenerator {

    /** Método responsável pela instanciação/criação da tabela
     * preditiva
     * @param  glc GLC - Gramática no qual deseja que a tabela seja
     * gerada
     * @return PreditiveTable - Instância da tabela preditiva
     */
    public static PreditiveTable createPreditiveTable(GLC glc) {

        //Cria a nova tabela
        PreditiveTable preditiveTable = new PreditiveTable();

        //Inicia a gramática
        glc.initGLC();

        //Percorrer todas as derivações da gramática
        for (Derivation derivation : glc.getDerivations()) {

            //Conjunto de termionais
            Set<Terminal> terminals = new HashSet<Terminal>();
            if (!derivation.getTargets().isEmpty()) {
                terminals = glc.first(derivation.getTargets().get(0));
            } else {
                terminals = glc.follow(derivation.getSource());
            }

            //Percorrer a lista de terminais
            for (Terminal terminal : terminals) {

                //Colocar a entrada na tabela
                preditiveTable.putEntryTable(terminal, derivation);
            }

        }

        //Retornar a tabela
        return preditiveTable;
    }
}

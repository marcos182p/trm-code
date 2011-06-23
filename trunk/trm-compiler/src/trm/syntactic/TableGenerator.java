package trm.syntactic;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author TRM
 */
public class TableGenerator {

    public static PreditiveTable createPreditiveTable(GLC glc) {
        PreditiveTable preditiveTable = new PreditiveTable();

        glc.initGLC();

        for (Derivation derivation : glc.getDerivations()) {
            Set<Terminal> terminals = new HashSet<Terminal>();
            if (!derivation.getTargets().isEmpty()) {
                terminals = glc.first(derivation.getTargets().get(0));
            } else {
                terminals = glc.follow(derivation.getSource());
            }

            for (Terminal terminal : terminals) {

                preditiveTable.putEntryTable(terminal, derivation);
            }

        }

        return preditiveTable;
    }
}

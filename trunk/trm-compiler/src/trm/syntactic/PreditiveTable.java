package trm.syntactic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PreditiveTable {

    private Map<Variable, Set<EntryTable>> preditiveTable;

    private class EntryTable {

        public Variable variable;
        public Terminal terminal;
        public Derivation derivation;

        public EntryTable(Variable variable, Terminal terminal, Derivation derivation) {
            if(!derivation.getSource().equals(variable)) {
                throw new IllegalArgumentException("A variavel deve ser a fonte da derivacao");
            }
            this.variable = variable;
            this.terminal = terminal;
            this.derivation = derivation;
        }
    }

    public PreditiveTable() {
        this.preditiveTable = new HashMap<Variable, Set<EntryTable>>();
        
    }
    public void putEntryTable(Terminal terminal, Derivation derivation) {
        Variable source = derivation.getSource();
        if(!preditiveTable.containsKey(source)) {
            preditiveTable.put(source, new HashSet<EntryTable>());
        }
        preditiveTable.get(source).add(new EntryTable(source, terminal, derivation));
    }

    public Derivation getDerivation(Variable v, Terminal t) {
        for(EntryTable entry : preditiveTable.get(v)) {
            if(entry.variable.equals(v) && entry.terminal.equals(t)) {
                return entry.derivation;
            }
        }
        return null;
    }

    public boolean isAmbiguous() {
        List<Terminal> terminals = new ArrayList<Terminal>();
        for(Variable v : preditiveTable.keySet()) {
            terminals.clear();
            for(EntryTable entry : preditiveTable.get(v)) {
                if(terminals.contains(entry.terminal)) {
                    return true;
                }
                terminals.add(entry.terminal);
            }
        }
        return false;
    }
}

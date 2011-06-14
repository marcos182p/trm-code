package trm.syntactic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GLC {
    
    private Variable initialElement;
    private Map<Variable, Set<Derivation>> derivations;
    private Set<Derivation> allDerivations;

    public GLC(Variable initialElement) {

        this.initialElement = initialElement;

        this.derivations = new HashMap<Variable, Set<Derivation>>();
        this.allDerivations = new HashSet<Derivation>();
    }

    public void addDerivation(Derivation derivation) {
        if(!derivations.containsKey(derivation.getSource())) {
            derivations.put(derivation.getSource(), new HashSet<Derivation>());
        }
        derivations.get(derivation.getSource()).add(derivation);
        this.allDerivations.add(derivation);
    }

    public void removeDerivation(Derivation derivation) {
        if(derivations.containsKey(derivation)) {
            derivations.get(derivation.getSource()).remove(derivation);
            this.allDerivations.remove(derivation);
        }
    }

    public Variable getInitialElement() {
        return initialElement;
    }
    
    public Set<Derivation> getDerivations() {

        return Collections.unmodifiableSet(allDerivations);
    }

    public Set<Derivation> getDerivations(Variable var) {
        return derivations.get(var);
    }

    public List<Terminal> calculateFirst(Element var) {
        List<Terminal> first = new ArrayList<Terminal>();

        if(var instanceof Terminal) {
            first.add((Terminal)var);
            return first;
        }
        
        Set<Derivation> derivations = getDerivations((Variable)var);

        for(Derivation d : derivations) {
            if(!d.getTargets().isEmpty()) {
                List<Terminal> terminals = new ArrayList<Terminal>();
                for(Element e : d.getTargets()) {
                    terminals = calculateFirst(e);
                    if(!terminals.isEmpty()) {
                        break;
                    }
                }
                first.addAll(terminals);
            }
        }
        return first;
    }

    public List<Terminal> calculateFolow(Variable var) {
        throw new UnsupportedOperationException();
    }

}

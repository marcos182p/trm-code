package trm.syntactic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GLC {
    
    private Set<Derivation> derivations;

    public GLC() {
        derivations = new HashSet<Derivation>();
    }

    public void addDerivation(Derivation derivation) {
        derivations.add(derivation);
    }

    public void removeDerivation(Derivation derivation) {
        derivations.remove(derivation);
    }

    public Set<Derivation> getDerivations() {
        return Collections.unmodifiableSet(derivations);
    }

}

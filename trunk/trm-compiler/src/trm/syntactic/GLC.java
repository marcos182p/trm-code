package trm.syntactic;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import trm.lexical.TokenClass;

public class GLC {

    private Variable initialElement;
    
    private Map<Variable, Set<Derivation>> derivations;
    private Set<Derivation> allDerivations;

    public Map<Variable, Set<Terminal>> follows;

    public GLC(Variable initialElement) {

        this.initialElement = initialElement;

        this.derivations = new HashMap<Variable, Set<Derivation>>();
        this.allDerivations = new HashSet<Derivation>();

        this.follows = new HashMap<Variable, Set<Terminal>>();
    }
    /**
     * metodo que deve ser chamado para atualizar os valores do follow
     */
    public void initGLC() {
        calculateFollow();
    }

    public void setInitialElement(Variable initialElement) {
        this.initialElement = initialElement;
    }

    

    public void addDerivation(Derivation derivation) {
        if (!derivations.containsKey(derivation.getSource())) {
            derivations.put(derivation.getSource(), new HashSet<Derivation>());
        }
        derivations.get(derivation.getSource()).add(derivation);
        this.allDerivations.add(derivation);
    }

    public void removeDerivation(Derivation derivation) {
        if (derivations.containsKey(derivation.getSource())) {
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

    public Set<Terminal> first(Element var) {
        Set<Terminal> first = new HashSet<Terminal>();

        if (var instanceof Terminal) {
            first.add((Terminal) var);
            return first;
        }

        Set<Derivation> varDerivations = getDerivations((Variable) var);

        for (Derivation d : varDerivations) {
            if (!d.getTargets().isEmpty()) {
                Set<Terminal> terminals = new HashSet<Terminal>();
                for (Element e : d.getTargets()) {
                    terminals = first(e);
                    if (!terminals.isEmpty()) {
                        break;
                    }
                }
                first.addAll(terminals);
            }
        }
        return first;
    }

    public Set<Terminal> follow(Variable var) {
        return follows.get(var);
    }

    private void calculateFollow() {

        follows.clear();
        
        follows.put(initialElement, new HashSet<Terminal>());
        follows.get(initialElement).add(new Terminal(TokenClass.TK_EOF));
        
        boolean modified = true;
        while (modified) {
            modified = false;
            for (Derivation derivation : allDerivations) {

                List<Element> targets = derivation.getTargets();

                for (Element element : targets) {

                    if (element instanceof Terminal) {
                        continue;
                    }

                    Variable var = (Variable) element;

                    Set<Terminal> follow = follows.get(var);

                    if (follow == null) {
                        follow = new HashSet<Terminal>();
                        follows.put(var, follow);
                    }
                    int lastSize = follow.size();

                    int nextIndex = targets.indexOf(var) + 1;

                    if (nextIndex < targets.size()) {

                        follow.addAll(first(targets.get(nextIndex)));

                        if (targets.get(nextIndex) instanceof Variable) {

                            if (hasEmptyDerivation((Variable) targets.get(nextIndex))) {

                                if (follows.get(derivation.getSource()) != null) {

                                    follow.addAll(follows.get(derivation.getSource()));
                                }
                            }
                        }
                    } else {
                         if (follows.get(derivation.getSource()) != null) {
                            follow.addAll(follows.get(derivation.getSource()));
                        }
                    }


                    if (follow.size() != lastSize) {
                        modified = true;
                    }
                }

            }
        }

    }

    public boolean hasEmptyDerivation(Variable var) {
        for(Derivation derivation : getDerivations(var)) {
            if(derivation.getTargets().isEmpty()) {
                return true;
            }
        }
        return false;
    }

}

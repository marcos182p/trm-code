package trm.syntactic;

import java.util.ArrayList;
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

    public GLC(Variable initialElement) {

        this.initialElement = initialElement;

        this.derivations = new HashMap<Variable, Set<Derivation>>();
        this.allDerivations = new HashSet<Derivation>();
    }

    public void addDerivation(Derivation derivation) {
        if (!derivations.containsKey(derivation.getSource())) {
            derivations.put(derivation.getSource(), new HashSet<Derivation>());
        }
        derivations.get(derivation.getSource()).add(derivation);
        this.allDerivations.add(derivation);
    }

    public void removeDerivation(Derivation derivation) {
        if (derivations.containsKey(derivation)) {
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

    public Set<Terminal> calculateFirst(Element var) {
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
                    terminals = calculateFirst(e);
                    if (!terminals.isEmpty()) {
                        break;
                    }
                }
                first.addAll(terminals);
            }
        }
        return first;
    }

    public Set<Terminal> calculateFollow(Variable var) {

        Set<Terminal> follow = new HashSet<Terminal>();
        if (var.equals(initialElement)) {
            follow.add(new Terminal(TokenClass.TK_EOF));
        }

        for (Derivation derivation : allDerivations) {
            boolean nextsFollow = false;
            List<Element> targets = derivation.getTargets();
            if (targets.contains(var)) {
                int currentIndex = targets.indexOf(var);
                Set<Terminal> terminals = new HashSet<Terminal>();
                int i;
                for (i = currentIndex + 1; i < targets.size(); i++) {
                    if (targets.get(i) instanceof Terminal) {
                        terminals.add((Terminal) targets.get(i));
                        break;
                    }
                    Derivation d = new Derivation((Variable) targets.get(i));

                    terminals.addAll(calculateFirst(targets.get(i)));

                    if (getDerivations((Variable) targets.get(i)).contains(d)) {
                        nextsFollow = true;
                        break;
                    }
                }
                follow.addAll(terminals);
                if (i == targets.size() || nextsFollow) {
                    if (!var.equals(derivation.getSource())) {
                        follow.addAll(calculateFollow(derivation.getSource()));
                    }
                }

            }


        }
        return follow;
    }

    public static void main(String[] args) {
        Variable S = new Variable("S");
        Variable S_ = new Variable("S1");
        Variable C = new Variable("C");


        GLC glc = new GLC(S);

        glc.addDerivation(new Derivation(S, new Terminal(TokenClass.TK_IF), C,
                new Terminal(TokenClass.TK_ATTRIBUTION), S, S_));
        glc.addDerivation(new Derivation(S, new Terminal(TokenClass.TK_ID)));

        glc.addDerivation(new Derivation(S_, new Terminal(TokenClass.TK_ELSE), S));

        glc.addDerivation(new Derivation(S_));

        glc.addDerivation(new Derivation(C, new Terminal(TokenClass.TK_ID)));



        System.out.println("follow");
        for (Element element : glc.calculateFollow(S_)) {
            System.out.println(element.getLabel());
        }



    }
}

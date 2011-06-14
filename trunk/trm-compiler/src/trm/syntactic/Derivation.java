package trm.syntactic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import trm.lexical.TokenClass;

public class Derivation {

    private Variable source;
    private List<Element> targets;

    private StringBuilder label;

    public Derivation(Variable source, Element... targets) {
        this(source, Arrays.asList(targets));
    }

    public Derivation(Variable source, List<Element> targets) {


        this.source = source;
        this.targets = targets;

        label = new StringBuilder();
        label.append(source.getLabel());
        label.append(" ->");

        for (Element element : targets) {
            
            label.append(" ");
            label.append(element.getLabel());
        }

    }

    public static void main(String[] args) {
        System.out.println(new Derivation(new Variable("E")));
        System.out.println(new Derivation(new Variable("E")).getTargets().size());
    }

    public Variable getSource() {
        return source;
    }

    public List<Element> getTargets() {
        return Collections.unmodifiableList(targets);
    }

   

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Derivation other = (Derivation) obj;
        if (this.source != other.source && (this.source == null ||
                !this.source.equals(other.source))) {
            return false;
        }
        if (this.targets != other.targets && (this.targets == null ||
                !this.targets.equals(other.targets))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.source != null ? this.source.hashCode() : 0);
        hash = 79 * hash + (this.targets != null ? this.targets.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return label.toString();
    }
}

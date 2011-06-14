package trm.syntactic;

public class Variable extends Element {

    private String label;

    public Variable(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}

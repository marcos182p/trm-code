package trm.lexical;

public class State {

    private String label;

    private StateDescription description;

    public State(String label, StateDescription description) {

        this.label = label;
        this.description = description;
    }

    public State(String label) {
        this(label, new StateDescription());
    }


    public String getLabel() {
        return label;
    }

    public StateDescription getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final State other = (State) obj;
        
        if (!this.label.equals(other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 79 * 7 + (this.label != null ? this.label.hashCode() : 0);
    }
}

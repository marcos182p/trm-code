package trm.syntactic;

public abstract class Element {
   public abstract String getLabel();

    @Override
    public final boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Element)) {
            return false;
        }
        final Element other = (Element) obj;

        return this.getLabel().equals(other.getLabel());
    }

    @Override
    public int hashCode() {
        int hash = 7 * getLabel().hashCode();
        return hash;
    }
}

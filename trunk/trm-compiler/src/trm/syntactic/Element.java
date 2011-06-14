package trm.syntactic;

public abstract class Element {
    
   public abstract String getLabel();

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Element)) {
            return false;
        }
        final Element other = (Element) obj;

        return this.getLabel().equals(other.getLabel());
    }
}

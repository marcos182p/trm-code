package trm.syntactic;

import trm.lexical.TokenClass;

public class Terminal extends Element {

    private TokenClass tokenClass;

    public Terminal(TokenClass tokenClass) {
        this.tokenClass = tokenClass;
    }

    @Override
    public String getLabel() {
        return tokenClass.name();
    }

    @Override
    public String toString() {
        return getLabel();
    }
}

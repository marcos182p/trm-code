package trm.lexical;


public class Token {

    private TokenClass tokenClass;
    
    private String value;

    public Token(String value, TokenClass token) {
        this.value = value;
        this.tokenClass = token;
    }

    public TokenClass getTokenClass() {
        return this.tokenClass;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return "[ " + tokenClass + " : " + value + " ]";
    }
}

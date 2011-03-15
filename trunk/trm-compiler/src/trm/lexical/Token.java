package trm.lexical;


public class Token {

    private TokenClass tokenClass;
    
    private String value;

    public Token(TokenClass token) {
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

    public String toString() {
        return "[ " + tokenClass + " : " + value + " ]";
    }
}

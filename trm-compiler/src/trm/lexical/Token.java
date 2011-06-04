package trm.lexical;

public class Token {

    private TokenClass tokenClass;
    private String value;
    private int line;
    private int column;

    public Token(String value, TokenClass token, int line, int column) {
        this.value = value;
        this.tokenClass = token;
        this.line = line;
        this.column = column;
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

    public int getcolumn() {
        return column;
    }

    public void setcolumn(int column) {
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "[ " + tokenClass + " : " + value + " : " + line + " : " + column + " ]";
    }
}

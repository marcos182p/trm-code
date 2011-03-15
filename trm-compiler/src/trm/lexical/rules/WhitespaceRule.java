package trm.lexical.rules;

public class WhitespaceRule implements Rule {

    @Override
    public boolean evaluate(char transitionChar) {
        return Character.isWhitespace(transitionChar);
    }
}

package trm.lexical.rules;

public class WhitespaceRule implements IRule {

    @Override
    public boolean evaluate(char transitionChar) {
        return Character.isWhitespace(transitionChar);
    }
}

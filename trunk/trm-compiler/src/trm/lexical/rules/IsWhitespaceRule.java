package trm.lexical.rules;

/**
 *
 */
public class IsWhitespaceRule implements Rule {

    @Override
    public boolean evaluate(char transitionChar) {
        return Character.isWhitespace(transitionChar);
    }
}

package trm.lexical.rules;

import trm.lexical.rules.Rule;

/**
 *
 */
public class WhitespaceRule implements Rule {

    @Override
    public boolean evaluate(char transitionChar) {
        return Character.isWhitespace(transitionChar);
    }
}

package rtm.lexical;

import rtm.lexical.rules.Rule;

public class isWhitespaceRule implements Rule {

    @Override
    public boolean evaluate(char transitionChar) {
        return Character.isWhitespace(transitionChar);
    }
}

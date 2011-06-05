package trm.lexical.rules;


public class IsDigitRule implements IRule {

    @Override
    public boolean evaluate(char transitionChar) {
        return Character.isDigit(transitionChar);
    }
}

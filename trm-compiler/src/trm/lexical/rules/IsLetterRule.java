package trm.lexical.rules;


public class IsLetterRule implements IRule {

    @Override
    public boolean evaluate(char transitionChar) {
        return (Character.isLetter(transitionChar));
    }
}

package trm.lexical.rules;


public class isWhitespaceRule implements Rule {
    
	@Override
	public boolean evaluate(char transitionChar) {
		return Character.isWhitespace(transitionChar);
	}

}

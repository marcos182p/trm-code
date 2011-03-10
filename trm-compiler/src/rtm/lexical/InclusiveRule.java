package rtm.lexical;

import rtm.lexical.rules.Rule;

public class InclusiveRule implements Rule{

	private char[] transitionChars;
	public InclusiveRule( char... transitionChars) {
		this.transitionChars = transitionChars;
	}
	public char[] getTransitionChars() {
		return this.transitionChars;
	}
	public boolean evaluate(char transitionChar) {
		for(int i = 0; i < transitionChars.length; i++) {
			if(transitionChars[i] == transitionChar) {
				return true;
			}
		}
		return false;
	}

	
}

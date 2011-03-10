package rtm.lexical;

import rtm.lexical.rules.Rule;

public class ExclusiveRule implements Rule{
	
	private char[] excludedChars;
	
	public ExclusiveRule(char... excludedChars) {
		this.excludedChars = excludedChars;
	}
	public char[] getTransitionChars() {
		return excludedChars;
	}
	
	@Override
	public boolean evaluate(char c) {
		for(int i = 0; i < excludedChars.length; i++) {
			if(excludedChars[i] == c) {
				return false;
			}
		}
		return true;
	}
}

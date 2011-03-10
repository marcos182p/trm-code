package rtm.lexical.rules;


public class IsLetterRule implements Rule{
	@Override
	public boolean evaluate(char transitionChar) {
		return (Character.isLetter(transitionChar));
	}

}

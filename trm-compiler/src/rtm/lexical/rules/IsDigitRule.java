package rtm.lexical.rules;


public class IsDigitRule implements Rule{

	@Override
	public boolean evaluate(char transitionChar) {
		return Character.isDigit(transitionChar);
	}

}

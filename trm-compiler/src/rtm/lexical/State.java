package rtm.lexical;


public class State {
	private Token token;
	private String value;
	
	public State(Token token) {
		this.token = token;
	}
	public Token getToken() {
		return this.token;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}
	public String toString() {
		return "[ " + token + " : " + value + " ]";
	}
}

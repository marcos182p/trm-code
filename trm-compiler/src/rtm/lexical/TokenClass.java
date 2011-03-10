package rtm.lexical;

public enum TokenClass {
	TK_UNDEFINED		(true), 
	TK_WHILE			(true),
	TK_FOR				(true),
	TK_IF				(true),
	TK_SWITCH			(true),
	TK_INTEGER_CTE		(false),
	TK_FLOATING_CTE		(false),
	TK_ID				(false);
	
	private boolean univoque;
	
	private TokenClass(boolean univoque) {
		this.univoque = univoque;
	}
	
	public boolean isUnivoque() {
		return univoque;
	}
	
}

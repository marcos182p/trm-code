package rtm.lexical;

import rtm.lexical.rules.Rule;

public class Transition{
	private Token source;
	private Token target;
	private Rule rule;
	
	public Transition(Token source, Token target, Rule rule) {
		this.source = source;
		this.target = target;
		this.rule = rule;
	}
	
	public Token getSource() {
		return this.source;
	}
	public Token getTarget() {
		return this.target;
	}
	public boolean accept(char c) {
		return rule.evaluate(c);
	}
}

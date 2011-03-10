package rtm.lexical;

import rtm.lexical.rules.Rule;

public class Transition{
	private State source;
	private State target;
	private Rule rule;
	
	public Transition(State source, State target, Rule rule) {
		this.source = source;
		this.target = target;
		this.rule = rule;
	}
	
	public State getSource() {
		return this.source;
	}
	public State getTarget() {
		return this.target;
	}
	public boolean accept(char c) {
		return rule.evaluate(c);
	}
}

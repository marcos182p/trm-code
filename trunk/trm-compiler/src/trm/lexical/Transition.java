package trm.lexical;

import trm.lexical.rules.IRule;

public class Transition {

    private State source;
    private State target;
    
    private IRule rule;

    public Transition(State source, State target, IRule rule) {
        this.source = source;
        this.target = target;
        this.rule = rule;
    }

    public State getSource() {
        return source;
    }

    public State getTarget() {
        return target;
    }

    public boolean accept(char c) {
        return rule.evaluate(c);
    }

    //TODO criar metodo equals
}

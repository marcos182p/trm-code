package rtm.lexical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rtm.lexical.rules.Rule;

public class Automaton {
	private Map<State, List<Transition>> automaton;
	private State startState;
	private State lastState;
	private State currentState;
	private boolean halted = false;
	
	public Automaton(State start) {
		this.automaton = new HashMap<State, List<Transition>>();
		this.startState = start;
		this.currentState = start;
		this.lastState = null;
		this.halted = false;
		this.currentState.setValue("");
	}
	

	public void addTransition(State source, State target, Rule rule) {
		if(!automaton.containsKey(source)) {
			automaton.put(source, new ArrayList<Transition>());
		}
		automaton.get(source).add(new Transition(source, target, rule));
	}
	public void reset() {
		this.currentState = startState;
		this.lastState = null;
		this.halted = false;
		this.currentState.setValue("");
	}
	public State getCurrentState() {
		return currentState;
	}
	public State getLastState() {
		return lastState;
	}
	public boolean hasHalted() {
		return this.halted;
	}
	public String getCurrentValue() {
		return this.currentState.getValue();
	}
	public Token getCurrentToken() {
		return this.currentState.getToken();
	}
	private void checkStart() {
		if(currentState.equals(startState)) {
			reset();
		}
	}
	public void transition(char c) {
		halted = true;
		for(Transition r : automaton.get(currentState)) {
			if(r.accept(c)) {
				lastState = currentState;
				currentState = r.getTarget();
				halted = false;
				this.currentState.setValue(lastState.getValue() + c);
				checkStart();
				return;
			}
		}
		
	}
}

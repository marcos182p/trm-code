package rtm.lexical.test;

import org.junit.Test;

import rtm.lexical.Automaton;
import rtm.lexical.ExclusiveRule;
import rtm.lexical.InclusiveRule;
import rtm.lexical.State;
import rtm.lexical.Token;
import rtm.lexical.rules.IsDigitRule;
import rtm.lexical.rules.IsLetterRule;
import rtm.lexical.rules.isWhitespaceRule;
import static org.junit.Assert.*;

public class TestingAutomaton {
	
	public void runAutomaton(Automaton a, String test) {
		for(char c : test.toCharArray()) {
			a.transition(c);
		}
	}
	@Test
	public void testingAutomaton() {
		State start = new State(Token.TK_UNDEFINED);
		State idState = new State(Token.TK_ID);
		State digitState = new State(Token.TK_INTEGER_CTE);
		State firstSlash = new State(Token.TK_UNDEFINED);
		State firstStar = new State(Token.TK_UNDEFINED);
		State secondSlash = new State(Token.TK_UNDEFINED);
		State secondStar = new State(Token.TK_UNDEFINED);
		
		Automaton automaton = new Automaton(start);
		
		automaton.addTransition(start, start, new isWhitespaceRule());
		automaton.addTransition(start, idState, new IsLetterRule());
		automaton.addTransition(start, digitState, new IsDigitRule());
		automaton.addTransition(start, firstSlash, new InclusiveRule('/'));
		
		automaton.addTransition(idState, idState, new IsDigitRule());
		automaton.addTransition(idState, idState, new IsLetterRule());
		
		automaton.addTransition(digitState, digitState, new IsDigitRule());
		
		automaton.addTransition(firstSlash, secondSlash, new InclusiveRule('/'));
		automaton.addTransition(firstSlash, firstStar, new InclusiveRule('*'));
		
		automaton.addTransition(secondSlash, secondSlash, new ExclusiveRule('\n'));
		automaton.addTransition(secondSlash, start, new InclusiveRule('\n'));
		
		automaton.addTransition(firstStar, firstStar, new ExclusiveRule('*'));
		automaton.addTransition(firstStar, secondStar, new InclusiveRule('*'));
		automaton.addTransition(secondStar, secondStar, new InclusiveRule('*'));
		automaton.addTransition(secondStar, firstStar, new ExclusiveRule('*', '/'));
		automaton.addTransition(secondStar, start, new InclusiveRule('/'));
		
		assertEquals(automaton.getCurrentState(), start);
		assertEquals(automaton.getCurrentValue(), "");
		
		automaton.transition('*');
		assertTrue(automaton.hasHalted());
		assertEquals(automaton.getCurrentValue(), "");
		assertTrue(automaton.getLastState() ==  null);
		
		automaton.reset();
		
		runAutomaton(automaton, "testando123");
		
		assertTrue(!automaton.hasHalted());
		assertEquals(automaton.getCurrentToken(),Token.TK_ID);
		assertEquals(automaton.getCurrentValue(), "testando123");
		
		automaton.transition(';');
		
		assertTrue(automaton.hasHalted());
		assertTrue(automaton.getCurrentState().getToken().equals(Token.TK_ID));
		assertTrue(automaton.getCurrentValue().equals("testando123"));
		
		automaton.reset();
		
		runAutomaton(automaton, "123456");
		
		assertTrue(!automaton.hasHalted());
		assertEquals(automaton.getCurrentToken(),Token.TK_INTEGER_CTE);
		assertEquals(automaton.getCurrentValue(), "123456");
		
		automaton.transition(';');
		
		assertTrue(automaton.hasHalted());
		
		automaton.reset();
		
		runAutomaton(automaton, "/*comentario\n em duas linhas*/");
		
		System.out.println(automaton.getCurrentValue());
		
		assertTrue(!automaton.hasHalted());
		assertEquals(automaton.getCurrentState(), start);
		assertEquals(automaton.getCurrentValue(), "");
		
		runAutomaton(automaton, "//comentario de uma linha\n");
		
		assertTrue(!automaton.hasHalted());
		assertEquals(automaton.getCurrentState(), start);
		assertEquals(automaton.getCurrentValue(), "");
		
	}
}

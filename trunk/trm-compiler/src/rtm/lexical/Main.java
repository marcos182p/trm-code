package rtm.lexical;

import rtm.lexical.rules.ExclusiveRule;
import rtm.lexical.rules.InclusiveRule;
import rtm.lexical.rules.IsDigitRule;
import rtm.lexical.rules.IsLetterRule;
import rtm.lexical.rules.IsWhitespaceRule;

public class Main {

    public static void main(String[] args) {
        State start = new State("1", new StateDescription());
        State idState = new State("2", new StateDescription(WordType.WORD));
        State digitState = new State("3", new StateDescription(WordType.INTEGER_CTE));
        State firstSlash = new State("4", new StateDescription());
        State firstStar = new State("5", new StateDescription());
        State secondSlash = new State("6", new StateDescription());
        State secondStar = new State("7", new StateDescription());

        Automaton automaton = new Automaton(start, idState, digitState);

        automaton.addTransition(start, start, new IsWhitespaceRule());
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


        Recognizer recognizer = new Recognizer(automaton, " d123456 1212 enquanto 3 se".toCharArray());
     
        recognizer.run();

        for(Token t : recognizer.getTokens()) {
            System.out.println("tokenValue = " + t.getValue());
            System.out.println("tokenClass = " + t.getTokenClass());
            System.out.println("");
        }
    }

}

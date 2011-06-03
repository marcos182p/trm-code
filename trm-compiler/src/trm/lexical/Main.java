package trm.lexical;

import trm.lexical.rules.ExclusiveRule;
import trm.lexical.rules.InclusiveRule;
import trm.lexical.rules.IsDigitRule;
import trm.lexical.rules.IsLetterRule;
import trm.lexical.rules.IsSymbolRule;
import trm.lexical.rules.WhitespaceRule;

public class Main {

    public static void main(String[] args) {

        //Automaton's states
        State startState = new State("1", new StateDescription());
        State idState = new State("2", new StateDescription(WordType.WORD));
        State fisrtRealState = new State("8", new StateDescription());
        State realState = new State("9", new StateDescription(WordType.REAL_CTE));
        State integerState = new State("3", new StateDescription(WordType.INTEGER_CTE));
        State firstCharState = new State("10", new StateDescription());
        State secondCharState = new State("11", new StateDescription());
        State charState = new State("12", new StateDescription(WordType.CHARACTER_CTE));
        State firstStringState = new State("13", new StateDescription());
        State stringState = new State("14", new StateDescription(WordType.STRING_CTE));
        State firstSlashState = new State("4", new StateDescription());
        State firstStarState = new State("5", new StateDescription());
        State secondSlashState = new State("6", new StateDescription());
        State secondStarState = new State("7", new StateDescription());


        State openCurlyBracketState = new State("15", new StateDescription(WordType.OPEN_CURLY_BRACKET));
        State closeCurlyBracketState = new State("16", new StateDescription(WordType.CLOSE_CURLY_BRACKET));
        State openParenthesesState = new State("17", new StateDescription(WordType.OPEN_PARENTHESES));
        State closeParenthesesState = new State("18", new StateDescription(WordType.CLOSE_PARENTHESES));


        Automaton automaton = new Automaton(startState, idState, integerState, realState, charState, stringState, openCurlyBracketState, closeCurlyBracketState, openParenthesesState, closeParenthesesState);

        //Automaton's transitions
        automaton.addTransition(startState, startState, new WhitespaceRule());
        automaton.addTransition(startState, firstCharState, new InclusiveRule('\''));
        automaton.addTransition(startState, firstStringState, new InclusiveRule('"'));
        automaton.addTransition(startState, idState, new IsLetterRule());
        automaton.addTransition(startState, integerState, new IsDigitRule());
        automaton.addTransition(startState, firstSlashState, new InclusiveRule('/'));


        automaton.addTransition(startState, openCurlyBracketState, new InclusiveRule('{'));
        automaton.addTransition(startState, closeCurlyBracketState, new InclusiveRule('}'));

        automaton.addTransition(startState, openParenthesesState, new InclusiveRule('('));
        automaton.addTransition(startState, closeParenthesesState, new InclusiveRule(')'));


        automaton.addTransition(firstCharState, secondCharState, new IsDigitRule());
        automaton.addTransition(firstCharState, secondCharState, new IsLetterRule());
        automaton.addTransition(firstCharState, secondCharState, new IsSymbolRule());
        automaton.addTransition(firstCharState, secondCharState, new WhitespaceRule());

        automaton.addTransition(secondCharState, charState, new InclusiveRule('\''));

        automaton.addTransition(firstStringState, firstStringState, new IsDigitRule());
        automaton.addTransition(firstStringState, firstStringState, new IsLetterRule());
        automaton.addTransition(firstStringState, firstStringState, new IsSymbolRule());
        automaton.addTransition(firstStringState, firstStringState, new WhitespaceRule());

        automaton.addTransition(firstStringState, stringState, new InclusiveRule('"'));

        automaton.addTransition(idState, idState, new IsDigitRule());
        automaton.addTransition(idState, idState, new IsLetterRule());
        automaton.addTransition(idState, idState, new InclusiveRule('_'));

        automaton.addTransition(integerState, integerState, new IsDigitRule());
        automaton.addTransition(integerState, fisrtRealState, new InclusiveRule('.'));

        automaton.addTransition(fisrtRealState, realState, new IsDigitRule());
        automaton.addTransition(realState, realState, new IsDigitRule());

        automaton.addTransition(firstSlashState, secondSlashState, new InclusiveRule('/'));
        automaton.addTransition(firstSlashState, firstStarState, new InclusiveRule('*'));

        automaton.addTransition(secondSlashState, secondSlashState, new ExclusiveRule('\n'));
        automaton.addTransition(secondSlashState, startState, new InclusiveRule('\n'));

        automaton.addTransition(firstStarState, firstStarState, new ExclusiveRule('*'));
        automaton.addTransition(firstStarState, secondStarState, new InclusiveRule('*'));
        automaton.addTransition(secondStarState, secondStarState, new InclusiveRule('*'));
        automaton.addTransition(secondStarState, firstStarState, new ExclusiveRule('*', '/'));
        automaton.addTransition(secondStarState, startState, new InclusiveRule('/'));


//        String texto = "";
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(new File("teste.trm")));
//
//            while (reader.ready()) {
//                texto += reader.readLine() + System.getProperty("line.separator");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //String arquivo = " false true  1.1 d123456 1212d /***aqui também*/ enquanto 3 //se 22\n   3hhg /***Isto é um comentário e deve ser ignorado*/ asdasds 123asd blá ";


        //Test
        String arquivo = "(( ) { }";
        Recognizer recognizer = new Recognizer(automaton, arquivo.toCharArray());

        recognizer.run();

    }
}

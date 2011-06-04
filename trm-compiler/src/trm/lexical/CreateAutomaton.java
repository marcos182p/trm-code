package trm.lexical;

import trm.lexical.rules.ExclusiveRule;
import trm.lexical.rules.InclusiveRule;
import trm.lexical.rules.IsDigitRule;
import trm.lexical.rules.IsLetterRule;
import trm.lexical.rules.IsSymbolRule;
import trm.lexical.rules.WhitespaceRule;

public class CreateAutomaton {

    //Metodo responsável pela instanciação/criação do automato
    public static Automaton create() {

        //Criação dos estados do automato
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
        State addState = new State("19", new StateDescription(WordType.ADD));
        State subState = new State("20", new StateDescription(WordType.SUB));
        State multState = new State("20", new StateDescription(WordType.MULT));
        State divState = new State("21", new StateDescription(WordType.DIV));
        State equalState = new State("28", new StateDescription(WordType.EQUAL));
        State greaterThanState = new State("29", new StateDescription(WordType.GREATER_THAN));
        State greaterOrEqualState = new State("35", new StateDescription(WordType.GREATER_OR_EQUAL));
        State lessThanState = new State("30", new StateDescription(WordType.LESS_THAN));
        State lessOrEqualState = new State("36", new StateDescription(WordType.LESS_OR_EQUAL));
        State openSquareBracketState = new State("31", new StateDescription(WordType.OPEN_SQUARE_BRACKET));
        State closeSquareBracketState = new State("32", new StateDescription(WordType.CLOSE_SQUARE_BRACKET));
        State semiColonState = new State("33", new StateDescription(WordType.SEMICOLON));
        State colonState = new State("34", new StateDescription(WordType.COLON));
        State firstNotEqualState = new State("37", new StateDescription());
        State notEqualState = new State("38", new StateDescription(WordType.NOT_EQUAL));
        State atributionState = new State("39", new StateDescription(WordType.ATRIBUTION));

        // Instanciação/Criação do automato
        Automaton automaton = new Automaton(startState, idState, integerState, realState, charState, stringState,
                openCurlyBracketState, closeCurlyBracketState, openParenthesesState, closeParenthesesState, addState,
                subState, multState, divState, equalState, greaterThanState, lessThanState, openSquareBracketState,
                closeSquareBracketState, semiColonState, colonState, greaterOrEqualState, lessOrEqualState, notEqualState,
                atributionState);



        //Criação das transições do automato
        automaton.addTransition(startState, startState, new WhitespaceRule());
        automaton.addTransition(startState, firstCharState, new InclusiveRule('\''));
        automaton.addTransition(startState, firstStringState, new InclusiveRule('"'));
        automaton.addTransition(startState, idState, new IsLetterRule());
        automaton.addTransition(startState, integerState, new IsDigitRule());
        automaton.addTransition(startState, firstSlashState, new InclusiveRule('/'));
        automaton.addTransition(startState, addState, new InclusiveRule('+'));
        automaton.addTransition(startState, subState, new InclusiveRule('-'));
        automaton.addTransition(startState, multState, new InclusiveRule('*'));
        automaton.addTransition(startState, divState, new InclusiveRule('/'));
        automaton.addTransition(startState, equalState, new InclusiveRule('='));
        automaton.addTransition(startState, greaterThanState, new InclusiveRule('>'));
        automaton.addTransition(greaterThanState, greaterOrEqualState, new InclusiveRule('='));
        automaton.addTransition(startState, lessThanState, new InclusiveRule('<'));
        automaton.addTransition(lessThanState, lessOrEqualState, new InclusiveRule('='));
        automaton.addTransition(lessThanState, atributionState, new InclusiveRule('-'));
        automaton.addTransition(startState, openSquareBracketState, new InclusiveRule('['));
        automaton.addTransition(startState, closeSquareBracketState, new InclusiveRule(']'));
        automaton.addTransition(startState, semiColonState, new InclusiveRule(';'));
        automaton.addTransition(startState, colonState, new InclusiveRule(':'));
        automaton.addTransition(startState, openCurlyBracketState, new InclusiveRule('{'));
        automaton.addTransition(startState, closeCurlyBracketState, new InclusiveRule('}'));
        automaton.addTransition(startState, openParenthesesState, new InclusiveRule('('));
        automaton.addTransition(startState, closeParenthesesState, new InclusiveRule(')'));
        automaton.addTransition(startState, firstNotEqualState, new InclusiveRule('!'));
        automaton.addTransition(firstNotEqualState, notEqualState, new InclusiveRule('='));
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

        return automaton;
    }
}

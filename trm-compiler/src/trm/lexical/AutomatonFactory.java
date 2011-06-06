package trm.lexical;

import java.beans.Expression;
import trm.lexical.rules.ExclusiveRule;
import trm.lexical.rules.InclusiveRule;
import trm.lexical.rules.IsDigitRule;
import trm.lexical.rules.IsLetterRule;
import trm.lexical.rules.IsSymbolRule;
import trm.lexical.rules.WhitespaceRule;

/** Classe AutomatonFactory - Classe responsável por
 * criar o automato que reconhecerá a linguagem
 * @author TRM
 * @version 0.99
 */
public class AutomatonFactory {

    /** Método responsável pela instanciação/criação do automato
     * @return Automaton - Instância do autômato que reconhecerá
     * a linguagem
     */
    public static Automaton createAutomaton() {

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
        State commanState = new State("40", new StateDescription(WordType.COMMAN));

        // Instanciação/Criação do automato
        Automaton automaton = new Automaton(startState, idState, integerState, realState, charState, stringState,
                openCurlyBracketState, closeCurlyBracketState, openParenthesesState, closeParenthesesState, addState,
                subState, multState, divState, equalState, greaterThanState, lessThanState, openSquareBracketState,
                closeSquareBracketState, semiColonState, colonState, greaterOrEqualState, lessOrEqualState, notEqualState,
                atributionState, commanState);



        //Criação das transições do automato
        automaton.addTransition("1", startState, startState, new WhitespaceRule());
        automaton.addTransition("2", startState, firstCharState, new InclusiveRule('\''));
        automaton.addTransition("3", startState, firstStringState, new InclusiveRule('"'));
        automaton.addTransition("4", startState, idState, new IsLetterRule());
        automaton.addTransition("5", startState, integerState, new IsDigitRule());
        automaton.addTransition("6", startState, firstSlashState, new InclusiveRule('/'));
        automaton.addTransition("7", startState, addState, new InclusiveRule('+'));
        automaton.addTransition("8", startState, subState, new InclusiveRule('-'));
        automaton.addTransition("9", startState, multState, new InclusiveRule('*'));
        automaton.addTransition("10", startState, divState, new InclusiveRule('/'));
        automaton.addTransition("11", startState, equalState, new InclusiveRule('='));
        automaton.addTransition("12", startState, greaterThanState, new InclusiveRule('>'));
        automaton.addTransition("13", greaterThanState, greaterOrEqualState, new InclusiveRule('='));
        automaton.addTransition("14", startState, lessThanState, new InclusiveRule('<'));
        automaton.addTransition("15", lessThanState, lessOrEqualState, new InclusiveRule('='));
        automaton.addTransition("16", lessThanState, atributionState, new InclusiveRule('-'));
        automaton.addTransition("17", startState, openSquareBracketState, new InclusiveRule('['));
        automaton.addTransition("18", startState, closeSquareBracketState, new InclusiveRule(']'));
        automaton.addTransition("19", startState, semiColonState, new InclusiveRule(';'));
        automaton.addTransition("20", startState, colonState, new InclusiveRule(':'));
        automaton.addTransition("21", startState, openCurlyBracketState, new InclusiveRule('{'));
        automaton.addTransition("22", startState, closeCurlyBracketState, new InclusiveRule('}'));
        automaton.addTransition("23", startState, openParenthesesState, new InclusiveRule('('));
        automaton.addTransition("24", startState, closeParenthesesState, new InclusiveRule(')'));
        automaton.addTransition("25", startState, firstNotEqualState, new InclusiveRule('!'));
        automaton.addTransition("26", startState, commanState, new InclusiveRule(','));
        automaton.addTransition("27", firstNotEqualState, notEqualState, new InclusiveRule('='));
        automaton.addTransition("28", firstCharState, secondCharState, new IsDigitRule());
        automaton.addTransition("29", firstCharState, secondCharState, new IsLetterRule());
        automaton.addTransition("30", firstCharState, secondCharState, new IsSymbolRule());
        automaton.addTransition("31", firstCharState, secondCharState, new WhitespaceRule());
        automaton.addTransition("32", secondCharState, charState, new InclusiveRule('\''));
        automaton.addTransition("33", firstStringState, firstStringState, new IsDigitRule());
        automaton.addTransition("34", firstStringState, firstStringState, new IsLetterRule());
        automaton.addTransition("35", firstStringState, firstStringState, new IsSymbolRule());
        automaton.addTransition("36", firstStringState, firstStringState, new WhitespaceRule());
        automaton.addTransition("37", firstStringState, stringState, new InclusiveRule('"'));
        automaton.addTransition("38", idState, idState, new IsDigitRule());
        automaton.addTransition("39", idState, idState, new IsLetterRule());
        automaton.addTransition("40", idState, idState, new InclusiveRule('_'));
        automaton.addTransition("41", integerState, integerState, new IsDigitRule());
        automaton.addTransition("42", integerState, fisrtRealState, new InclusiveRule('.'));
        automaton.addTransition("43", fisrtRealState, realState, new IsDigitRule());
        automaton.addTransition("44", realState, realState, new IsDigitRule());
        automaton.addTransition("45", firstSlashState, secondSlashState, new InclusiveRule('/'));
        automaton.addTransition("46", firstSlashState, firstStarState, new InclusiveRule('*'));
        automaton.addTransition("47", secondSlashState, secondSlashState, new ExclusiveRule('\n'));
        automaton.addTransition("48", secondSlashState, startState, new InclusiveRule('\n'));
        automaton.addTransition("49", firstStarState, firstStarState, new ExclusiveRule('*'));
        automaton.addTransition("50", firstStarState, secondStarState, new InclusiveRule('*'));
        automaton.addTransition("51", secondStarState, secondStarState, new InclusiveRule('*'));
        automaton.addTransition("52", secondStarState, firstStarState, new ExclusiveRule('*', '/'));
        automaton.addTransition("53", secondStarState, startState, new InclusiveRule('/'));

        return automaton;
    }
}

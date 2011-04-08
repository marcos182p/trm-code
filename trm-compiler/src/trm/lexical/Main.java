package trm.lexical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import trm.lexical.rules.WhitespaceRule;
import trm.lexical.rules.ExclusiveRule;
import trm.lexical.rules.InclusiveRule;
import trm.lexical.rules.IsDigitRule;
import trm.lexical.rules.IsLetterRule;

public class Main {

    public static void main(String[] args) {
        
        State start = new State("1", new StateDescription());
        State idState = new State("2", new StateDescription(WordType.WORD));
        State digitState = new State("3", new StateDescription(WordType.INTEGER_CTE));
        State firstSlash = new State("4", new StateDescription());
        State firstStar = new State("5", new StateDescription());
        State secondSlash = new State("6", new StateDescription());
        State secondStar = new State("7", new StateDescription());

        Automaton automaton = new Automaton(start, idState, digitState );

        automaton.addTransition(start, start, new WhitespaceRule());
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

        String texto = "";
        try {
            BufferedReader reader = new BufferedReader( new FileReader(new File("teste.trm")) );

            while(reader.ready()) {
                texto += reader.readLine() + System.getProperty("line.separator");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        String arquivo = "  d123456 1212d /***aqui também*/ enquanto 3 //se 22\n   3hhg /***Isto é um comentário e deve ser ignorado*/ asdasds 123asd blá ";
        Recognizer recognizer = new Recognizer(automaton, texto.toCharArray());
     
        recognizer.run();

    }

}

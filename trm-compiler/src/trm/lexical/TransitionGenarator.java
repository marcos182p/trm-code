package trm.lexical;

import trm.lexical.rules.ExclusiveRule;
import trm.lexical.rules.InclusiveRule;
import trm.lexical.rules.IsDigitRule;
import trm.lexical.rules.IsLetterRule;
import trm.lexical.rules.IRule;
import trm.lexical.rules.WhitespaceRule;

/** Classe TransitionGenarator - Criar Transições dinamicamente
 * (Em fase de implementação)
 * @author TRM
 * @version 0.99
 */
public class TransitionGenarator {

    /** Método createRule - Cria uma nova regra dinamicamente
     * @param  rule String - Regra a ser criada
     * @return IRule - Instancia da nova regra Criada que implementa IRule
     */
    public static IRule createRule(String rule) {

        rule = rule.trim();
        if (rule.equals("white")) {
            return new WhitespaceRule();
        } else if (rule.equals("letter")) {
            return new IsLetterRule();
        } else if (rule.equals("digit")) {
            return new IsDigitRule();
        }
        rule = rule.replace("{", "").replace("}", "");

        if (rule.contains("-")) {
            System.out.println("aquii");
            return new InclusiveRule(rule.replace("-", "").toCharArray());

        }
        return new ExclusiveRule(rule.toCharArray());
    }
}

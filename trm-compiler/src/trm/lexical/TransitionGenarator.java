package trm.lexical;

import trm.lexical.rules.ExclusiveRule;
import trm.lexical.rules.InclusiveRule;
import trm.lexical.rules.IsDigitRule;
import trm.lexical.rules.IsLetterRule;
import trm.lexical.rules.IRule;
import trm.lexical.rules.WhitespaceRule;

/**
 *
 * @author Marcos Paulo
 */
public class TransitionGenarator {

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

        if(rule.contains("-")) {
            System.out.println("aquii");
            return new InclusiveRule(rule.replace("-", "").toCharArray());

        }
        return new ExclusiveRule(rule.toCharArray());
    }

}

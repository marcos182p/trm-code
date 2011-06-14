package trm.syntactic;

import trm.lexical.ILexical;

public interface SyntacticAnalyser {
    
    public void parse(ILexical lexical);
}

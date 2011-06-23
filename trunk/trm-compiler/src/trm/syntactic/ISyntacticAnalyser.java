package trm.syntactic;

import trm.lexical.ILexical;

public interface ISyntacticAnalyser {

    public void parse(ILexical lexical);
}

package trm.syntactic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import trm.lexical.ILexical;
import trm.lexical.Token;
import trm.lexical.TokenClass;

public class GLCAnalyser {

    private GLC glc;
    
    private PreditiveTable table;
    private Stack<Element> variables;
    
    private List<Token> readTokens;
    
    public GLCAnalyser(GLC glc) {
        this.table = TableGenerator.createPreditiveTable(glc);
        this.glc = glc;
    }

    private void initParser() {
        variables = new Stack<Element>();
        variables.push(new Terminal(TokenClass.TK_EOF));
        variables.push(glc.getInitialElement());
        readTokens = new ArrayList<Token>();
    }
    
    public void analysis(ILexical lexical) {
        initParser();
        
        Token token = null;
        Token lastToken = null;
        
        while ((token = lexical.nextToken()) != null) {

            readTokens.add(token);

            if (variables.isEmpty()) {
                erro(token);
            }

            Terminal terminal = new Terminal(token.getTokenClass());

            Element top = null;

            while (!((top = variables.pop()) instanceof Terminal)) {
                
                Variable var = (Variable) top;
                Derivation derivation = table.getDerivation(var, terminal);


//                System.out.println(var.getLabel() + ", " + terminal.getLabel()
//                        + " = " + derivation);

                if(derivation == null) {
                    erro(token);
                }
                List<Element> elements = derivation.getTargets();

                for (int i = elements.size() - 1; i >= 0; i--) {
                    variables.push(elements.get(i));
                }
            }
            if (!top.equals(terminal)) {
                erro(token);
            }
            lastToken = token;
        }
        //FIXME isso está certo aqui?
        if (!variables.isEmpty()) {
            erro(lastToken);
        }
        
    }
    
    public List<Token> getReadTokens() {
        return readTokens;
    }

    private void erro(Token token) {
        System.out.println(token);
        throw new RuntimeException("erro de sintaxe em: " + token.getLine()
                + ", " + token.getcolumn());
    }

}

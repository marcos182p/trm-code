package trm.syntactic;

import java.util.List;
import java.util.Stack;
import trm.lexical.ILexical;
import trm.lexical.LexicalAnalyzer;
import trm.lexical.Token;
import trm.lexical.TokenClass;

public class Parser implements SyntacticAnalyser {

    private GLC glc;
    
    private PreditiveTable table;
    private Stack<Element> variables;
    
    private TokenClass finalToken;

    public Parser(GLC glc) {
        this.table = TableGenerator.createPreditiveTable(glc);
        this.glc = glc;
    }

    /**
     * passando o final token, vc estara definido um ponto de saida antes de ler
     * todos tokens
     */
    public Parser(GLC glc, TokenClass finalToken) {
        this(glc);
        this.finalToken = finalToken;
    }

    private void initParser() {
        variables = new Stack<Element>();
        variables.push(new Terminal(TokenClass.TK_EOF));
        variables.push(glc.getInitialElement());
    }
    @Override
    public void parse(ILexical lexical) {
        initParser();
        Token token = null;
        
        boolean parser = true;
        
        while ((token = lexical.nextToken()) != null && parser) {

            //FIXME ver maneira melhor de fazer isso
            if (token.getTokenClass().equals(finalToken)) {
                token = new Token(null, TokenClass.TK_EOF,
                        token.getLine(), token.getcolumn());
                parser = false;
            }

            if (variables.isEmpty()) {
                erro(token);
            }

            Terminal terminal = new Terminal(token.getTokenClass());

            Element top = null;

            while (!((top = variables.pop()) instanceof Terminal)) {
                
                Variable var = (Variable) top;
                Derivation derivation = table.getDerivation(var, terminal);


                System.out.println(var.getLabel() + ", " + terminal.getLabel()
                        + " = " + derivation);

                if(derivation == null) {
                    
                    System.out.println(variables.isEmpty());
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
        }
        
        if (!parser) {
            ((LexicalAnalyzer)lexical).putToken(token);
        }
    }

    private void erro(Token token) {
        throw new RuntimeException("erro de sintaxe em: " + token.getLine() + ", " + token.getcolumn());
    }
}

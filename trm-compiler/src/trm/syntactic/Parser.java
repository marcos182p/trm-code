package trm.syntactic;

import java.util.List;
import java.util.Stack;
import trm.lexical.ILexical;
import trm.lexical.Token;
import trm.lexical.TokenClass;

public class Parser implements SyntacticAnalyser {

    private PreditiveTable table;
    private Stack<Element> variables;
    
    private TokenClass finalToken;

    public Parser(GLC glc) {
        this.table = TableGenerator.createPreditiveTable(glc);
        variables = new Stack<Element>();
        variables.push(new Terminal(TokenClass.TK_EOF));
        variables.push(glc.getInitialElement());
    }
    /**
     * passando o final token, vc estara definido um ponto de saida antes de ler
     * todos tokens
     */
    public Parser(GLC glc, TokenClass finalToken) {
        this.table = TableGenerator.createPreditiveTable(glc);
        variables = new Stack<Element>();
        variables.push(new Terminal(TokenClass.TK_EOF));
        variables.push(glc.getInitialElement());
        this.finalToken = finalToken;
    }

    @Override
    public void parse(ILexical lexical) {
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
    }

    private void erro(Token token) {
        throw new RuntimeException("erro de sintaxe em: " + token.getLine() + ", " + token.getcolumn());
    }
}

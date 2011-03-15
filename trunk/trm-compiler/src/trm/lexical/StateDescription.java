package trm.lexical;

public class StateDescription {

    private WordType wordType;

    /**
     * Construtor padrão que cria um tipo de palavra indefinida.
     */
    public StateDescription() {
        wordType = WordType.UNDEFINED;
    }

    public StateDescription(WordType wordType) {
        
        this.wordType = wordType;
    }

    /**
     *
     * @return tipo de palavra que foi reconhecida até esse estado.
     */
    public WordType getWordType() {
        return wordType;
    }
}

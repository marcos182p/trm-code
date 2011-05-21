package trm.core;

/**
 * A peça em um jogo de domino
 */
public class Stone {

    private SquareNumber squareUp;
    private SquareNumber squareDown;

    public Stone(SquareNumber squareUp, SquareNumber squareDown) {
        this.squareUp = squareUp;
        this.squareDown = squareDown;
    }
    
    public SquareNumber getSquareUp() {
        return squareUp;
    }
    
    public SquareNumber getSquareDown() {
        return squareDown;
    }
    
    public static void main(String[] args) {
        Stone stone1 = new Stone(SquareNumber.SIX, SquareNumber.FIVE);
        Stone stone2 = new Stone(SquareNumber.FIVE, SquareNumber.SIX);
        System.out.println(stone1.equals(stone2));
        
        System.out.println(stone1.hashCode());
        System.out.println(stone2.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof  Stone)) {
            return false;
        }
        final Stone other = (Stone) obj;
        if (this.squareUp != other.squareUp
                && this.squareUp != other.squareDown) {

                return false;
        }
        if (this.squareDown != other.squareDown
                && this.squareDown != other.squareUp) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash += (this.squareUp != null ? this.squareUp.hashCode() : 0);
        hash += (this.squareDown != null ? this.squareDown.hashCode() : 0);
        return hash;
    }
}

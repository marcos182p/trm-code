package trm.core;

/**
 * A peça em um jogo de domino
 */
public class Stone {

    private SquareNumber squareLeft;
    private SquareNumber squareRight;

    public Stone(SquareNumber squareLeft, SquareNumber squareRight) {
        this.squareLeft = squareLeft;
        this.squareRight = squareRight;
    }
    
    public SquareNumber getSquareLeft() {
        return squareLeft;
    }
    
    public SquareNumber getSquareRight() {
        return squareRight;
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
        if (this.squareLeft != other.squareLeft
                && this.squareLeft != other.squareRight) {

                return false;
        }
        if (this.squareRight != other.squareRight
                && this.squareRight != other.squareLeft) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash += (this.squareLeft != null ? this.squareLeft.hashCode() : 0);
        hash += (this.squareRight != null ? this.squareRight.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Stone{" + "squareLeft=" + squareLeft + ", squareRight=" + squareRight + '}';
    }
    
}

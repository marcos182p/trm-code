package trm.core;

/**
 * A peça em um jogo de domino
 * @author TRM
 * @version 0.99
 */
public class Stone extends GameEntity {

    private SquareNumber squareLeft;
    private SquareNumber squareRight;

    protected Stone() {
        //para o gson
    }
    
    public Stone(SquareNumber squareLeft, SquareNumber squareRight) {
        this.squareLeft = squareLeft;
        this.squareRight = squareRight;
    }
    
    public SquareNumber getSquareLeft() {
        notifyObservers(findMethod(this, "getSquareLeft"));
        
        return squareLeft;
    }
    
    public SquareNumber getSquareRight() {
        notifyObservers(findMethod(this, "getSquareRight"));
        
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stone other = (Stone) obj;
        if (this.squareLeft != other.squareLeft) {
            return false;
        }
        if (this.squareRight != other.squareRight) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.squareLeft != null ? this.squareLeft.hashCode() : 0);
        hash = 97 * hash + (this.squareRight != null ? this.squareRight.hashCode() : 0);
        return hash;
    }
    
       @Override
    public String toString() {
        return "Stone{" + "squareLeft=" + squareLeft + ", squareRight=" + squareRight + '}';
    }

   
    
}

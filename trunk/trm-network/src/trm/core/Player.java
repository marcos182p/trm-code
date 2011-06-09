package trm.core;

/**
 * @author TRM
 * @version 0.99
 */
public class Player {

    private PlayerInf inf;

    public Player() {
        //para o gson.
    }
    
    public Player(PlayerInf inf) {
        this.inf = inf;
    }

    public PlayerInf getInf() {
        return inf;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.inf != other.inf && (this.inf == null || !this.inf.equals(other.inf))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.inf != null ? this.inf.hashCode() : 0);
        return hash;
    }
}

package trm.core;

/**
 * @author TRM
 * @version 0.99
 */
public class PlayerInf extends GameEntity {
    
    private Long id;
    private String nickName;
    private boolean owner;

    protected PlayerInf() {
        //para o gson
    }
    
    public PlayerInf(Long id, String nickName) {
        this (id, nickName, false);
    }

    public PlayerInf(Long id, String nickName, boolean owner) {
        this.id = id;
        this.nickName = nickName;
        this.owner = owner;
    }
    
    public Long getId() {
        notifyObservers(findMethod(this, "getId"));
        return id;
    }
    
    public String getNickName() {
        notifyObservers(findMethod(this, "getNickName"));
        return nickName;
    }

    public boolean isOwner() {
        notifyObservers(findMethod(this, "isOwner"));
        return owner;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerInf other = (PlayerInf) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
}

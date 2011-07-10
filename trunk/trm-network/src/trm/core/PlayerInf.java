package trm.core;

/**
 * @author TRM
 * @version 0.99
 */
public class PlayerInf {
    
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
        
        return id;
    }
    
    public String getNickName() {

        return nickName;
    }

    public boolean isOwner() {

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

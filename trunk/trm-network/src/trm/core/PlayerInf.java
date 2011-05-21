package trm.core;

/**
 *
 * @author Marcos
 */
public class PlayerInf {
    
    private Long id;
    private String nickName;
    
    public PlayerInf(Long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getNickName() {
        return nickName;
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

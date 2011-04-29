package br.ufal.ic.model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Marcos Paulo
 */
public class GroupNet {
    
    private Long id;
    
    private Set<UserNet> users;
    
    public GroupNet(Long id) {
        this.id = id;
        users = new HashSet<UserNet>();
    }
    
    public Long getId() {
        return id;
    }
    
    public void putUser(UserNet user) {
        users.add(user);
    }

    public void broadcast(Message message) throws IOException {
        for (UserNet user : users) {
            user.sendMessage(message);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GroupNet other = (GroupNet) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
}

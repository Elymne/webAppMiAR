package api;

import api.entities.User;

public interface Authentification {
    
    void login(User user);
    
    void logout(User user);
    
}

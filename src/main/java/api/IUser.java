package api;

import api.entities.User;

public interface IUser {
    
    void login(User user);
    
    void logout(User user);
    
    void addFavorite(User user, String idEvent);
    
    void removeFavorite(User user, String idEvent);
    
}

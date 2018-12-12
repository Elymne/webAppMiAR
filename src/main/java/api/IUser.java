package api;

import api.entities.User;

public interface IUser {
    
    void login(User user);
    
    void logout(User user);
    
    void addFavorite(String idEvent);
    
    void removeFavorite(String idEvent);
    
}

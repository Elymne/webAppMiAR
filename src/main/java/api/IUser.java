package api;

import api.entities.User;

public interface IUser {
    
    void login(User user);
    
    void logout(User user);
    
    void addFavorite(String idUser, String idEvent);
    
    void removeFavorite(String idUser, String idEvent);
    
}

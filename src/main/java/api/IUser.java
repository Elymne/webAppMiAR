package api;

import api.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface IUser {
    
    void login(User user);
    
    void logout(User user);
    
    void addFavorite(String idUser, String idEvent);
    
    void removeFavorite(String idUser, String idEvent);
    
}

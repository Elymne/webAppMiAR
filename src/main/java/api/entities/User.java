package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    public String login;
    public String password;
    public boolean connected = false;
    public List<Event> favoriteEvent = new ArrayList<>();
}

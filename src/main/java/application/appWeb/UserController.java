package application.appWeb;

import api.entities.Event;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import api.entities.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.UserService;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String inscription(@RequestBody User user) throws IOException, JSONException {
        Boolean result = false;

        if (userService.isValidAccountName(user.login)) {
            userService.addNewUser(user);
            result = true;
        }

        JSONObject json = new JSONObject();
        json.put("success", result);
        return json.toString();
    }

    @PostMapping(value = "/user/signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String connexion(@RequestBody User user) throws IOException, JSONException {
        Boolean result = false;
        List<String> bookmarkList = new ArrayList<>();
        JSONObject json = new JSONObject();

        if (userService.isValidAuthentification(user.login, user.password)) {
            userService.login(user);
            result = true;

            for (Event event : user.favoriteEvent) {
                bookmarkList.add(event.recordid);
            }
        }

        json.put("success", result);
        json.put("idUser", user.login);
        json.put("bookmarks", bookmarkList);
        return json.toString();
    }

    @PostMapping(value = "/user/signout", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String deconnexion(@RequestBody User user) throws IOException, JSONException {
        Boolean result = false;

        userService.logout(user);

        JSONObject json = new JSONObject();
        json.put("success", result);
        json.put("idUser", user.login);
        return json.toString();
    }

    @PostMapping(value = "/bookmarks/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String addBookmarks(@RequestBody String payload) throws IOException, JSONException {
        Boolean result = true;
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(payload);
        String idEvent = jsonNode.get("eventId").asText();
        String idUser = jsonNode.get("userId").asText();
        userService.addBookmarks(idUser, idEvent);
        
        JSONObject json = new JSONObject();
        json.put("success", result);
        return json.toString();
    }
    
    @PostMapping(value = "/bookmarks/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String removeBookmarks(@RequestBody String payload) throws IOException, JSONException {
        Boolean result = true;
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(payload);
        String idEvent = jsonNode.get("eventId").asText();
        String idUser = jsonNode.get("userId").asText();
        userService.removeBookmarks(idUser, idEvent);
        
        JSONObject json = new JSONObject();
        json.put("success", result);
        return json.toString();
    }
}

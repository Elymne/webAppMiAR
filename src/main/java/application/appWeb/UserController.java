package application.appWeb;

import api.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.UserService;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/inscription", method = RequestMethod.POST, consumes = "text/plain")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String inscription(@RequestBody String payload) throws IOException, JSONException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(payload, User.class);
        Boolean result = false;
        if (userService.isValidAccountName(user.login)) {
            userService.addNewUser(user);
            result = true;
        }
        JSONObject json = new JSONObject();
        json.put("success", result);
        return json.toString();
    }

    @RequestMapping(value = "/connexion", method = RequestMethod.POST, consumes = "text/plain")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String connexion(@RequestBody String payload) throws IOException, JSONException {
        boolean result = false;
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(payload, User.class);
        if (userService.isValidAuthentification(user.login, user.password)) {
            result = true;
            userService.login(user);
        }
        JSONObject json = new JSONObject();
        json.put("success", result);
        json.put("user", user.login);
        json.put("connected", user.connected);
        return json.toString();
    }

    @RequestMapping(value = "/deconnexion", method = RequestMethod.POST, consumes = "text/plain")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String deconnexion(@RequestBody String payload) throws IOException, JSONException {
        boolean result = false;
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(payload, User.class);
        userService.logout(user);
        if (userService.getUserByName(user.login).connected) {
            result = false;
        }
        JSONObject json = new JSONObject();
        json.put("success", result);
        return json.toString();
    }
}

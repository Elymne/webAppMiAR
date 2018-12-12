package application.appWeb;

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
import domain.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/inscription", consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping(value = "/connexion", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String connexion(@RequestBody User user) throws IOException, JSONException {
        Boolean result = false;

        if (userService.isValidAuthentification(user.login, user.password)) {
            userService.login(user);
            result = true;
        }

        JSONObject json = new JSONObject();
        json.put("success", result);
        json.put("idUser", user.login);
        return json.toString();
    }

    @PostMapping(value = "/deconnexion", consumes = MediaType.APPLICATION_JSON_VALUE)
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

}
package application.appWeb;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import api.entities.Commentary;
import api.entities.Event;
import api.entities.EventLocation;
import api.entities.Parking;
import api.entities.User;
import domain.Service;

@Controller
public class EventController {

    @Autowired
    Service service;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/evenement/tous")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public List< Event> getAllEvents() {
        return service.getAllEvents();
    }

    @RequestMapping(value = "/evenement/{id}")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public Event getEventById(@PathVariable("id") String id) {
        return service.getEventById(id);
    }

    @RequestMapping(value = "/commentaire/{id}")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public List< Commentary> getCommentaryById(@PathVariable("id") String id) {
        return service.getAllCommentaryById(id);
    }

    @GetMapping("/parkings")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public List< Parking> getAllParkings() {
        return service.getAllParkings();
    }

    @GetMapping("/greeting")
    public String greeting(Event event, Model model) {
        event = service.getEventById("1");
        if (event != null) {
            model.addAttribute("event", event.nom);
        } else {
            model.addAttribute("event", "ERREUR");
        }
        return "greeting";
    }

    @RequestMapping(value = "/inscription", method = RequestMethod.POST, consumes = "text/plain")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String inscription(@RequestBody String payload) throws IOException, JSONException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(payload, User.class);
        Boolean resultat = false;
        if(service.isValidAccountName(user.login))
            service.addNewUser(user);
            resultat = true;
        JSONObject json = new JSONObject();
        json.put("success", resultat);
        return json.toString();
    }
    
    @RequestMapping(value = "/eventsByLocation", method = RequestMethod.POST, consumes = "text/plain")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Event> getEventByLocation(@RequestBody String payload) throws IOException {
        System.out.println(payload);
        ObjectMapper mapper = new ObjectMapper();
        EventLocation location = mapper.readValue(payload, EventLocation.class);
        return service.getEventByLocalisation(location.latitude, location.longitude, location.radius);
    }
    
    @RequestMapping(value = "/connexion", method = RequestMethod.POST, consumes = "text/plain")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String connexion(@RequestBody String payload) throws IOException {
        String res = null;
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(payload, User.class);
        if(service.isValidAuthentification(user.login, user.password))
            res = "Bien joué";
        return "hello from back!";
    }

    @GetMapping("/evenement/charger")
    @ResponseBody
    public String charger() {
        service.charge();
        return "Chargement événement, ceci ne sert que pour le tests, c'est rapide et pratique";
    }

}

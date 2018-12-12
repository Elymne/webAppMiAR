package application.appWeb;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import api.entities.Commentary;
import api.entities.Event;
import api.entities.EventLocation;
import api.entities.User;
import domain.EventService;
import domain.UserService;
import org.json.JSONException;
import org.json.JSONObject;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @GetMapping("/evenement/all")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public List< Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @RequestMapping(value = "/evenement/{id}")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public Event getEventById(@PathVariable("id") String id) {
        return eventService.getEventById(id);
    }

    @RequestMapping(value = "/commentary/getByEvent/{id}")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public List< Commentary> getCommentaryById(@PathVariable("id") String id) {
        return eventService.getAllCommentaryById(id);
    }

    @RequestMapping(value = "/eventsByLocation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Event> getEventByLocation(@RequestBody String payload) throws IOException {
        System.out.println(payload);
        ObjectMapper mapper = new ObjectMapper();
        EventLocation location = mapper.readValue(payload, EventLocation.class);
        System.out.println(location.latitude + " " + location.longitude + " " + location.radius);
        return eventService.getEventByLocalisation(location.latitude, location.longitude, location.radius);
    }

    @RequestMapping(value = "/commentary/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String addCommentary(@RequestBody String payload) throws IOException, JSONException {
        ObjectMapper mapper = new ObjectMapper();
        Commentary commentary = mapper.readValue(payload, Commentary.class);
        Boolean result = false;
        if (userService.getUserByName(commentary.idUser).connected) {
            if (eventService.isValidCommentary(commentary.message)) {
                eventService.addNewCommentary(commentary);
                result = true;
            }
        }
        JSONObject json = new JSONObject();
        json.put("success", result);
        return json.toString();
    }

    @GetMapping("/evenement/charger")
    @ResponseBody
    public String charger() {
        eventService.charge();
        return "Chargement Ã©vÃ©nement, ceci ne sert que pour le tests, c'est rapide et pratique";
    }

}

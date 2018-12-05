package application.appWeb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import api.entities.Event;
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
    public List< Event> getAllEvents() {
        return service.getAllEvents();
    }

    @RequestMapping(value = "/evenement/{id}")
    @ResponseBody
    public Event getEventById(@PathVariable("id") String id) {
        return service.getEventById(id);
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

}

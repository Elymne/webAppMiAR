package application.appWeb;

import api.entities.Commentary;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import api.entities.Event;
import api.entities.User;
import domain.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    @CrossOrigin(origins="http://localhost:3000")
    public List< Event> getAllEvents() {
        return service.getAllEvents();
    }

    @RequestMapping(value = "/evenement/{id}")
    @ResponseBody
    @CrossOrigin(origins="http://localhost:3000")
    public Event getEventById(@PathVariable("id") String id) {
        return service.getEventById(id);
    }

    @RequestMapping(value = "/commentaire/{id}")
    @ResponseBody
    @CrossOrigin(origins="http://localhost:3000")
    public List<Commentary> getCommentaryById(@PathVariable("id") String id) {
        return service.getAllCommentaryById(id);
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

    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public boolean recoverPass(@RequestParam("user") User user) {
        boolean res = service.isValidAccountName(user.accountName);
        if(res)
            service.addNewUser(user);
        return res;
    }
    
    @GetMapping("/evenement/charger")
    @ResponseBody
    public String charger() {
        service.charge();
        return "Chargement événement, ceci ne sert que pour le tests, c'est rapide et pratique";
    }

}

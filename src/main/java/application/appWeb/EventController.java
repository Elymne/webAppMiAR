package application.appWeb;

import api.entities.Commentary;
import api.entities.Event;
import domain.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventController {

    @Autowired
    Service service;

    @GetMapping("/")
    @ResponseBody
    public String getHome(List<Event> lesEvents, Model model) {
        lesEvents = new ArrayList<>();
        lesEvents = service.getAllEvents();
        if (lesEvents != null) {
            model.addAttribute("lesEvents", lesEvents);
        } else {
            model.addAttribute("lesEvents", "Aucunes événements dans la base de données");
        }
        return "home";
    }

    @RequestMapping(value = "/evenement/{id}")
    @ResponseBody
    public String getEvenement(@PathVariable("id") String id, Event unEvent, List<Commentary> lesCommentaires, Model model) {
        unEvent = service.getEventById(id);
        lesCommentaires = new ArrayList<>();
        lesCommentaires = service.getAllCommentaryById(id);
        if (unEvent != null) {
            model.addAttribute("unEvent", unEvent.nom);
            model.addAttribute("lesCommentaires", lesCommentaires);
        } else {
            model.addAttribute("unEvent", "Selection d'un événement innexistant");
        }
        return "evenement";
    }
    
    // ---
    // Methode de test d'appel aux bases de données et aux chargements de celles-ci

    @GetMapping("/test/evenement/tous")
    @ResponseBody
    public List<Event> getAllEvents() {
        return service.getAllEvents();
    }

    @RequestMapping(value = "/test/evenement/{id}")
    @ResponseBody
    public Event getEventById(@PathVariable("id") String id) {
        return service.getEventById(id);
    }

    @GetMapping("/evenement/charger")
    @ResponseBody
    public String eventLoad() {
        service.loadAllEvent();
        return "Chargement des évènements";
    }
}

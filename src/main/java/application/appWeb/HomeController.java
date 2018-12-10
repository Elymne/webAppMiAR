package application.appWeb;

import api.entities.Event;
import domain.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    EventService service;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        //Utilisation pour retourner la page principale
        return "Greetings from Spring Boot!";
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

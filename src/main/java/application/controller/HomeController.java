package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import api.entities.Event;
import domain.EventService;

@Controller
public class HomeController
{

	@Autowired
	EventService service;

	// Ne pas supprimer le fichier (pour le moment)

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

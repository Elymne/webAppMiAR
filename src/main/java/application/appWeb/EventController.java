/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.appWeb;

import api.entities.Event;
import domain.Service;
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
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/evenement/tous")
    @ResponseBody
    public List<Event> getAllEvents() {
        return service.getAllEvents();
    }

    @RequestMapping(value = "/evenement/{id}")
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

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}

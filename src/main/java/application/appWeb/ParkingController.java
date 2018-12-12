package application.appWeb;

import api.entities.Event;
import api.entities.Parking;
import domain.EventService;
import domain.ParkingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParkingController {

    @Autowired
    ParkingService parkingService;
    
    @Autowired
    EventService eventService;

    @GetMapping("/parking/all")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public List< Parking> getAllParkings() {
        return parkingService.getAllParkings();
    }
    
    @RequestMapping(value = "/parking/nearby/{id}")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Parking> getParkingNearBy(@PathVariable("id") String id) {
        Event event = eventService.getEventById(id);
        return parkingService.getParkingNearBy(event);
    }
    
    @GetMapping("/parking/charger")
    @ResponseBody
    public String charger() {
        parkingService.charge();
        return "Chargement Parkings, ceci ne sert que pour le tests, c'est rapide et pratique";
    }

}

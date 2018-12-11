package application.controller;

import api.entities.Parking;
import domain.ParkingService;
import domain.EventService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @GetMapping("/parkings")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public List< Parking> getAllParkings() {
        return parkingService.getAllParkings();
    }

}

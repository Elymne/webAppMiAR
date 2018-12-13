package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import api.entities.Parking;
import domain.ParkingService;

@Controller
public class ParkingController
{

	@Autowired
	ParkingService service;


	@GetMapping( "/parking/all" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Parking > getAllParkings()
	{
		return service.getAllParkings();
	}

	@GetMapping( "/parking/nearby/{id}" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Parking > getParkingNearBy( @PathVariable( "id" ) String id )
	{
		return service.getParkingNearBy( id );
	}

}

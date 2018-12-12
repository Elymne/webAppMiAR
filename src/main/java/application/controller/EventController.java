package application.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import api.entities.Event;
import api.entities.EventLocation;
import api.entities.Mark;
import domain.EventService;
import domain.MarksService;
import domain.UserService;

@Controller
@RequestMapping( "/evenement" )
public class EventController
{
	@Autowired
	EventService eventService;

	@Autowired
	UserService userService;

	@Autowired
	MarksService marksService;

	@GetMapping( "/all" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Event > getAllEvents()
	{
		return eventService.getAll();
	}

	@GetMapping( value = "/{id}" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public Event getEventById( @PathVariable( "id" ) String id )
	{
		return eventService.getById( id );
	}

	@PostMapping( value = "/byLocation", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Event > getEventByLocation( @RequestBody EventLocation location ) throws IOException
	{
		System.out.println( location.latitude + " " + location.longitude + " " + location.radius );
		return eventService.findWithin( location.latitude, location.longitude, location.radius );
	}

	@PostMapping( value = "/rate", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public void rateEvent( @RequestBody Mark mark ) throws IOException
	{
		marksService.rate( mark );
	}

	@GetMapping( "/charger" )
	@ResponseBody
	public String charger()
	{
		eventService.update();
		return "Chargement Ã©vÃ©nement, ceci ne sert que pour le tests, c'est rapide et pratique";
	}

}

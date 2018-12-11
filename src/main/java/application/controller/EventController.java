package application.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.entities.Commentary;
import api.entities.Event;
import api.entities.EventLocation;
import api.entities.User;
import domain.EventService;
import domain.UserService;

@Controller
public class EventController
{

	@Autowired
	EventService eventService;

	@Autowired
	UserService userService;

	@GetMapping( "/evenement/tous" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Event > getAllEvents()
	{
		return eventService.getAll();
	}

	@RequestMapping( value = "/evenement/{id}" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public Event getEventById( @PathVariable( "id" ) String id )
	{
		return eventService.getById( id );
	}

	@RequestMapping( value = "/commentaire/{id}" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Commentary > getCommentaryById( @PathVariable( "id" ) String id )
	{
		return eventService.getAllCommentaryById( id );
	}

	@PostMapping( value = "/eventsByLocation", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Event > getEventByLocation( @RequestBody EventLocation location ) throws IOException
	{
		System.out.println( location.latitude + " " + location.longitude + " " + location.radius );
		return eventService.findWithin( location.latitude, location.longitude, location.radius );
	}

	@RequestMapping( value = "/commentaire/ajout", method = RequestMethod.POST, consumes = "text/plain" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String addCommentary( @RequestBody String payload ) throws IOException, JSONException
	{
		ObjectMapper	mapper		= new ObjectMapper();
		Commentary		commentary	= mapper.readValue( payload, Commentary.class );
		User			user		= mapper.readValue( payload, User.class );
		Boolean			result		= false;

		if( userService.getUserByName( user.login ).connected )
		{
			if( eventService.isValidCommentary( commentary.message ) )
			{
				eventService.addNewCommentary( commentary );
				result = true;
			}
		}
		JSONObject json = new JSONObject();
		json.put( "success", result );
		return json.toString();
	}

	@GetMapping( "/evenement/charger" )
	@ResponseBody
	public String charger()
	{
		eventService.update();
		return "Chargement Ã©vÃ©nement, ceci ne sert que pour le tests, c'est rapide et pratique";
	}

}

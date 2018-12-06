package application.appWeb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import api.entities.Commentary;
import api.entities.Event;
import api.entities.Parking;
import api.entities.User;
import domain.Service;

@Controller
public class EventController
{

	@Autowired
	Service service;

	@GetMapping( "/" )
	@ResponseBody
	public String index()
	{
		return "Greetings from Spring Boot!";
	}

	@GetMapping( "/evenement/tous" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Event > getAllEvents()
	{
		return service.getAllEvents();
	}

	@RequestMapping( value = "/evenement/{id}" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public Event getEventById( @PathVariable( "id" ) String id )
	{
		return service.getEventById( id );
	}

	@RequestMapping( value = "/commentaire/{id}" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Commentary > getCommentaryById( @PathVariable( "id" ) String id )
	{
		return service.getAllCommentaryById( id );
	}

	@GetMapping( "/parkings" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Parking > getAllParkings()
	{
		return service.getAllParkings();
	}

	@GetMapping( "/greeting" )
	public String greeting( Event event, Model model )
	{
		event = service.getEventById( "1" );
		if( event != null )
		{
			model.addAttribute( "event", event.nom );
		}
		else
		{
			model.addAttribute( "event", "ERREUR" );
		}
		return "greeting";
	}

	@RequestMapping( value = "/inscription", method = RequestMethod.POST )
	@CrossOrigin( origins = "http://localhost:3000" )
	public ResponseEntity< Boolean > recoverPass( @RequestParam User user )
	{
//		boolean res = service.isValidAccountName( user.accountName );
//		if( res )
//			service.addNewUser( user );
		return ResponseEntity.ok( false );
	}

	@GetMapping( "/evenement/charger" )
	@ResponseBody
	public String charger()
	{
		service.charge();
		return "Chargement événement, ceci ne sert que pour le tests, c'est rapide et pratique";
	}

}

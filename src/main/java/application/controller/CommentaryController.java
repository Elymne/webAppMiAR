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
import org.springframework.web.bind.annotation.ResponseBody;

import api.entities.Commentary;
import domain.CommentaryService;
import domain.UserService;

@Controller
@RequestMapping( "/commentary" )
public class CommentaryController
{
	@Autowired
	CommentaryService service;

	@Autowired
	UserService users;

	@GetMapping( value = "/getByEvent/{recordid}" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Commentary > getCommentaryById( @PathVariable( "recordid" ) String id )
	{
		return service.getByEventId( id );
	}

	@PostMapping( value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String addCommentary( @RequestBody Commentary commentary ) throws IOException, JSONException
	{
		Boolean result = users.isConnected( users.getUserByLogin( commentary.userid ) );

		if( result )
			service.add( commentary );

		JSONObject json = new JSONObject();
		json.put( "success", result );
		return json.toString();
	}
}

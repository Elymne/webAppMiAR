package application.controller;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.UserService;

@Controller
@RequestMapping( "/bookmarks" )
public class BookmarksController
{
	@Autowired
	UserService service;

	@PostMapping( value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String addBookmarks( @RequestBody String payload ) throws IOException, JSONException
	{
		Boolean			result	= true;
		ObjectMapper	mapper	= new ObjectMapper();

		JsonNode	jsonNode	= mapper.readTree( payload );
		String		idEvent		= jsonNode.get( "eventId" ).asText();
		String		idUser		= jsonNode.get( "userId" ).asText();
		service.addBookmarks( idUser, idEvent );

		JSONObject json = new JSONObject();
		json.put( "success", result );
		return json.toString();
	}

	@PostMapping( value = "/remove", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String removeBookmarks( @RequestBody String payload ) throws IOException, JSONException
	{
		Boolean			result	= true;
		ObjectMapper	mapper	= new ObjectMapper();

		JsonNode	jsonNode	= mapper.readTree( payload );
		String		idEvent		= jsonNode.get( "eventId" ).asText();
		String		idUser		= jsonNode.get( "userId" ).asText();
		service.removeBookmarks( idUser, idEvent );

		JSONObject json = new JSONObject();
		json.put( "success", result );
		return json.toString();
	}
}

package application.controller;

import java.io.IOException;
import java.util.List;

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

import api.entities.User;
import domain.UserService;

@Controller
@RequestMapping( "/user" )
public class UserController
{

	@Autowired
	UserService service;

	@PostMapping( value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String inscription( @RequestBody User user ) throws IOException, JSONException
	{
		Boolean result = service.isValidAccountName( user.login );

		if( result )
			service.addNewUser( user );

		JSONObject json = new JSONObject();
		json.put( "success", result );
		return json.toString();
	}

	@PostMapping( value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String signin( @RequestBody User user ) throws IOException, JSONException
	{
		Boolean			valid	= service.isValidAuthentification( user.login, user.password );
		List< String >	favs	= null;

		if( valid )
		{
			service.login( user );
			favs = service.getUserByLogin( user.login ).favoriteEvent;

			System.out.println( favs.size() );
		}

		JSONObject json = new JSONObject();
		json.put( "success", valid );
		json.put( "idUser", user.login );
		json.put( "bookmarks", favs );
		return json.toString();
	}

	@PostMapping( value = "/signout", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String singout( @RequestBody User user ) throws IOException, JSONException
	{
		service.logout( user );

		JSONObject json = new JSONObject();
		json.put( "success", true );
		json.put( "idUser", user.login );
		return json.toString();
	}

}

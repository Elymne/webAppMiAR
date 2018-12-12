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

import api.entities.User;
import domain.UserService;

@Controller
@RequestMapping( "/user" )
public class UserController
{

	@Autowired
	UserService userService;

	@PostMapping( value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String inscription( @RequestBody User user ) throws IOException, JSONException
	{
		Boolean result = userService.isValidAccountName( user.login );

		if( result )
			userService.addNewUser( user );

		JSONObject json = new JSONObject();
		json.put( "success", result );
		return json.toString();
	}

	@PostMapping( value = "/connect", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String connexion( @RequestBody User user ) throws IOException, JSONException
	{
		JSONObject json = new JSONObject();
		json.put( "success", userService.isValidAuthentification( user.login, user.password ) );
		return json.toString();
	}

}

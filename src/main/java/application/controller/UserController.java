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

	@PostMapping( value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String signin( @RequestBody User user ) throws IOException, JSONException
	{
		Boolean valid = userService.isValidAuthentification( user.login, user.password );

		if( valid )
			userService.login( user );

		JSONObject json = new JSONObject();
		json.put( "success", valid );
		return json.toString();
	}

	@PostMapping( value = "/signout", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String singout( @RequestBody User user ) throws IOException, JSONException
	{
		Boolean valid = userService.isValidAuthentification( user.login, user.password );
		System.out.println( "logout " + user.login + " : " + user.password + " : " + valid ); // JE NE RECOIS RIEN?

		if( valid )
			userService.logout( user );

		JSONObject json = new JSONObject();
		json.put( "success", valid );
		return json.toString();
	}

}

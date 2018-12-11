package application.appWeb;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import api.entities.User;
import domain.UserService;

@Controller
public class UserController
{

	@Autowired
	UserService userService;

	@RequestMapping( value = "/inscription", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String inscription( @RequestBody User user ) throws IOException, JSONException
	{
		Boolean result = false;

		if( userService.isValidAccountName( user.login ) )
		{
			userService.addNewUser( user );
			result = true;
		}

		JSONObject json = new JSONObject();
		json.put( "success", result );
		return json.toString();
	}

	@RequestMapping( value = "/connexion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String connexion( @RequestBody User user ) throws IOException, JSONException
	{
		Boolean result = false;

		if( userService.isValidAuthentification( user.login, user.password ) )
			result = true;

		JSONObject json = new JSONObject();
		json.put( "success", result );
		return json.toString();
	}

}

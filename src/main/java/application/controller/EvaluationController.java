package application.controller;

import java.io.IOException;

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

import api.entities.Evaluation;
import domain.EvaluationService;

@Controller
@RequestMapping( "/evaluation" )
public class EvaluationController
{
	@Autowired
	EvaluationService service;

	@GetMapping( "/getByEvent/{id}" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public Double getByEventId( @PathVariable( "id" ) String id )
	{
		return service.getAverageEvaluation( id );
	}

	@PostMapping( value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public String addEvaluation( @RequestBody Evaluation evaluation ) throws IOException, JSONException
	{
		Boolean result = false;

		if( service.isValidEvaluation( evaluation ) )
		{
			service.addEvaluation( evaluation );
			result = true;
		}
		JSONObject json = new JSONObject();
		json.put( "success", result );
		return json.toString();
	}
}

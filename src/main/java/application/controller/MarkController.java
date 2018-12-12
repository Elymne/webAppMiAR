package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import api.entities.Mark;
import domain.MarksService;

@Controller
public class MarkController
{
	@Autowired
	MarksService service;

	@GetMapping( "/evenement/{id}/marks" )
	@ResponseBody
	@CrossOrigin( origins = "http://localhost:3000" )
	public List< Mark > getMarksByEventId( @PathVariable( "recordid" ) String recordid )
	{
		return service.getByEventId( recordid );
	}
}

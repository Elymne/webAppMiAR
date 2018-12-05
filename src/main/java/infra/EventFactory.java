package infra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.Database;
import api.Factory;
import api.entities.Event;

public class EventFactory implements Factory
{

	@Autowired
	EventRepository eventRepository;

	@Autowired
	Database eventMongoDb;

	@Override
	public List< Event > getAllEvents()
	{
		// List< Event > dbEvents = getFromDatabase();
		// TODO: ici, la logique pour récup les events depuis la db (ou sinon depuis le
		// repo si y'en a pas)
		eventMongoDb.insertAll( buildEvents( eventRepository.getAll() ) );

		return eventMongoDb.getEvents();// buildEvents( eventRepository.getAll() );
	}

	public void putAllEvents()
	{
		JsonNode nodes = eventRepository.getAll();
		// eventMongoDb.loadEvent( resFromRepo );
	}

	private List< Event > buildEvents( JsonNode nodes )
	{
		ObjectMapper	mapper	= new ObjectMapper();
		List< Event >	list	= new ArrayList<>();

		try
		{
			for( JsonNode node : nodes )
				list.add( mapper.readValue( node.findValue( "fields" ).toString(), Event.class ) );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

		return list;
	}

	private List< Event > getFromDatabase()
	{
		return null;
	}



}

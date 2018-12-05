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
	Database database;

	@Override
	public List< Event > getAll()
	{
		// Si on a déjà des events dans la database, on les envoies
		/*
		 * List< Event > events = getFromDatabase();
		 * 
		 * if( events.size() > 0 ) return events;
		 * 
		 * // Sinon récup depuis le repo return getFromRepository();
		 */
		database.clear();
		database.insertAll( getFromRepository() );

		return database.getEvents();
	}


	private List< Event > getFromRepository()
	{
		return buildEvents( eventRepository.getAll() );
	}

	private List< Event > getFromDatabase()
	{
		return database.getEvents();
	}


	// Devrait être dans une autre classe
	// TODO
	private boolean shouldDatabaseBeUpdated()
	{
		return false;
	}

	private List< Event > buildEvents( JsonNode nodes )
	{
		ObjectMapper	mapper	= new ObjectMapper();
		List< Event >	list	= new ArrayList<>();

		try
		{
			for( JsonNode node : nodes )
			{
				Event event = mapper.readValue( node.findValue( "fields" ).toString(), Event.class );
				event.record_id = node.get( "recordid" ).textValue();

				list.add( event );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

		return list;
	}
}

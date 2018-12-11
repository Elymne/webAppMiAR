package domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import api.Factory;
import api.MongoDbQuery;
import api.entities.Commentary;
import api.entities.Event;
import infra.database.collection.EventCollection;
import infra.repository.EventRepository;

public class EventService
{
	@Autowired
	EventCollection collection;

	@Autowired
	EventRepository repository;

	@Autowired
	Factory< Event > factory;


	Delta delta = new Delta();

	@Scheduled( fixedRate = ( 1000 * 60 * 60 * 24 ) ) // Scheduled for once a day.
	public void update()
	{
		// System.out.println( "update!" );
		collection.saveAll( factory.build( repository.getAll() ) );
	}

	public List< Event > getAll()
	{
		return collection.findAll();
	}

	public Event getById( String id )
	{
		return collection.findByRecordid( id );
	}

	public List< Event > findWithin( double latitude, double longitude, double distance )
	{
		List< Event > events = new ArrayList<>();

		for( Event event : this.getAll() )
			if( delta.distance( latitude, longitude, event.locationX, event.locationY ) <= distance )
				events.add( event );

		return events;
	}


	// TODO: move all these methods to CommentaryService
	@Autowired
	MongoDbQuery< Commentary > commentaryQuery;

	public List< Commentary > getAllCommentaryById( String id )
	{
		List< Commentary > res = new ArrayList<>();
		for( Commentary commentary : commentaryQuery.getAll() )
		{
			if( commentary.idEvent.equals( id ) )
			{
				res.add( commentary );
			}
		}
		return res;
	}

	public boolean isValidCommentary( String message )
	{
		boolean res = true;
		if( message.length() < 20 )
		{
			res = false;
		}
		return res;
	}

	public void addNewCommentary( Commentary commentary )
	{
		commentaryQuery.insertValue( commentary );
	}
}

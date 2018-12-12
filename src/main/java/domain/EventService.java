package domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import api.Factory;
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
}

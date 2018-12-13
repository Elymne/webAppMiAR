package domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
		// collection.saveAll( factory.build( repository.getAll() ) );
	}

	@Value( "${event.week}" )
	private int week;

	public List< Event > getAll()
	{
		List< Event > filtered = new ArrayList<>();

		LocalDateTime	date	= new LocalDateTime();
		String[]		parts	= null;
		int				year, month, day;

		for( Event event : collection.findAll() )
		{
			parts	= event.date.split( "-" );
			year	= Integer.parseInt( parts[ 0 ] );
			month	= Integer.parseInt( parts[ 1 ] );
			day		= Integer.parseInt( parts[ 2 ] );
			if( date.getYear() == year )
			{
				if( date.getMonthOfYear() == month )
				{
					if( date.getDayOfMonth() + week >= day )
					{
						filtered.add( event );
					}
				}
			}
		}

		return filtered;
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

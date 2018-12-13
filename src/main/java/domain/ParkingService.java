package domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.entities.Event;
import api.entities.Parking;
import infra.database.collection.ParkingCollection;

public class ParkingService
{
	@Autowired
	ParkingCollection collection;

	@Autowired
	EventService events;

	Delta delta = new Delta();

	public List< Parking > getAllParkings()
	{
		return collection.findAll();
	}

	public List< Parking > getParkingNearBy( String eventid )
	{
		Event event = events.getById( eventid );

		List< Parking > res = new ArrayList<>();
		for( Parking parking : getAllParkings() )
		{
			if( delta.distance( parking.locationX, parking.locationY, event.locationX, event.locationY ) <= 0.5 )
			{
				res.add( parking );
			}
		}
		return res;
	}

}

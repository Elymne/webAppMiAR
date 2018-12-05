package infra.database.collection;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;

import api.DatabaseCollection;
import api.entities.Event;
import infra.database.MongoDatabaseClient;

public class EventCollection implements DatabaseCollection< Event >
{
	MongoDatabaseClient mongoDatabaseClient = MongoDatabaseClient.getInstance();

	private MongoCollection< Event > eventList = mongoDatabaseClient.getRoot().getCollection( "event", Event.class );

	@Override
	public void clear()
	{
		this.eventList.drop();
	}

	@Override
	public void insert( Event event )
	{
		this.eventList.insertOne( event );
	}

	@Override
	public void insertAll( List< Event > listEvent )
	{
		this.eventList.insertMany( listEvent );
	}

	@Override
	public List< Event > getAll()
	{
		List< Event > res = new ArrayList<>();

		for( Event event : this.eventList.find( Event.class ) )
		{
			res.add( event );
		}

		return res;
	}

}

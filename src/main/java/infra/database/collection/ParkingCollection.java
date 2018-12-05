package infra.database.collection;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;

import api.DatabaseCollection;
import api.entities.Parking;
import infra.database.MongoDatabaseClient;

public class ParkingCollection implements DatabaseCollection< Parking >
{
	MongoDatabaseClient mongoDatabaseClient = MongoDatabaseClient.getInstance();

	private MongoCollection< Parking > collection = mongoDatabaseClient.getRoot().getCollection( "parking",
			Parking.class );

	@Override
	public void clear()
	{
		this.collection.drop();
	}

	@Override
	public void insert( Parking parking )
	{
		this.collection.insertOne( parking );
	}

	@Override
	public void insertAll( List< Parking > parkings )
	{
		this.collection.insertMany( parkings );
	}

	@Override
	public List< Parking > getAll()
	{
		List< Parking > parkings = new ArrayList<>();

		for( Parking parking : this.collection.find( Parking.class ) )
			parkings.add( parking );

		return parkings;
	}

}

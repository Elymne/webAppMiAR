package infra.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.MongoDbQuery;
import api.entities.Parking;
import infra.database.collection.ParkingCollection;
import infra.repository.ParkingRepository;

public class ParkingFactory implements MongoDbQuery< Parking >
{
	@Autowired
	ParkingRepository repository;

	@Autowired
	ParkingCollection collection;

	@Override
	public List< Parking > getAll()
	{
		collection.deleteAll();
		collection.insert( buildEvents( repository.getAll() ) );

		return collection.findAll();
	}

	private List< Parking > buildEvents( JsonNode nodes )
	{
		ObjectMapper	mapper	= new ObjectMapper();
		List< Parking >	list	= new ArrayList<>();

		try
		{
			for( JsonNode node : nodes )
			{
				list.add( mapper.readValue( node.findValue( "fields" ).toString(), Parking.class ) );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void loadDatabase()
	{
		throw new UnsupportedOperationException( "Not supported yet." ); // To change body of generated methods, choose
																			// Tools | Templates.
	}

	@Override
	public void insertValue( Parking object )
	{
		throw new UnsupportedOperationException( "Not supported yet." ); // To change body of generated methods, choose
																			// Tools | Templates.
	}
}

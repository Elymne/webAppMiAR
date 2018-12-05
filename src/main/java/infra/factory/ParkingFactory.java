package infra.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.Factory;
import api.entities.Parking;
import infra.database.collection.ParkingCollection;
import infra.repository.ParkingRepository;

public class ParkingFactory implements Factory< Parking >
{
	@Autowired
	ParkingRepository repository;

	@Autowired
	ParkingCollection collection;

	@Override
	public List< Parking > getAll()
	{
		collection.clear();
		collection.insertAll( buildEvents( repository.getAll() ) );

		return collection.getAll();
	}

	private List< Parking > buildEvents( JsonNode nodes )
	{
		ObjectMapper	mapper	= new ObjectMapper();
		List< Parking >	list	= new ArrayList<>();

		try
		{
			for( JsonNode node : nodes )
				list.add( mapper.readValue( node.findValue( "fields" ).toString(), Parking.class ) );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

		return list;
	}

}

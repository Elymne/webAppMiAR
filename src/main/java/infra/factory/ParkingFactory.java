package infra.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.Factory;
import api.entities.Parking;

public class ParkingFactory implements Factory< Parking >
{
	@Override
	public List< Parking > build( JsonNode nodes )
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

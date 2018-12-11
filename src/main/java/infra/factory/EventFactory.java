package infra.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.Factory;
import api.entities.Event;

public class EventFactory implements Factory< Event >
{
	@Override
	public List< Event > build( JsonNode nodes )
	{
		ObjectMapper	mapper	= new ObjectMapper();
		List< Event >	list	= new ArrayList<>();

		try
		{
			for( JsonNode node : nodes )
			{
				Event event = mapper.readValue( node.findValue( "fields" ).toString(), Event.class );
				event.recordid = node.get( "recordid" ).textValue();

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

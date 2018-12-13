package infra.repository;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import api.Repository;

public class EventRepository implements Repository
{

	@Override
	public JsonNode getAll()
	{
		return new RestTemplate()
				.getForObject( basePath + "244400404_agenda-evenements-nantes-nantes-metropole&rows=1000",
						JsonNode.class )
				.get( "records" );
	}
}

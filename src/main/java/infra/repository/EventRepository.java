package infra.repository;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import api.Repository;

public class EventRepository implements Repository
{
	public static String baseUrl = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=";

	@Override
	public JsonNode getAll()
	{
		return new RestTemplate()
				.getForObject( baseUrl + "244400404_agenda-evenements-nantes-nantes-metropole&rows=100",
						JsonNode.class )
				.get( "records" );
	}

}

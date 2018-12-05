package api;

import com.fasterxml.jackson.databind.JsonNode;

public interface Repository
{
	public static String basePath = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=";

	public JsonNode getAll();

}

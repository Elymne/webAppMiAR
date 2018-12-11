package api;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public interface Factory< T >
{
	List< T > build( JsonNode data );
}

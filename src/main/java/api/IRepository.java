package api;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public interface IRepository {

    public static String basePath = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=";

    public JsonNode getAll();

}

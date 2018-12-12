package infra.repository;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import api.IRepository;

public class ParkingRepository implements IRepository {

    @Override
    public JsonNode getAll() {
        return new RestTemplate()
                .getForObject(basePath + "244400404_parkings-publics-nantes&rows=100", JsonNode.class)
                .get("records");
    }
}

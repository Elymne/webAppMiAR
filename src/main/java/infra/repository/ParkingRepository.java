package infra.repository;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import api.Repository;

public class ParkingRepository implements Repository {

    @Override
    public JsonNode getAll() {
        return new RestTemplate()
                .getForObject(basePath + "244400404_parkings-publics-nantes&rows=100", JsonNode.class)
                .get("records");
    }
}

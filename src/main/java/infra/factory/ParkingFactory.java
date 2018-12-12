package infra.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.entities.Parking;
import infra.database.collection.ParkingCollection;
import infra.repository.ParkingRepository;
import api.ILoader;

public class ParkingFactory implements ILoader< Parking> {

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    ParkingCollection parkingMongoDb;

    @Override
    public void loadDatabase() {
        parkingMongoDb.clear();
        parkingMongoDb.insertAll(getFromRepository());
    }

    private List<Parking> getFromRepository() {
        return buildEvents(parkingRepository.getAll());
    }

    private List< Parking> buildEvents(JsonNode nodes) {
        ObjectMapper mapper = new ObjectMapper();
        List< Parking> list = new ArrayList<>();

        try {
            for (JsonNode node : nodes) {
                list.add(mapper.readValue(node.findValue("fields").toString(), Parking.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}

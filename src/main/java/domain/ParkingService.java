package domain;

import api.MongoDbQuery;
import api.entities.Parking;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingService {

    @Autowired
    MongoDbQuery<Parking> parkingQuery;

    public List< Parking> getAllParkings() {
        return parkingQuery.getAll();
    }

}

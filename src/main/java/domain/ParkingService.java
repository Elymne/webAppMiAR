package domain;

import api.entities.Parking;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import api.ILoader;
import api.IDatabaseCollection;
import api.entities.Event;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {

    @Autowired
    ILoader<Parking> parkingLoader;

    @Autowired
    IDatabaseCollection<Parking> parkingQuery;

    Delta delta = new Delta();

    public List< Parking> getAllParkings() {
        return parkingQuery.getAll();
    }

    public void charge() {
        parkingLoader.loadDatabase();
    }

    public List<Parking> getParkingNearBy(Event event) {
        List<Parking> res = new ArrayList<>();
        for (Parking parking : getAllParkings()) {
            if (delta.distance(parking.locationX,
                    parking.locationY,
                    event.locationX,
                    event.locationY) <= 0.5) {
                res.add(parking);
            }
        }
        return res;
    }

}

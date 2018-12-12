package domain;

import api.entities.Parking;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import api.ILoader;
import api.IDatabaseCollection;

public class ParkingService {

    @Autowired
    ILoader<Parking> parkingLoader;
    
    @Autowired
    IDatabaseCollection<Parking> parkingQuery;

    public List< Parking> getAllParkings() {
        return parkingQuery.getAll();
    }
    
    public void charge(){
        parkingLoader.loadDatabase();
    }

}

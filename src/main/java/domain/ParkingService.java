package domain;

import api.DatabaseCollection;
import api.entities.Parking;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import api.ILoader;

public class ParkingService {

    @Autowired
    ILoader<Parking> parkingLoader;
    
    @Autowired
    DatabaseCollection<Parking> parkingQuery;

    public List< Parking> getAllParkings() {
        return parkingQuery.getAll();
    }
    
    public void charge(){
        parkingLoader.loadDatabase();
    }

}

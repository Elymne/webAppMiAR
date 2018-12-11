package infra.database.collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import api.entities.Parking;

public interface ParkingCollection extends MongoRepository< Parking, String >
{

}

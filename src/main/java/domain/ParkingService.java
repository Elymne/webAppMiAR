package domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.entities.Parking;
import infra.database.collection.ParkingCollection;

public class ParkingService
{

	@Autowired
	ParkingCollection collection;

	public List< Parking > getAllParkings()
	{
		return collection.findAll();
	}

}

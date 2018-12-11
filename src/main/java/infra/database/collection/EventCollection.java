package infra.database.collection;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import api.entities.Event;

public interface EventCollection extends MongoRepository< Event, String >
{
	List< Event > findByNom( String nom );
}

package infra.database.collection;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import api.entities.Mark;

public interface MarkCollection extends MongoRepository< Mark, String >
{
	List< Mark > findByRecordid( String id );
}

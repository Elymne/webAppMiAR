package infra.database.collection;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import api.entities.Commentary;

public interface CommentaryCollection extends MongoRepository< Commentary, String >
{
	List< Commentary > findByRecordid( String id );
}

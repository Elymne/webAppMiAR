package infra.database.collection;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import api.entities.Evaluation;

public interface EvaluationCollection extends MongoRepository< Evaluation, String >
{
	List< Evaluation > findByEventId( String id );
}

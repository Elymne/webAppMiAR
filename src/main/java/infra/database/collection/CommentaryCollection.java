package infra.database.collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import api.entities.Commentary;

public interface CommentaryCollection extends MongoRepository< Commentary, String >
{
}

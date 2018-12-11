package infra.database.collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import api.entities.User;

public interface UserCollection extends MongoRepository< User, String >
{

}

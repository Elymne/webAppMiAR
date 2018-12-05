package infra.factory;

import api.Factory;
import api.entities.User;
import infra.database.collection.UserCollection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class UserFactory implements Factory {

    @Autowired
    UserCollection userMongoDb;

    @Override
    public List<User> getAll() {
        return userMongoDb.getAll();
    }

}

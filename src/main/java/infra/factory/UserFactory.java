package infra.factory;

import api.Factory;
import api.MongoDbQuery;
import api.entities.User;
import infra.database.collection.UserCollection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class UserFactory implements Factory<User>, MongoDbQuery<User> {

    @Autowired
    UserCollection userMongoDb;

    @Override
    public List<User> getAll() {
        return userMongoDb.getAll();
    }

    @Override
    public void insertValue(User user) {
        this.userMongoDb.insert(user);
    }

    @Override
    public void loadDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

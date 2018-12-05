package infra.database.collection;

import api.DatabaseCollection;
import api.entities.User;
import com.mongodb.client.MongoCollection;
import infra.database.MongoDatabaseClient;
import java.util.ArrayList;
import java.util.List;

public class UserCollection implements DatabaseCollection< User> {

    MongoDatabaseClient mongoDatabaseClient = MongoDatabaseClient.getInstance();

    private MongoCollection<User> userList = mongoDatabaseClient.getRoot().getCollection("user",
            User.class);

    @Override
    public void clear() {
        this.userList.drop();
    }

    @Override
    public void insert(User user) {
        this.userList.insertOne(user);
    }

    @Override
    public void insertAll(List< User> userList) {
        this.userList.insertMany(userList);
    }

    @Override
    public List< User> getAll() {
        List< User> res = new ArrayList<>();

        for (User user : this.userList.find(User.class)) {
            res.add(user);
        }

        return res;
    }
}

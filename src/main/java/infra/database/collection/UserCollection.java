package infra.database.collection;

import api.DatabaseCollection;
import api.entities.User;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import infra.database.MongoDatabaseClient;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import api.IUser;
import static com.mongodb.client.model.Updates.pull;
import static com.mongodb.client.model.Updates.push;

public class UserCollection implements DatabaseCollection< User>, IUser {

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
    public void login(User user) {
        Bson filter = eq("login", user.login);
        Bson query = combine(set("connected", true));
        this.userList.updateOne(filter, query);
    }

    @Override
    public void logout(User user) {
        Bson filter = eq("login", user.login);
        Bson query = combine(set("connected", false));
        this.userList.updateOne(filter, query);
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

    @Override
    public void addFavorite(User user, String idEvent) {
        Bson filter = eq("login", user.login);
        Bson query = combine(push("favoriteEvent",idEvent));
        this.userList.updateOne(filter, query);
    }

    @Override
    public void removeFavorite(User user, String idEvent) {
        Bson filter = eq("login", user.login);
        Bson query = combine(pull("favoriteEvent",idEvent));
        this.userList.updateOne(filter, query);
    }
}

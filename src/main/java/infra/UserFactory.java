package infra;
/*
import api.Factory;
import api.entities.User;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;

public class UserFactory implements Factory {
    
    MongoDb mongoDb = new MongoDb();

    @Override
    public List<User> getAll() {
        DBCollection resFromMongoDb = mongoDb.getAllUser();
        return userBuild(resFromMongoDb);
    }
    
    public List<User> userBuild(DBCollection objectDB){
        List<User> lesUtilisateurs = new ArrayList<>();
        DBObject obj;
        User user;
        DBCursor cursor = objectDB.find();
        while (cursor.hasNext()) {
            obj = cursor.next();
            user = new User();
            user.id = (obj.get("_id").toString());
            user.nomCompte = (obj.get("nomCompte").toString());
            user.password = (obj.get("password").toString());
            lesUtilisateurs.add(user);
        }
        return lesUtilisateurs;
    }
    
}
*/
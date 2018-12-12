package infra.factory;

import api.MongoDbQuery;
import api.entities.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import infra.database.collection.UserCollection;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class UserFactory implements MongoDbQuery<User> {

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

    private User buildUser(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();
        User res = null;

        try {
            User user = mapper.readValue(node.findValue("fields").toString(), User.class);
        } catch (IOException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
}

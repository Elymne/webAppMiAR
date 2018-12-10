package infra.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.MongoDbQuery;
import api.entities.Commentary;
import infra.database.collection.CommentaryCollection;

public class CommentaryFactory implements MongoDbQuery<Commentary> {

    @Autowired
    CommentaryCollection commentaryMongoDb;

    @Override
    public List<Commentary> getAll() {
        return commentaryMongoDb.getAll();
    }

    @Override
    public void loadDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertValue(Commentary commentary) {
        commentaryMongoDb.insert(commentary);
    }
}

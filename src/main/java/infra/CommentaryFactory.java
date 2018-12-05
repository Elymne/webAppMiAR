package infra;

import api.Factory;
import api.entities.Commentary;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentaryFactory implements Factory {

    @Autowired
    CommentaryMongoDatabase commentaryMongoDb;

    @Override
    public List<Commentary> getAll() {
        return commentaryMongoDb.getAll();
    }
}

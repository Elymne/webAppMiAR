package infra;

import api.IMongoDb;
import api.entities.Commentary;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;

public class CommentaryMongoDatabase implements IMongoDb<Commentary> {

    MongoDatabaseClient mongoDatabaseClient = MongoDatabaseClient.getInstance();

    private MongoCollection<Commentary> lesCommentaires = mongoDatabaseClient.getRoot().getCollection("commentary", Commentary.class);

    @Override
    public void clear() {
        this.lesCommentaires.drop();
    }

    @Override
    public void insert(Commentary commentary) {
        this.lesCommentaires.insertOne(commentary);
    }

    @Override
    public void insertAll(List<Commentary> listCommentary) {
        this.lesCommentaires.insertMany(listCommentary);
    }

    @Override
    public List<Commentary> getAll() {
        List<Commentary> lesCommentaires = new ArrayList<>();

        for (Commentary unCommentaire : this.lesCommentaires.find(Commentary.class)) {
            lesCommentaires.add(unCommentaire);
        }

        return lesCommentaires;
    }

}

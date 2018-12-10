package infra.database.collection;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;

import api.DatabaseCollection;
import api.entities.Commentary;
import infra.database.MongoDatabaseClient;

public class CommentaryCollection implements DatabaseCollection<Commentary> {

    MongoDatabaseClient mongoDatabaseClient = MongoDatabaseClient.getInstance();

    private MongoCollection< Commentary> commentaryList = mongoDatabaseClient.getRoot().getCollection("commentary",
            Commentary.class);

    @Override
    public void clear() {
        this.commentaryList.drop();
    }

    @Override
    public void insert(Commentary commentary) {
        this.commentaryList.insertOne(commentary);
    }

    @Override
    public void insertAll(List<Commentary> commentaryList) {
        this.commentaryList.insertMany(commentaryList);
    }

    @Override
    public List<Commentary> getAll() {
        List<Commentary> res = new ArrayList<>();

        for (Commentary commentary : this.commentaryList.find(Commentary.class)) {
            res.add(commentary);
        }

        return res;
    }

}

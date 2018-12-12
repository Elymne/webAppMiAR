package infra.database.collection;

import api.DatabaseCollection;
import api.entities.Evaluation;
import com.mongodb.client.MongoCollection;
import infra.database.MongoDatabaseClient;
import java.util.ArrayList;
import java.util.List;

public class EvaluationCollection implements DatabaseCollection<Evaluation> {

    MongoDatabaseClient mongoDatabaseClient = MongoDatabaseClient.getInstance();

    private MongoCollection< Evaluation> evaluationList = mongoDatabaseClient.getRoot().getCollection("evaluation", Evaluation.class);

    @Override
    public void clear() {
        this.evaluationList.drop();
    }

    @Override
    public void insert(Evaluation evaluation) {
        this.evaluationList.insertOne(evaluation);
    }

    @Override
    public void insertAll(List<Evaluation> evaluationList) {
        this.evaluationList.insertMany(evaluationList);
    }

    @Override
    public List<Evaluation> getAll() {
        List<Evaluation> res = new ArrayList<>();
        for (Evaluation evaluation : this.evaluationList.find(Evaluation.class)) {
            res.add(evaluation);
        }

        return res;
    }

}

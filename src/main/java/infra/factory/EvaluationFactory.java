package infra.factory;

import api.MongoDbQuery;
import api.entities.Evaluation;
import infra.database.collection.EvaluationCollection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class EvaluationFactory implements MongoDbQuery<Evaluation>{
    
    @Autowired
    EvaluationCollection evaluationMongoDb;

    @Override
    public void loadDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertValue(Evaluation object) {
        evaluationMongoDb.insert(object);
    }

    @Override
    public List<Evaluation> getAll() {
        return evaluationMongoDb.getAll();
    }
    
}

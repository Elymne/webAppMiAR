package api;

import java.util.List;

public interface MongoDbQuery<T> {

    void loadDatabase();

    void insertValue(T object);
    
    List<T> getAll();

}

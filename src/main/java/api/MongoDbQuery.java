package api;

public interface MongoDbQuery<T> {

    void loadDatabase();

    void insertValue(T object);

}

package api;

import api.entities.Event;
import java.util.List;

public interface IMongoDb<T> {

    public void clear();

    public void insert(T obj);

    public void insertAll(List<T> obj);

    public List<T> getAll();

}

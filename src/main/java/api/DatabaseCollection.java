package api;

import java.util.List;

public interface DatabaseCollection< T> {

    public void clear();

    public void insert(T obj);

    public void insertAll(List<T> obj);

    public List<T> getAll();

}

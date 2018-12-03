package api;

import java.util.List;

public interface Repository<T> {

    public List<T> getAll();

}

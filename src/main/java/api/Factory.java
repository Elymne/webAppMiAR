package api;

import api.entities.Event;
import java.util.List;

public interface Factory<T> {
    
    public List<T> getAll();
    
}

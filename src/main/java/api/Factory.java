package api;

import java.util.List;

public interface Factory<T> {  
    public List<T> getAll(); 
}

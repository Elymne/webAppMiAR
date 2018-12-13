package api;

import org.springframework.stereotype.Service;

public interface ILoader<T> {
    void loadDatabase();
}

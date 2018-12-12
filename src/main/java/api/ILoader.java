package api;

import org.springframework.stereotype.Service;

@Service
public interface ILoader<T> {
    void loadDatabase();
}

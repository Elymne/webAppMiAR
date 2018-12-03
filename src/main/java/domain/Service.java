package domain;

import api.Factory;
import api.entities.Event;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class Service {

    @Autowired
    Factory factory;

    public List<Event> getAllEvents() {
        return factory.getAllEvents();
    }

}

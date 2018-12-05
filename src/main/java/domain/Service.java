package domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.Database;
import api.Factory;
import api.LoadDatabase;
import api.entities.Event;

public class Service {

    @Autowired
    Factory<Event> factory;
    
    @Autowired
    LoadDatabase loadDatabase;

    @Autowired
    Database mongodb;

    public List< Event> getAllEvents() {
        if(factory.getAll().isEmpty())
            loadDatabase.loadDatabase();
        return factory.getAll();
    }

    public Event getEventById(String id) {
        Event res = null;
        for (Event unEvent : factory.getAll()) {
            if (unEvent.recordid.equals(id)) {
                res = unEvent;
            }
        }
        return res;
    }
}

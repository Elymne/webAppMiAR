package domain;

import api.Factory;
import api.LoadBdd;
import api.entities.Event;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class Service {

    @Autowired
    Factory factory;
    
    @Autowired
    LoadBdd loadBdd;

    public List<Event> getAllEvents() {
        return factory.getAllEvents();
    }
    
    public void loadAllEvent() {
        loadBdd.putAllEvents();
    }
    
    public Event getEventById(String id){
        Event res = null;
        for(Event unEvent : factory.getAllEvents()){
            if(unEvent.getId().equals(id)){
                res = unEvent;
            }
        }
        return res;
    }

}

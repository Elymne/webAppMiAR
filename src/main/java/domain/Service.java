package domain;

import api.Factory;
import api.LoadBdd;
import api.entities.Commentary;
import api.entities.Event;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class Service {

    @Autowired
    Factory<Event> eventFactory;

    @Autowired
    Factory<Commentary> commentaryFactory;

    @Autowired
    LoadBdd loadBdd;

    public List<Event> getAllEvents() {
        return eventFactory.getAll();
    }

    public void loadAllEvent() {
        loadBdd.putAllEvents();
    }

    public Event getEventById(String id) {
        Event res = null;
        for (Event unEvent : eventFactory.getAll()) {
            if (unEvent.id.equals(id)) {
                res = unEvent;
            }
        }
        return res;
    }

    public List<Commentary> getAllCommentaryById(String id) {
        List<Commentary> res = new ArrayList<>();
        for (Commentary unCommentaire : commentaryFactory.getAll()) {
            if (unCommentaire.idref.equals(id)) {
                res.add(unCommentaire);
            }
        }
        return res;
    }

}

package domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.Factory;
import api.MongoDbQuery;
import api.entities.Commentary;
import api.entities.Event;
import api.entities.Parking;
import api.entities.User;

public class Service {

    @Autowired
    Factory<Event> eventFactory;

    @Autowired
    Factory<Parking> parkingFactory;

    @Autowired
    Factory<Commentary> commentaryFactory;

    @Autowired
    Factory<User> userFactory;

    @Autowired
    MongoDbQuery<Event> eventQuery;

    @Autowired
    MongoDbQuery<User> userQuery;

    Delta delta = new Delta();

    public List< Event> getAllEvents() {
        if (eventFactory.getAll().isEmpty()) {
            eventQuery.loadDatabase();
        }
        return eventFactory.getAll();
    }

    public Event getEventById(String id) {
        Event res = null;
        for (Event event : eventFactory.getAll()) {
            if (event.recordid.equals(id)) {
                res = event;
            }
        }
        return res;
    }

    public List<Event> getEventByLocalisation(String latitude, String longitude, String radius) {
        List<Event> res = new ArrayList<>();
        for (Event event : getAllEvents()) {
            if (delta.distance(Double.parseDouble(latitude), Double.parseDouble(longitude), event.locationX, event.locationY) <= Double.parseDouble(radius)) {
                res.add(event);
            }

        }
        return res;
    }

    public List< Commentary> getAllCommentaryById(String id) {
        List< Commentary> res = new ArrayList<>();
        for (Commentary commentary : commentaryFactory.getAll()) {
            if (commentary.idref.equals(id)) {
                res.add(commentary);
            }
        }
        return res;
    }

    public List< User> getAllUser() {
        return userFactory.getAll();
    }

    public boolean isValidAuthentification(String accountName, String password) {
        boolean res = true;
        for (User user : userFactory.getAll()) {
            if (user.login.equals(accountName)) {
                if (!user.password.equals(user)) {
                    res = false;
                }
            }
        }
        return res;
    }

    public boolean isValidAccountName(String accountName) {
        boolean res = true;
        for (User user : userFactory.getAll()) {
            if (user.login.equals(accountName)) {
                res = false;
            }
        }
        return res;
    }

    public void addNewUser(User user) {
        userQuery.insertValue(user);
    }

    public void charge() {
        eventQuery.loadDatabase();
    }

    public List< Parking> getAllParkings() {
        return parkingFactory.getAll();
    }

}

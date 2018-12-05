package infra;

import api.IMongoDb;
import api.entities.Event;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;

public class EventMongoDatabase implements IMongoDb<Event> {

    MongoDatabaseClient mongoDatabaseClient = MongoDatabaseClient.getInstance();

    private MongoCollection<Event> lesEvents = mongoDatabaseClient.getRoot().getCollection("event", Event.class);

    @Override
    public void clear() {
        this.lesEvents.drop();
    }

    @Override
    public void insert(Event event) {
        this.lesEvents.insertOne(event);
    }

    @Override
    public void insertAll(List<Event> listEvent) {
        this.lesEvents.insertMany(listEvent);
    }

    @Override
    public List<Event> getAll() {
        List<Event> lesEvents = new ArrayList<>();

        for (Event unEvent : this.lesEvents.find(Event.class)) {
            lesEvents.add(unEvent);
        }

        return lesEvents;
    }

}

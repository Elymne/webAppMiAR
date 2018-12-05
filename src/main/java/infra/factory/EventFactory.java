package infra.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.Factory;
import api.MongoDbQuery;
import api.entities.Event;
import infra.database.collection.EventCollection;
import infra.repository.EventRepository;

public class EventFactory implements Factory<Event>, MongoDbQuery<Event> {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventCollection eventMongoDb;

    @Override
    public List<Event> getAll() {
        return eventMongoDb.getAll();
    }

    @Override
    public void loadDatabase() {
        eventMongoDb.clear();
        eventMongoDb.insertAll(getFromRepository());
    }

    private List< Event> getFromRepository() {
        return buildEvents(eventRepository.getAll());
    }

    private List< Event> buildEvents(JsonNode nodes) {
        ObjectMapper mapper = new ObjectMapper();
        List< Event> list = new ArrayList<>();

        try {
            for (JsonNode node : nodes) {
                Event event = mapper.readValue(node.findValue("fields").toString(), Event.class);
                event.recordid = node.get("recordid").textValue();

                list.add(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void insertValue(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
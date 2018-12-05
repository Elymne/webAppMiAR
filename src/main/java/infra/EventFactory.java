package infra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.Database;
import api.Factory;
import api.entities.Event;

public class EventFactory implements Factory {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    Database eventMongoDb;

    @Override
    public List< Event> getAll() {
        eventMongoDb.clear();
        eventMongoDb.insertAll(getFromRepository());

        return eventMongoDb.getEvents();
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
}

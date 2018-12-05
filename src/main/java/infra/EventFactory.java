package infra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import api.Factory;
import api.LoadBdd;
import api.entities.Event;

public class EventFactory implements Factory, LoadBdd {

    @Autowired
    EventRepository eventRepository;

    MongoDb mongoDb = new MongoDb();

    @Override
    public List< Event> getAll() {
        DBCollection resFromMongoDb = mongoDb.getAllEvent();
        List<Event> res = eventBuild(resFromMongoDb);
        return res;
    }

    @Override
    public void putAllEvents() {
        JsonNode nodes = eventRepository.getAll();
        mongoDb.loadEvent(buildEvents(nodes));
    }

    private List< Event> buildEvents(JsonNode nodes) {
        ObjectMapper mapper = new ObjectMapper();
        List< Event> list = new ArrayList<>();

        try {
            for (JsonNode node : nodes) {
                list.add(mapper.readValue(node.findValue("fields").toString(), Event.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Event> eventBuild(DBCollection objectDB) {
        List<Event> lesEvents = new ArrayList<>();
        DBObject obj;
        Event event;
        DBCursor cursor = objectDB.find();
        double locationX, locationY;
        while (cursor.hasNext()) {
            obj = cursor.next();
            event = new Event();
            event.id = (obj.get("_id").toString());
            event.nom = (obj.get("nom").toString());
            event.description = (obj.get("description").toString());
            event.type = (obj.get("type").toString());
            event.date = (obj.get("date").toString());
            event.heure_debut = (obj.get("heureDebut").toString());
            event.heure_fin = (obj.get("heureFin").toString());
            event.lieu = (obj.get("nomLieu").toString());
            event.adresse  = (obj.get("adresse").toString());
            event.url_internet_1 = (obj.get("siteWeb").toString());
            event.media_1 = (obj.get("imageUrl").toString());
            locationX = new Double(obj.get("locationX").toString());
            locationY = new Double(obj.get("locationY").toString());
            event.locationX = (locationX);
            event.locationY = (locationY);

            lesEvents.add(event);
        }
        return lesEvents;
    }

}

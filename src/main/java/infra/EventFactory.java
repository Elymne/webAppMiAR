package infra;

import api.Factory;
import api.LoadBdd;
import api.Repository;
import api.entities.Event;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import infra.data.event.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class EventFactory implements Factory, LoadBdd{

    @Autowired
    Repository<EventData> EventRepository;

    @Autowired
    Repository<DBCollection> EventMongoDb;

    EventMongoDb eventMongoDb = new EventMongoDb();

    @Override
    public List<Event> getAllEvents() {
        List<DBCollection> resFromRepo = EventMongoDb.getAll();
        List<Event> res = eventBuild(resFromRepo);
        return res;
    }

    @Override
    public void putAllEvents() {
        List<EventData> resFromRepo = EventRepository.getAll();
        eventMongoDb.loadEvent(eventDbBuild(resFromRepo));
    }

    public List<Event> eventDbBuild(List<EventData> objectList) {
        List<Event> res = new ArrayList<>();
        Event event = null;
        String location[];

        for (EventData obj : objectList) {
            event = new Event();
            for (Record record : obj.getRecords()) {
                event.setId(record.getRecordid());
                event.setNom(record.getFields().getNom());
                event.setDescription(record.getFields().getDescription());
                event.setType(record.getFields().getType());
                event.setDate(record.getFields().getDate());
                event.setHeureDeb(record.getFields().getHeureDebut());
                event.setHeureFin(record.getFields().getHeureFin());
                event.setNomLieu(record.getFields().getLieu());
                event.setAdresse(record.getFields().getAdresse());
                event.setSiteWeb(record.getFields().getUrlInternet1());
                event.setImageUrl(record.getFields().getMedia1());
                location = record.getFields().getLocation().split(",");
                event.setLocationX(Double.parseDouble(location[0]));
                event.setLocationY(Double.parseDouble(location[1]));
                res.add(event);
            }

        }
        return res;
    }

    public List<Event> eventBuild(List<DBCollection> objectList) {
        List<Event> lesEvents = new ArrayList<>();
        DBObject obj;
        Event event;
        DBCursor cursor = objectList.get(0).find();
        double locationX, locationY;
        while (cursor.hasNext()) {
            obj = cursor.next();
            event = new Event();
            event.setId(obj.get("_id").toString());
            event.setNom(obj.get("nom").toString());
            event.setDescription(obj.get("description").toString());
            event.setType(obj.get("type").toString());
            event.setDate(obj.get("date").toString());
            event.setHeureDeb(obj.get("heureDebut").toString());
            event.setHeureFin(obj.get("heureFin").toString());
            event.setNomLieu(obj.get("nomLieu").toString());
            event.setAdresse(obj.get("adresse").toString());
            event.setSiteWeb(obj.get("siteWeb").toString());
            event.setImageUrl(obj.get("imageUrl").toString());
            locationX = new Double(obj.get("_id").toString());
            locationY = new Double(obj.get("_id").toString());
            event.setLocationX(locationX);
            event.setLocationY(locationY);
            
            lesEvents.add(event);
        }
        return lesEvents;
    }

}

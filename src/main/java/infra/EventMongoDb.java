package infra;

import api.Repository;
import api.entities.Event;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.ArrayList;
import java.util.List;

public class EventMongoDb implements Repository<DBCollection> {

    @Override
    public List<DBCollection> getAll() {
        List<DBCollection> res = new ArrayList<>();
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        res.add(mongoClient.getDB("appWeb").getCollection("event"));
        return res;
    }

    public void loadEvent(List<Event> lesEvents) {
        
        List<Object> attribut = new ArrayList<>();
        int id = 0;
        DBObject event;
        MongoClient mongoClient = new MongoClient();
        DBCollection collection = mongoClient.getDB("appWeb").getCollection("event");
        collection.remove(new BasicDBObject());

        for (Event unEvent : lesEvents) {
            
            attribut = verification(unEvent);
            
            event = new BasicDBObject("_id", Integer.toString(id))
                    .append("nom", unEvent.getNom())
                    .append("description", unEvent.getDescription())
                    .append("type", unEvent.getType())
                    .append("date", unEvent.getDate())
                    .append("heureDebut", attribut.get(2))
                    .append("heureFin", attribut.get(3))
                    .append("nomLieu", unEvent.getNomLieu())
                    .append("adresse", unEvent.getAdresse())
                    .append("siteWeb", attribut.get(0))
                    .append("imageUrl", attribut.get(1))
                    .append("locationX", unEvent.getLocationX())
                    .append("locationY", unEvent.getLocationY());
            collection.insert(event);
            id++;
        }
    }

    private List<Object> verification(Event unEvent) {
        String type, date, heureDebut, heureFin, nomLieu, adresse, siteWeb, imageUrl, locationX, locationY;
        List<Object> res = new ArrayList<>();
        if (unEvent.getSiteWeb() == null) {
            siteWeb = "";
        } else {
            siteWeb = unEvent.getSiteWeb();
        }
        res.add(siteWeb);
        if (unEvent.getImageUrl() == null) {
            imageUrl = "";
        } else {
            imageUrl = unEvent.getImageUrl();
        }
        res.add(imageUrl);
        if (unEvent.getHeureDeb() == null) {
            heureDebut = "";
        } else {
            heureDebut = unEvent.getHeureDeb();
        }
        res.add(heureDebut);
        if (unEvent.getHeureFin() == null) {
            heureFin = "";
        } else {
            heureFin = unEvent.getHeureFin();
        }
        res.add(heureFin);

        return res;

    }
}

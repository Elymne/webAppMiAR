package infra;

import api.Repository;
import api.entities.Event;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
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
        
        int id = 0;
        DBObject event;
        MongoClient mongoClient = new MongoClient();
        DBCollection collection = mongoClient.getDB("appWeb").getCollection("event");
        collection.remove(new BasicDBObject());

        for (Event unEvent : lesEvents) {
            
            event = new BasicDBObject("_id", Integer.toString(id))
                    .append("nom", unEvent.getNom())
                    .append("description", unEvent.getDescription())
                    .append("type", unEvent.getType())
                    .append("date", unEvent.getDate())
                    .append("heureDebut", unEvent.getHeureDeb())
                    .append("heureFin", unEvent.getHeureFin())
                    .append("nomLieu", unEvent.getNomLieu())
                    .append("adresse", unEvent.getAdresse())
                    .append("siteWeb", unEvent.getSiteWeb())
                    .append("imageUrl", unEvent.getImageUrl())
                    .append("locationX", unEvent.getLocationX())
                    .append("locationY", unEvent.getLocationY());
            collection.insert(event);
            id++;
        }
    }
}

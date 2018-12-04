package infra;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import api.entities.Event;
import com.mongodb.DBObject;

public class EventMongoDb {

    public DBCollection getAll() {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        return mongoClient.getDB("appWeb").getCollection("event");
    }

    public void loadEvent(List< Event> lesEvents) {
        List<Object> attribut = new ArrayList<>();
        DBObject event;
        MongoClient mongoClient = new MongoClient();
        DBCollection collection = mongoClient.getDB("appWeb").getCollection("event");
        collection.remove(new BasicDBObject());
        

        for (Event unEvent : lesEvents) {
            attribut = verification(unEvent);
            unEvent.setRealLocation();
            event = new BasicDBObject("_id", unEvent.id)
                    .append("nom", unEvent.nom)
                    .append("description", unEvent.description)
                    .append("type", unEvent.type)
                    .append("date", unEvent.date)
                    .append("heureDebut", attribut.get(2))
                    .append("heureFin", attribut.get(3))
                    .append("nomLieu", attribut.get(4))
                    .append("adresse", unEvent.adresse)
                    .append("siteWeb", attribut.get(0))
                    .append("imageUrl", attribut.get(1))
                    .append("locationX", unEvent.locationX)
                    .append("locationY", unEvent.locationY);
            collection.insert(event);
        }
    }
    
    private List<Object> verification(Event unEvent) {
        String heureDebut, heureFin, siteWeb, imageUrl, lieu;
        List<Object> res = new ArrayList<>();
        if (unEvent.media_1 == null) {
            siteWeb = "";
        } else {
            siteWeb = unEvent.media_1;
        }
        res.add(siteWeb);
        if (unEvent.url_internet_1 == null) {
            imageUrl = "";
        } else {
            imageUrl = unEvent.url_internet_1;
        }
        res.add(imageUrl);
        if (unEvent.heure_debut == null) {
            heureDebut = "";
        } else {
            heureDebut = unEvent.heure_debut;
        }
        res.add(heureDebut);
        if (unEvent.heure_fin == null) {
            heureFin = "";
        } else {
            heureFin = unEvent.heure_fin;
        }
        res.add(heureFin);
        if (unEvent.lieu == null) {
            lieu = "";
        } else {
            lieu = unEvent.lieu ;
        }
        res.add(lieu);

        return res;

    }
}

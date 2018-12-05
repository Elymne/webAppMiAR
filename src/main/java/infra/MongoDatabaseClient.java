package infra;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import api.Database;
import api.entities.Event;

public class MongoDatabaseClient implements Database {

    private MongoClient client;
    private MongoDatabase root;

    private MongoCollection< Event> lesEvents;

    public MongoDatabaseClient() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("api.entities").build();

        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodecProvider));

        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();

        this.client = new MongoClient(new ServerAddress(host, port), options);

        this.root = this.client.getDatabase(name);
        this.lesEvents = this.root.getCollection("event", Event.class);
    }

    @Override
    public void clear() {
        this.lesEvents.drop();
    }

    @Override
    public void insert(Event event) {
        this.lesEvents.insertOne(event);
    }

    @Override
    public void insertAll(List< Event> events) {
        this.lesEvents.insertMany(events);
    }

    @Override
    public List< Event> getEvents() {
        List< Event> lesEvents = new ArrayList<>();

        for (Event unEvent : this.lesEvents.find(Event.class)) {
            lesEvents.add(unEvent);
        }

        return lesEvents;
    }

}

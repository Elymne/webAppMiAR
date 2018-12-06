package application.appWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import api.Database;
import api.Factory;
import api.MongoDbQuery;
import api.entities.Commentary;
import api.entities.Event;
import api.entities.Parking;
import api.entities.User;
import domain.Service;
import infra.database.MongoDatabaseClient;
import infra.database.collection.CommentaryCollection;
import infra.database.collection.EventCollection;
import infra.database.collection.ParkingCollection;
import infra.database.collection.UserCollection;
import infra.factory.CommentaryFactory;
import infra.factory.EventFactory;
import infra.factory.ParkingFactory;
import infra.factory.UserFactory;
import infra.repository.EventRepository;
import infra.repository.ParkingRepository;

@Configuration
public class Config {

    // Service
    @Bean
    public Service getService() {
        return new Service();
    }

    // Database
    @Bean
    public Database getDatabase() {
        return MongoDatabaseClient.getInstance();
    }

    // Factories
    @Bean
    @Primary
    public Factory< Event> getEventFactory() {
        return new EventFactory();
    }

    @Bean
    @Primary
    public Factory< Commentary> getCommentaryFactory() {
        return new CommentaryFactory();
    }

    @Bean
    @Primary
    public Factory< User> getUserFactory() {
        return new UserFactory();
    }

    @Bean
    @Primary
    public Factory< Parking> getParkingFactory() {
        return new ParkingFactory();
    }

    // Repositories
    @Bean
    public EventRepository getEventRepository() {
        return new EventRepository();
    }

    @Bean
    public ParkingRepository getParkingRepository() {
        return new ParkingRepository();
    }

    // Collections
    @Bean
    public EventCollection getEventCollection() {
        return new EventCollection();
    }

    @Bean
    public ParkingCollection getParkingCollection() {
        return new ParkingCollection();
    }

    @Bean
    public CommentaryCollection getCommentaryCollection() {
        return new CommentaryCollection();
    }

    @Bean
    public UserCollection getUserCollection() {
        return new UserCollection();
    }

    // Queries
    @Bean
    public MongoDbQuery< Event> getEventMongoDbQuery() {
        return new EventFactory();
    }

    @Bean
    public MongoDbQuery< Commentary> getCommentaryMongoDbQuery() {
        return new CommentaryFactory();
    }

    @Bean
    public MongoDbQuery< User> getUserMongoDbQuery() {
        return new UserFactory();
    }

}

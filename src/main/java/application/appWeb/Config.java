package application.appWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.MongoDbQuery;
import api.entities.Commentary;
import api.entities.Event;
import api.entities.Parking;
import api.entities.User;
import domain.EventService;
import domain.ParkingService;
import domain.UserService;
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
import org.springframework.context.annotation.Primary;
import api.IUser;
import api.entities.Evaluation;
import infra.database.collection.EvaluationCollection;
import infra.factory.EvaluationFactory;

@Configuration
public class Config {

    // Service
    @Bean
    public EventService getEventService() {
        return new EventService();
    }

    @Bean
    public ParkingService getParkingService() {
        return new ParkingService();
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
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
    
    @Bean
    public EvaluationCollection getEvaluationCollection() {
        return new EvaluationCollection();
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

    @Bean
    public MongoDbQuery< Parking> getParkingMongoDbQuery() {
        return new ParkingFactory();
    }

    @Bean
    public MongoDbQuery< Evaluation> getEvaluationMongoDbQuery() {
        return new EvaluationFactory();
    }

    @Bean
    @Primary
    public IUser getAuthentification() {
        return new UserCollection();
    }

}

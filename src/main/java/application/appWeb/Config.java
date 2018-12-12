package application.appWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.entities.Event;
import api.entities.Parking;
import domain.EventService;
import domain.ParkingService;
import domain.UserService;
import infra.database.collection.CommentaryCollection;
import infra.database.collection.EventCollection;
import infra.database.collection.ParkingCollection;
import infra.database.collection.UserCollection;
import infra.factory.EventFactory;
import infra.factory.ParkingFactory;
import infra.repository.EventRepository;
import infra.repository.ParkingRepository;
import org.springframework.context.annotation.Primary;
import api.IUser;
import infra.database.collection.EvaluationCollection;
import api.ILoader;

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

    // Factory
    @Bean
    public ILoader< Event> getEventMongoDbQuery() {
        return new EventFactory();
    }

    @Bean
    public ILoader< Parking> getParkingMongoDbQuery() {
        return new ParkingFactory();
    }

    //Authentification
    @Bean
    @Primary
    public IUser getAuthentification() {
        return new UserCollection();
    }
}

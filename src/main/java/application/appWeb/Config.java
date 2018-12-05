package application.appWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.Database;
import api.Factory;
import api.entities.Commentary;
import api.entities.Event;
import api.entities.User;
import domain.Service;
import infra.database.MongoDatabaseClient;
import infra.database.collection.CommentaryCollection;
import infra.database.collection.EventCollection;
import infra.database.collection.UserCollection;
import infra.factory.CommentaryFactory;
import infra.factory.EventFactory;
import infra.factory.UserFactory;
import infra.repository.EventRepository;

@Configuration
public class Config {

    @Bean
    public Service getService() {
        return new Service();
    }

    @Bean
    public Database getDatabase() {
        return MongoDatabaseClient.getInstance();
    }

    @Bean
    public Factory< Event> getEventFactory() {
        return new EventFactory();
    }

    @Bean
    public EventCollection getEventCollection() {
        return new EventCollection();
    }

    @Bean
    public EventRepository getEventRepository() {
        return new EventRepository();
    }

    @Bean
    public Factory< Commentary> getCommentaryFactory() {
        return new CommentaryFactory();
    }

    @Bean
    public CommentaryCollection getCommentaryCollection() {
        return new CommentaryCollection();
    }

    @Bean
    public Factory< User> getUserFactory() {
        return new UserFactory();
    }

    @Bean
    public UserCollection getUserCollection() {
        return new UserCollection();
    }

}

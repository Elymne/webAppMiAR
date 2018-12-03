package application.appWeb;

import api.Factory;
import api.Repository;
import com.mongodb.DBCollection;
import domain.Service;
import infra.EventRepository;
import infra.EventFactory;
import infra.EventMongoDb;
import infra.data.event.EventData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    
    @Bean
    public Service getService() {
        return new Service();
    }
    
    @Bean
    public Factory getEventFactory() {
        return new EventFactory();
    }
    
    @Bean
    public Repository<EventData> getEventRepository() {
        return new EventRepository();
    }
    
    @Bean
    public Repository<DBCollection> getRepositoryBddEvent() {
        return new EventMongoDb();
    }
    
}

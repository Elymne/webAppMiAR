package application.appWeb;

import api.Factory;
import api.Repository;
import domain.Service;
import infra.EventRepository;
import infra.FactoryImplementation;
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
    public Factory getFactory() {
        return new FactoryImplementation();
    }
    
    @Bean
    public Repository<EventData> getRepositoryEvent() {
        return new EventRepository();
    }
    
}

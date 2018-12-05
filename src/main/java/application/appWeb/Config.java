package application.appWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.Database;
import api.Factory;
import api.entities.Event;
import domain.Service;
import infra.database.MongoDatabaseClient;
import infra.database.collection.EventCollection;
import infra.factory.EventFactory;
import infra.repository.EventRepository;


@Configuration
public class Config
{

	@Bean
	public Service getService()
	{
		return new Service();
	}

	@Bean
	public Factory< Event > getEventFactory()
	{
		return new EventFactory();
	}

	@Bean
	public EventRepository getEventRepository()
	{
		return new EventRepository();
	}

	@Bean
	public Database getDatabase()
	{
		return MongoDatabaseClient.getInstance();
	}

	@Bean
	public EventCollection getEventDatabase()
	{
		return new EventCollection();
	}


}

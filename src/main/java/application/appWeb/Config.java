package application.appWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.Database;
import api.Factory;
import domain.Service;
import infra.EventFactory;
import infra.EventRepository;
import infra.MongoDatabaseClient;

@Configuration
public class Config
{

	@Bean
	public Service getService()
	{
		return new Service();
	}

	@Bean
	public Factory getEventFactory()
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
		return new MongoDatabaseClient();
	}

}

package application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import api.Factory;
import api.entities.Event;
import api.entities.Parking;
import domain.CommentaryService;
import domain.EventService;
import domain.MarksService;
import domain.ParkingService;
import domain.UserService;
import infra.factory.EventFactory;
import infra.factory.ParkingFactory;
import infra.repository.EventRepository;
import infra.repository.ParkingRepository;

@Configuration
@EnableScheduling
@EnableMongoRepositories( basePackages = "infra.database.collection" )
public class Config
{
	// Service
	@Bean
	public EventService getEventService()
	{
		return new EventService();
	}

	@Bean
	public ParkingService getParkingService()
	{
		return new ParkingService();
	}

	@Bean
	public UserService getUserService()
	{
		return new UserService();
	}

	@Bean
	public MarksService getMarksService()
	{
		return new MarksService();
	}

	@Bean
	CommentaryService getCommentaryService()
	{
		return new CommentaryService();
	}

	// Repositories
	@Bean
	public EventRepository getEventRepository()
	{
		return new EventRepository();
	}

	@Bean
	public ParkingRepository getParkingRepository()
	{
		return new ParkingRepository();
	}

	// Factories
	@Bean
	public Factory< Event > getEventFactory()
	{
		return new EventFactory();
	}

	@Bean
	public Factory< Parking > getParkingMongoDbQuery()
	{
		return new ParkingFactory();
	}
}

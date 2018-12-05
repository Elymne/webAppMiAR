package domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.Database;
import api.Factory;
import api.entities.Event;

public class Service
{

	@Autowired
	Factory factory;

	@Autowired
	Database mongodb;

	public List< Event > getAllEvents()
	{
		return factory.getAllEvents();
	}

	public void loadAllEvents()
	{
		mongodb.test();
	}

	public Event getEventById( String id )
	{
		Event res = null;
		for( Event unEvent : factory.getAllEvents() )
		{
			if( unEvent.tid.equals( id ) )
			{
				res = unEvent;
			}
		}
		return res;
	}

}

package domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.entities.Event;
import api.entities.Mark;
import api.entities.User;
import infra.database.collection.MarkCollection;

public class MarksService
{
	@Autowired
	EventService events;

	@Autowired
	UserService users;

	@Autowired
	MarkCollection collection;

	public void rate( Mark marker )
	{
		Event	event	= events.getById( marker.recordid );
		User	user	= users.getUserByLogin( marker.userid );

		if( event == null || user == null )
			return;

		Mark mark = new Mark();
		mark.recordid	= marker.recordid;
		mark.userid		= marker.userid;
		mark.score		= marker.score;

		collection.insert( mark );
	}

	public List< Mark > getByEventId( String recordid )
	{
		return collection.findByRecordid( recordid );
	}
}

package domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.entities.Commentary;
import infra.database.collection.CommentaryCollection;

public class CommentaryService
{
	@Autowired
	CommentaryCollection collection;

	public List< Commentary > getAll()
	{
		return collection.findAll();
	}

	public List< Commentary > getByEventId( String id )
	{
		return collection.findByRecordid( id );
	}

	private boolean isValid( String message )
	{
		boolean res = true;
		if( message.length() < 20 )
		{
			res = false;
		}
		return res;
	}

	public boolean add( Commentary commentary )
	{
		Boolean valid = isValid( commentary.message );

		if( valid )
			collection.insert( commentary );

		return valid;
	}
}

package domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.entities.User;
import infra.database.collection.UserCollection;

public class UserService
{
	@Autowired
	UserCollection collection;

	public List< User > getAll()
	{
		return collection.findAll();
	}

	public User getUserByLogin( String login )
	{
		return collection.findByLogin( login );
	}

	public boolean isValidAuthentification( String accountName, String password )
	{
		boolean res = false;

		for( User user : collection.findAll() )
			if( user.login.equals( accountName ) )
				if( user.password.equals( password ) )
					res = true;

		return res;
	}

	public boolean isValidAccountName( String accountName )
	{
		boolean res = true;
		for( User user : collection.findAll() )
		{
			if( user.login.equals( accountName ) )
			{
				res = false;
			}
		}
		return res;
	}

	public void addNewUser( User user )
	{
		collection.insert( user );
	}

	public void login( User requestingUser )
	{
		User user = collection.findByLogin( requestingUser.login );
		user.connected = true;

		collection.save( user );
	}

	public void logout( User requestingUser )
	{
		User user = collection.findByLogin( requestingUser.login );
		user.connected = false;

		collection.save( user );
	}

	public void addBookmarks( String userId, String eventId )
	{
		User user = collection.findByLogin( userId );
		user.favoriteEvent.add( eventId );

		collection.save( user );
	}

	public void removeBookmarks( String userId, String eventId )
	{
		User user = collection.findByLogin( userId );
		user.favoriteEvent.remove( eventId );

		collection.save( user );
	}

	public boolean isConnected( User requestingUser )
	{
		User user = this.getUserByLogin( requestingUser.login );

		if( user == null )
			return false;

		return user.connected;
	}

}

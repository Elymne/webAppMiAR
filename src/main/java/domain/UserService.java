package domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.MongoDbQuery;
import api.entities.User;
import infra.database.collection.UserCollection;

public class UserService
{
	@Autowired
	MongoDbQuery< User > userQuery;

	// @Autowired
	// Authentification authentification;

	@Autowired
	UserCollection userCollection;

	public List< User > getAllUser()
	{
		return userQuery.getAll();
	}

	public User getUserByName( String userName )
	{
		User res = null;
		for( User user : getAllUser() )
		{
			if( user.login.equals( userName ) )
			{
				res = user;
			}
		}
		return res;
	}

	public boolean isValidAuthentification( String accountName, String password )
	{
		boolean res = false;

		for( User user : userQuery.getAll() )
			if( user.login.equals( accountName ) )
				if( user.password.equals( password ) )
					if( !user.connected )
						res = true;

		return res;
	}

	public boolean isValidAccountName( String accountName )
	{
		boolean res = true;
		for( User user : userQuery.getAll() )
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
		userQuery.insertValue( user );
	}

	public void login( User user )
	{
		// authentification.login(user);
	}

	public void logout( User user )
	{
		// authentification.logout(user);
	}

}

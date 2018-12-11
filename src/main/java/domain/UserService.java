package domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.MongoDbQuery;
import api.entities.User;

public class UserService
{

	@Autowired
	MongoDbQuery< User > userQuery;

	public List< User > getAllUser()
	{
		return userQuery.getAll();
	}

	public boolean isValidAuthentification( String accountName, String password )
	{
		boolean res = true;
		for( User user : userQuery.getAll() )
		{
			if( user.login.equals( accountName ) )
			{
				if( !user.password.equals( user ) )
				{
					res = false;
				}
			}
		}
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
		System.out.println( "adding user " + user.login );
		userQuery.insertValue( user );
	}

}

package api.entities;

import org.springframework.core.convert.converter.Converter;

public class User implements Converter< String, User >
{
	public String	accountName;
	public String	password;

	@Override
	public User convert( String from )
	{
		String[] data = from.split( "," );

		User user = new User();
		user.accountName	= data[ 0 ];
		user.password		= data[ 1 ];

		return user;
	}
}

package api.entities;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class User
{
	@Id
	public String login;

	public String	password;
	public boolean	connected	= false;
}

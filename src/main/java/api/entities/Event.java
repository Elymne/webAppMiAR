package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Event
{
	@JsonProperty( "id" )
	public String tid;

	public String	nom;
	public String	description;
	public String	type;
	public String	date;
	public String	heureDeb;
	public String	heureFin;
	public String	nomLieu;
	public String	adresse;
	public String	siteWeb;
	public String	imageUrl;
	public double	locationX;
	public double	locationY;

	@JsonSetter( "location" )
	public void parseLocation( String locations )
	{
		String[] location = locations.split( "," );
		this.locationX	= Double.parseDouble( location[ 0 ] );
		this.locationY	= Double.parseDouble( location[ 1 ] );
	}

}

package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Event
{
	public String record_id;

	public String	nom;
	public String	description;
	public String	type;
	public String	date;
	public String	heure_debut;
	public String	heure_fin;
	public String	lieu;
	public String	adresse;
	public String	site_web;
	public String	image_url;
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

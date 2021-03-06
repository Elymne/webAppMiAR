package api.entities;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties( ignoreUnknown = true )
//@Document( collection = "si tu veux que ce genre d'objet aille dans une collection custom" )
public class Event
{
	@Id
	public String recordid;

	public String	nom;
	public String	description;
	public String	type;
	public String	date;
	public String	heure_debut;
	public String	heure_fin;
	public String	lieu;
	public String	adresse;
	public String	url_internet_1;
	public String	media_1;
	public String	location;
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

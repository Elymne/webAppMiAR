package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Event
{
	@JsonProperty( "_id" )
	public String	id;
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

}

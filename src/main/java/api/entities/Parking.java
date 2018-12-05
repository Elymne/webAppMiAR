package api.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Parking
{
	public String	nom_complet;
	public String	telephone;
	public String	adresse;
	public String	moyen_paiement;
	public String	acces_transports_communs;
	public int		capacite_voiture;

	public double	locationX;
	public double	locationY;

	@JsonSetter( "location" )
	public void parseLocation( List< Double > locations )
	{
		this.locationX	= locations.get( 0 );
		this.locationY	= locations.get( 1 );
	}
}

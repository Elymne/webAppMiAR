package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Event
{
	public String	id;
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
        public String location;
	public double	locationX;
	public double	locationY;
        
        public void setRealLocation(){
            String resLocation[] = location.split(",");
            this.locationX = (Double.parseDouble(resLocation[0]));
            this.locationY = (Double.parseDouble(resLocation[1]));
        }

}

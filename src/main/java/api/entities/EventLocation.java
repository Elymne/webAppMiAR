package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class EventLocation
{
	public double	latitude;
	public double	longitude;
	public double	radius;

}

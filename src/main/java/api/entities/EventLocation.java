package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventLocation {

    public String latitude;
    public String longitude;
    public String radius;

}

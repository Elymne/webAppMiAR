package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Evaluation {
    
    public String eventId;
    public String userId;
    public Double evaluation;
    
}

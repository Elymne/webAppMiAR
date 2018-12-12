package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Evaluation {
    
    public String eventId;
    public List<Double> evaluation = new ArrayList<>();
    
}

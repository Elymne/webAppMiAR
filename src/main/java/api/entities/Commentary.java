package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commentary {
    
    @JsonProperty("id")
    public String tid;

    public String auteur;
    public String message;
    public String idref;
}

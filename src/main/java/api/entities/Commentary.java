package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commentary {

    public String message;
    public String idUser;
    public String idEvent;
}

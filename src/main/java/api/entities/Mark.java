package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Mark
{
	public String	recordid;
	public String	userid;

	public byte score;
}

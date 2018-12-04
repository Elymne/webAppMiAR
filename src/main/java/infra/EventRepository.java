package infra;

import api.Repository;
import infra.data.event.EventData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestTemplate;

public class EventRepository implements Repository<EventData>{

    @Override
    public List<EventData> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_agenda-evenements-nantes-nantes-metropole&rows=100";
        EventData resObj = restTemplate.getForObject(url, EventData.class);
        List<EventData> res = new ArrayList<>();
        res.add(resObj);
        return res;
    }

}

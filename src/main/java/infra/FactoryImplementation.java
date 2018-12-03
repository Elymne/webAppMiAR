package infra;

import api.Factory;
import api.Repository;
import api.entities.Event;
import infra.data.event.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class FactoryImplementation implements Factory {

    @Autowired
    Repository<EventData> EventRepository;

    @Override
    public List<Event> getAllEvents() {
        List<EventData> resFromRepo = EventRepository.getAll();
        List<Event> res = eventBuild(resFromRepo);
        return res;
    }

    public List<Event> eventBuild(List<EventData> objectList) {
        List<Event> res = new ArrayList<>();
        Event event = null;
        String location[];

        for (EventData obj : objectList) {
            event = new Event();
            for (Record record : obj.getRecords()) {
                event.setId(record.getRecordid());
                event.setNom(record.getFields().getNom());
                event.setDescription(record.getFields().getDescription());
                event.setType(record.getFields().getType());
                event.setDate(record.getFields().getDate());
                event.setHeureDeb(record.getFields().getHeureDebut());
                event.setHeureFin(record.getFields().getHeureFin());
                event.setAdresse(record.getFields().getAdresse());
                location = record.getFields().getLocation().split(",");
                event.setLocationX(Double.parseDouble(location[0]));
                event.setLocationY(Double.parseDouble(location[1]));
                res.add(event);
            }

        }
        return res;
    }

}

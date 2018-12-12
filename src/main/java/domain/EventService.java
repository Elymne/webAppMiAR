package domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.MongoDbQuery;
import api.entities.Commentary;
import api.entities.Evaluation;
import api.entities.Event;
import infra.database.MongoDatabaseClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.LocalDateTime;

public class EventService {

    @Autowired
    MongoDbQuery<Event> eventQuery;

    @Autowired
    MongoDbQuery<Commentary> commentaryQuery;
    
    @Autowired
    MongoDbQuery<Evaluation> evaluationQuery;

    Delta delta = new Delta();

    public List< Event> getAllEvents() {
        final Properties prop = new Properties();
        InputStream input = null;

        List<Event> res = new ArrayList<>();
        LocalDateTime date = new LocalDateTime();
        String[] parts = null;
        int year, month, day;
        if (eventQuery.getAll().isEmpty()) {
            eventQuery.loadDatabase();
        }

        try {
            input = new FileInputStream("src/main/resources/application.properties");
            prop.load(input);

            for (Event event : eventQuery.getAll()) {
                parts = event.date.split("-");
                year = Integer.parseInt(parts[0]);
                month = Integer.parseInt(parts[1]);
                day = Integer.parseInt(parts[2]);
                if (date.getYear() == year) {
                    if (date.getMonthOfYear() == month) {
                        if (date.getDayOfMonth() + Integer.parseInt(prop.getProperty("event.week")) >= day) {
                            res.add(event);
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MongoDatabaseClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MongoDatabaseClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public Event getEventById(String id) {
        Event res = null;
        for (Event event : eventQuery.getAll()) {
            if (event.recordid.equals(id)) {
                res = event;
            }
        }
        return res;
    }

    public List<Event> getEventByLocalisation(String latitude, String longitude, String radius) {
        List<Event> res = new ArrayList<>();
        for (Event event : getAllEvents()) {
            if (delta.distance(Double.parseDouble(latitude),
                    Double.parseDouble(longitude),
                    event.locationX,
                    event.locationY) <= Double.parseDouble(radius)) {
                res.add(event);
            }
        }
        return res;
    }

    public List< Commentary> getAllCommentaryById(String id) {
        List< Commentary> res = new ArrayList<>();
        for (Commentary commentary : commentaryQuery.getAll()) {
            if (commentary.idEvent.equals(id)) {
                res.add(commentary);
            }
        }
        return res;
    }

    public boolean isValidCommentary(String message) {
        boolean res = true;
        if (message.length() < 20) {
            res = false;
        }
        return res;
    }

    public void addNewCommentary(Commentary commentary) {
        commentaryQuery.insertValue(commentary);
    }

    public void charge() {
        eventQuery.loadDatabase();
    }

    public void addEvaluation(Evaluation evaluation) {
        evaluationQuery.insertValue(evaluation);
    }

    public boolean isValidEvaluation(Evaluation evaluation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Evaluation> getAllEvaluationById(String id){
        List< Evaluation> res = new ArrayList<>();
        for (Evaluation evaluation : evaluationQuery.getAll()) {
            if (evaluation.eventId.equals(id)) {
                res.add(evaluation);
            }
        }
        return res;
    }

}

package domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
import api.ILoader;
import api.IDatabaseCollection;
import ch.qos.logback.classic.gaffer.PropertyUtil;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    ILoader<Event> eventLoader;

    @Autowired
    IDatabaseCollection<Event> eventQuery;

    @Autowired
    IDatabaseCollection<Commentary> commentaryQuery;

    @Autowired
    IDatabaseCollection<Evaluation> evaluationQuery;

    Delta delta = new Delta();

    public List< Event> getAllEvents() {
        final Properties prop = new Properties();
        InputStream input = null;
        InputStream inputStream = null;

        List<Event> res = new ArrayList<>();
        LocalDateTime date = new LocalDateTime();
        String[] parts = null;
        int year, month, day;
        if (eventQuery.getAll().isEmpty()) {
            eventLoader.loadDatabase();
        }

        try {
            inputStream = PropertyUtil.class.getResourceAsStream("src/main/resources/application.properties");
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
        commentaryQuery.insert(commentary);
    }

    public void charge() {
        eventLoader.loadDatabase();
    }

    public void addEvaluation(Evaluation evaluation) {
        evaluationQuery.insert(evaluation);
    }

    public boolean isValidEvaluation(Evaluation evaluation) {
        boolean res = false;
        if (evaluation.evaluation >= 1 || evaluation.evaluation <= 5) {
            res = true;
        }
        for (Evaluation evaluationTest : getEvaluationByEventId(evaluation.eventId)) {
            if (evaluationTest.userId.equals(evaluation.userId)) {
                res = false;
            }
        }
        return res;
    }

    public List<Evaluation> getEvaluationByEventId(String id) {
        List<Evaluation> res = new ArrayList<>();
        for (Evaluation evaluation : evaluationQuery.getAll()) {
            if (evaluation.eventId.equals(id)) {
                res.add(evaluation);
            }
        }
        return res;
    }

    public double getAverageEvaluation(String id) {
        double average = 0;

        if (getEvaluationByEventId(id).size() > 0) {
            for (Evaluation evaluation : getEvaluationByEventId(id)) {
                average += evaluation.evaluation;
            }
            average = average / getEvaluationByEventId(id).size();
        }
        return average;
    }

}

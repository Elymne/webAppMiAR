package api;

import java.util.List;

import api.entities.Event;

public interface Database {

    public static String host = "localhost";
    public static int port = 27017;

    public static String name = "webapp";

    public void clear();

    public void insert(Event event);

    public void insertAll(List< Event> events);

    public List< Event> getEvents();
}

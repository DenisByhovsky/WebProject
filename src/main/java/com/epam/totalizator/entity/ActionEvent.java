package com.epam.totalizator.entity;

import java.util.ArrayList;

/**
 * List of events with some info(description)
 *@author Denis Byhovsky
 */

public class ActionEvent {

    private String description;
    private String kindOfSport;
    private ArrayList<Event> events;

    public ActionEvent() {
        events = new ArrayList<Event>();
    }

    public boolean addEvent(Event event){
        return events.add(event);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public String getKindOfSport() {
        return kindOfSport;
    }

    public void setKindOfSport(String kindOfSport) {
        this.kindOfSport = kindOfSport;
    }
}

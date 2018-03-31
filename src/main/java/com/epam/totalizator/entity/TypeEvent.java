package com.epam.totalizator.entity;

import java.util.ArrayList;

/**
 * Event list with info(description), with event type.
 *@author Denis Byhovsky
 */
public class TypeEvent {

    private String eventType;
    private String kindOfSport;
    private ArrayList<ActionEvent> descriptions;

    public TypeEvent() {
        descriptions = new ArrayList<ActionEvent>();
    }

    public boolean addListOfEvents(ActionEvent actionEvent){
        return descriptions.add(actionEvent); }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getKindOfSport() {
        return kindOfSport;
    }

    public void setKindOfSport(String kindOfSport) {
        this.kindOfSport = kindOfSport;
    }

    public ArrayList<ActionEvent> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(ArrayList<ActionEvent> descriptions) {
        this.descriptions = descriptions;
    }


}

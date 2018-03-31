package com.epam.totalizator.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Events table in database
 *@author Denis Byhovsky
 */

public class Event implements java.io.Serializable {
    private int id;
    private String eventType;
    private String description;
    private String kindOfSport;
    private String firstCompetitor;
    private String secondCompetitor;
    private int firstScore;
    private int secondScore;
    private Date startDate;
    private Time startTime;
    private Coefficient coefficient;
    private boolean isPlayed;

    public Event(){}

    public String getFirstCompetitor() {
        return firstCompetitor;
    }

    public void setFirstCompetitor(String firstCompetitor) {
        this.firstCompetitor = firstCompetitor;
    }

    public String getSecondCompetitor() {
        return secondCompetitor;
    }

    public void setSecondCompetitor(String secondCompetitor) {
        this.secondCompetitor = secondCompetitor;
    }

    public int getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(int firstScore) {
        this.firstScore = firstScore;
    }

    public int getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(int secondScore) {
        this.secondScore = secondScore;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Coefficient getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Coefficient coefficient) {
        this.coefficient = coefficient;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKindOfSport(String type) {
        this.kindOfSport = type;
    }

    public String getKindOfSport() {
        return kindOfSport;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id &&
                firstScore == event.firstScore &&
                secondScore == event.secondScore &&
                isPlayed == event.isPlayed &&
                Objects.equals(eventType, event.eventType) &&
                Objects.equals(description, event.description) &&
                Objects.equals(kindOfSport, event.kindOfSport) &&
                Objects.equals(firstCompetitor, event.firstCompetitor) &&
                Objects.equals(secondCompetitor, event.secondCompetitor) &&
                Objects.equals(startDate, event.startDate) &&
                Objects.equals(startTime, event.startTime) &&
                Objects.equals(coefficient, event.coefficient);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, eventType, description, kindOfSport, firstCompetitor, secondCompetitor, firstScore, secondScore, startDate, startTime, coefficient, isPlayed);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", description='" + description + '\'' +
                ", kindOfSport='" + kindOfSport + '\'' +
                ", firstCompetitor='" + firstCompetitor + '\'' +
                ", secondCompetitor='" + secondCompetitor + '\'' +
                ", firstScore=" + firstScore +
                ", secondScore=" + secondScore +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", coefficient=" + coefficient +
                ", isPlayed=" + isPlayed +
                '}';
    }
}

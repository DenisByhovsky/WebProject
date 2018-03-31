package com.epam.totalizator.service;

import com.epam.totalizator.entity.*;
import com.epam.totalizator.exception.ServiceException;
import java.util.ArrayList;

/**
 * Event service interface.
 */
public interface EventService {
    /**
     * Method fetch finished matches.Sort by increment time.
     * Make list of events  by description.
     * @return TypeEvent
     * @throws ServiceException
     */
    TypeEvent fetchAllFinishedMatches() throws ServiceException ;
    /**
     * Method select all events of same  eventType.
     * Make list of events  by description.
     * @param eventType
     * @return TypeEvent
     * @throws ServiceException
     */
    TypeEvent fetchAllEventsOfType(String eventType) throws ServiceException ;
    /**
     * Method select all events of same  kindOfSport.
     * Make list of events  by description.
     * @param kindOfSport
     * @return TypeEvent
     * @throws ServiceException
     */
    TypeEvent fetchMatchesOfType(String kindOfSport) throws ServiceException ;
    /**
     * Method fetch mathes without coefficients.Sort by decrement time
     * Make list of events  by description.
     * @return TypeEvent
     * @throws ServiceException
     */
    TypeEvent fetchMatchesWithoutCoefficients() throws ServiceException ;
    /**
     * Method get events  results of same kind of sport.Sorts by time.
     * Forms the list of events combined by description.
     * @param kindOfSport
     * @return TypeEvent
     * @throws ServiceException
     */
    TypeEvent fetchResultsOfKind(String kindOfSport) throws ServiceException ;
    /**
     * Method form list of matches and group by description.
     * @param events
     * @return TypeEvent
     */
    TypeEvent formListOfMatchesGroupedByDescriptions(ArrayList<Event> events);
    /**
     * Method sort events by increment time.
     * @param events
     */
    void sortByIncrementTime(ArrayList<Event> events) ;
    //TODO
    /**
     * Method sort events by decrement time.
     * @param events
     */
    void sortByTimeDecrement(ArrayList<Event> events) ;
    /**
     * Method sort events by decrement time.
     * @param events
     * @return ActionEvent
     */
   ActionEvent containsDescription(TypeEvent events, String description) ;
    /**
     * Method get all unfinished matches.Sort by increment time
     * @throws ServiceException
     * @return TypeEvent
     */
    TypeEvent fetchAllUnfinishedMatches() throws ServiceException;
    //TODO
    String determineResultsPage(Account account);
    /**
     * Method get event by eventId.
     * @param eventID
     * @throws ServiceException
     */
    Event fetchEventById(int eventID) throws ServiceException ;
    /**
     * Method add new event.
     * @param eventType
     * @param kindOfSport
     * @param dateTime
     * @param description
     * @param secondCompetitor
     * @param firstCompetitor
     * @throws ServiceException
     */
    void addNewEvent(String eventType, String kindOfSport, String description, String firstCompetitor, String secondCompetitor, String dateTime) throws ServiceException ;
    /**
     * Method update coefficients.
     * @param eventID
     * @param first
     * @param firstOrNobody
     * @param firstOrSecond
     * @param second
     * @param secondOrNobody
     * @param nobody
     * @throws ServiceException
     */
    void updateCoefficients(int eventID, String first, String nobody, String second, String firstOrNobody, String firstOrSecond, String secondOrNobody) throws ServiceException ;
    /**
     * Method update event.
     * @param eventID
     * @param kindOfSport
     * @param dateTime
     * @param description
     * @param secondCompetitor
     * @param firstCompetitor
     * @throws ServiceException
     */
    void updateEvent(int eventID, String kindOfSport, String description, String firstCompetitor, String secondCompetitor, String dateTime) throws ServiceException ;
    /**
     * Method delete event.
     * @param eventID
     * @throws ServiceException
     */
    void deleteEventByID(int eventID) throws ServiceException ;
    /**
     * Method arrange coefficients.
     * @param eventId
     * @param first
     * @param firstOrNobody
     * @param firstOrSecond
     * @param second
     * @param secondOrNobody
     * @param nobody
     * @throws ServiceException
     */
    void arrangeCoefficients(int eventId, String first, String nobody, String second, String firstOrNobody, String firstOrSecond, String secondOrNobody) throws ServiceException ;
    /**
     * Method select event by eventId.
     * @param eventID
     * @return event
     * @throws ServiceException
     */
    Event selectEventById(int eventID) throws ServiceException;
}

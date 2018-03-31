package com.epam.totalizator.dao.interfaces;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.entity.Event;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface (@code EventDAO) manipulate the events' data in the (@code events) table.
 * @author Denis Byhovsky
 */
public interface EventDAO {
    /**
     * Method select all events of the same  eventType.
     * @param eventType the type of event. Sport or cybersport
     * @return ArrayList of events.
     * @throws DAOException
     */
    ArrayList<Event> selectEventsOfType(String eventType) throws DAOException;
    /**
     * Method select all events.
     * @param isPlayed  match is finished or not.
     * @return ArrayList
     * @throws DAOException
     */
    ArrayList<Event> selectAllEvents(int isPlayed) throws DAOException;
    /**
     * Method select all matches  without coefficients.
     * @return ArrayList
     * @throws DAOException
     */
    ArrayList<Event> selectMatchesWithoutCoefficients() throws DAOException;
    /**
     *Method take all events.
     * @return ArrayList
     * @throws SQLException
     */
    ArrayList<Event> takeEvents(ResultSet set) throws SQLException;
    /**
     *Method take all events and coefficients.
     * @return ArrayList
     * @throws SQLException
     */
    ArrayList<Event> takeEventsAndCoefficients(ResultSet set) throws SQLException;
    /**
     *Method insert a new event.
     * @param eventType Sport or Cybersport
     * @param kindOfSport kind of sport
     * @param description description of event
     * @param firstCompetitor first competitor of event
     * @param secondCompetitor second competitor of event
     * @param dateTime start date and time of event
     * @throws DAOException
     */
    void addNewEvent(String eventType, String kindOfSport, String description, String firstCompetitor, String secondCompetitor, String dateTime) throws DAOException;
    /**
     * Method update the event information with eventID.
     * @param eventID id of the event
     * @param kindOfSport kind of sport
     * @param description description of event
     * @param firstCompetitor first competitor of event
     * @param secondCompetitor second competitor of event
     * @param dateTime start date and time of event
     * @throws DAOException
     */
    void updateEvent(int eventID, String kindOfSport, String description, String firstCompetitor, String secondCompetitor, String dateTime) throws DAOException;
    /**
     * Method delete event with eventID
     * @param eventID id of the event.
     * @throws DAOException
     */
    void deleteEventByID(int eventID) throws DAOException;
    /**
     * Method select events fo the same kindOfSport.
     * @param kindOfSport the kind of sport
     * @param isPlayed match is finished or not.
     * @return ArrayList of events.
     * @throws DAOException
     */
    ArrayList<Event> selectEventsOfKind(String kindOfSport, int isPlayed) throws DAOException;
    /**
     * This method insert  results of the event with eventID.
     * @param eventID id of the match
     * @param firstScore the score of the first competitor
     * @param secondScore the score of the second competitor
     * @throws DAOException
     */
    void insertEventResults(int eventID, int firstScore, int secondScore) throws DAOException;

    Event selectByKey(Integer eventID) throws DAOException;

    List<Event> selectAll() throws DAOException;
}

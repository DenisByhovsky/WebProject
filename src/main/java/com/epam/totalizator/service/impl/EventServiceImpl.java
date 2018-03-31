package com.epam.totalizator.service.impl;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.factory.DAOFactory;
import com.epam.totalizator.dao.interfaces.EventDAO;
import com.epam.totalizator.dao.mysql.MySQLCoefficientDAO;
import com.epam.totalizator.dao.mysql.MySQLEventDAO;
import com.epam.totalizator.entity.*;
import com.epam.totalizator.service.EventService;
import com.epam.totalizator.service.ServiceConstant;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.manager.ManagerHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Event service class.
 */
public class EventServiceImpl implements EventService{

    private static final Logger LOGGER = LogManager.getLogger(EventServiceImpl.class.getName());
    private EventDAO eventDAO;

    public EventServiceImpl() {
        eventDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEventDAO();
    }

    private static class EventServiceImplHolder {
        private static final EventService instance = new EventServiceImpl();
    }
    public static EventService getInstance() {
        return EventServiceImpl.EventServiceImplHolder.instance;
    }

    public TypeEvent fetchAllFinishedMatches() throws ServiceException {
        TypeEvent events;
        try {
            ArrayList<Event> list = new MySQLEventDAO(true).selectAllEvents(ServiceConstant.ResultBet.FINISHED);
            sortByIncrementTime(list);
            events = formListOfMatchesGroupedByDescriptions(list);
        } catch (DAOException e) {
            throw new ServiceException("Error in fetch all finished mathes" +e);
        }
        return events;
    }

    public TypeEvent fetchMatchesOfType(String kindOfSport) throws ServiceException {
        TypeEvent events;
        try {
            ArrayList<Event> list =new  MySQLEventDAO(true).selectEventsOfKind(kindOfSport, ServiceConstant.ResultBet.NOT_FINISHED);
            sortByIncrementTime(list);
            events = formListOfMatchesGroupedByDescriptions(list);
            events.setEventType(kindOfSport);
        } catch (DAOException e) {
            throw new ServiceException("Errpr in fetch mathes of type"+e);
        }
        return events;
    }

    public TypeEvent fetchMatchesWithoutCoefficients() throws ServiceException {
        TypeEvent events;
        try {
            ArrayList<Event> list = new MySQLEventDAO(true).selectMatchesWithoutCoefficients();
            sortByIncrementTime(list);
            events = formListOfMatchesGroupedByDescriptions(list);
        } catch (DAOException e) {
            throw new ServiceException("Error in fetch mathes without coefficients"+e);
        }
        return events;
    }

    public TypeEvent fetchAllEventsOfType(String eventType) throws ServiceException {
        TypeEvent events;
        try {
            ArrayList<Event> list = new MySQLEventDAO(true).selectEventsOfType(eventType);
            sortByIncrementTime(list);
            events = formListOfMatchesGroupedByDescriptions(list);
            events.setEventType(eventType);
        } catch (DAOException e) {
            throw new ServiceException("Error in fetch all events of type"+e);
        }
        return events;
    }


    public TypeEvent fetchResultsOfKind(String kindOfSport) throws ServiceException {
        TypeEvent events;
        try {
            ArrayList<Event> list = new MySQLEventDAO(true).selectEventsOfKind(kindOfSport, ServiceConstant.ResultBet.FINISHED);
            sortByTimeDecrement(list);
            events = formListOfMatchesGroupedByDescriptions(list);
        } catch (DAOException e) {
            throw new ServiceException("Error in fetch results of kind"+e);
        }
        return events;
    }


   public void sortByIncrementTime(ArrayList<Event> events) {
        Collections.sort(events, (o1, o2) -> {
            if (o1.getStartDate().equals(o2.getStartDate())) {
                return o1.getStartTime().compareTo(o2.getStartTime());
            } else {
                return o1.getStartDate().compareTo(o2.getStartDate());
            }
        });
    }


    //TODO
    public void sortByTimeDecrement(ArrayList<Event> events) {
        Collections.sort(events, (o1, o2) -> {
            if (o1.getStartDate().equals(o2.getStartDate())) {
                return o2.getStartTime().compareTo(o1.getStartTime());
            } else {
                return o2.getStartDate().compareTo(o1.getStartDate());
            }
        });
    }


    public ActionEvent containsDescription(TypeEvent events, String description) {
        if (events != null) {
            for (ActionEvent e : events.getDescriptions()) {
                if (e.getDescription().equals(description)) {
                    LOGGER.log(Level.INFO, "Contains description:", e);
                    return e;
                }
            }
        }
        return null;
    }

    public TypeEvent formListOfMatchesGroupedByDescriptions(ArrayList<Event> events) {
        TypeEvent typeEvent = new TypeEvent();
        for (Event e : events) {
            ActionEvent sameDescriptionEvents = containsDescription(typeEvent, e.getDescription());
            if (sameDescriptionEvents != null) {
                sameDescriptionEvents.addEvent(e);
            } else {
                sameDescriptionEvents = new ActionEvent();
                sameDescriptionEvents.setKindOfSport(e.getKindOfSport());
                sameDescriptionEvents.setDescription(e.getDescription());
                sameDescriptionEvents.addEvent(e);
                typeEvent.addListOfEvents(sameDescriptionEvents);
            }
        }
        return typeEvent;
    }
    public TypeEvent fetchAllUnfinishedMatches() throws ServiceException {
        TypeEvent events;
        try {
            ArrayList<Event> list =new  MySQLEventDAO(true).selectAllEvents(ServiceConstant.ResultBet.NOT_FINISHED);
            sortByIncrementTime(list);
            events = formListOfMatchesGroupedByDescriptions(list);
        } catch (DAOException e) {
            throw new ServiceException("Error in fetch all unfinished matches"+e);
        }
        return events;
    }

    public Event fetchEventById(int eventID) throws ServiceException {
        Event event;
        try {
            event = new MySQLEventDAO(true).selectByKey(eventID);
            LOGGER.log(Level.INFO, "Fetch event by id:",event);
        } catch (DAOException e) {
            throw new ServiceException("Error in fetch event by id"+e);
        }
        return event;
    }

    public void addNewEvent(String eventType, String kindOfSport, String description,
                            String firstCompetitor, String secondCompetitor, String dateTime) throws ServiceException {
        try {
            new MySQLEventDAO(true).addNewEvent(eventType, kindOfSport, description, firstCompetitor,
                    secondCompetitor, dateTime);
            LOGGER.log(Level.INFO,"Add new event - done ");
        } catch (DAOException e) {
            throw new ServiceException("Error in add new event"+e);
        }
    }

    public void updateCoefficients(int eventID, String first, String nobody, String second, String fon, String fos, String son) throws ServiceException {
        try {
            new MySQLCoefficientDAO(true).updateCoefficients(eventID, first, nobody, second, fon, fos, son);
            LOGGER.log(Level.INFO,"Coefficient was updated");
        } catch (DAOException e) {
            throw new ServiceException("Error in update coefficients"+e);
        }
    }

    public String determineResultsPage(Account account) {
        String page;
        if (account != null) {
            if (account.getRole() == AccountRole.CLIENT) {
                page = ManagerHandler.getPage(ManagerHandler.RESULTS_CLIENT);
                LOGGER.log(Level.INFO, "Client results");
            } else {
                page = ManagerHandler.getPage(ManagerHandler.ADMIN_RESULTS);
                LOGGER.log(Level.INFO, "Admin results");
            }
        } else {
            page = ManagerHandler.getPage(ManagerHandler.RESUTLS_PAGE);
            LOGGER.log(Level.INFO, "Result page");
        }
        return page;
    }

    public void updateEvent(int eventID, String kindOfSport, String description, String firstCompetitor, String secondCompetitor, String dateTime) throws ServiceException {
        try {
           new  MySQLEventDAO(true).updateEvent(eventID, kindOfSport, description, firstCompetitor, secondCompetitor, dateTime);
            LOGGER.log(Level.INFO,"Update event");
        } catch (DAOException e) {
            throw new ServiceException("Error in update event"+e);
        }
    }

    public void deleteEventByID(int eventID) throws ServiceException {
        try {
            new MySQLEventDAO(true).deleteEventByID(eventID);
            LOGGER.log(Level.INFO,"Delete event - done");
        } catch (DAOException e) {
            throw new ServiceException("Error in delete event by id"+e);
        }
    }

    public Event selectEventById(int eventID) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, "Select event by id done:");
            return new MySQLEventDAO(true).selectByKey(eventID);
        }catch (DAOException e){
            throw new ServiceException("Error in selected by id"+e);
        }
    }
    public void arrangeCoefficients(int eventId, String first, String nobody, String second, String fon, String fos, String son) throws ServiceException {
        try {
            int coefficientID = new MySQLCoefficientDAO(true).insertCoefficients(first, nobody, second, fon, fos, son);
            LOGGER.log(Level.INFO, "Coefficient id:", coefficientID);
            if (coefficientID >= 0) {
                new MySQLCoefficientDAO(true).arrangeCoefficients(eventId, coefficientID);
                LOGGER.log(Level.INFO,"Arrange coefficient");
            } else {
                throw new ServiceException("Incorrect last insert id.");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in arrange coefficients"+e);
        }
    }

}
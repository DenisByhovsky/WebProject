package com.epam.totalizator.dao.oracle;

import com.epam.totalizator.dao.AbstractDAO;
import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.interfaces.EventDAO;
import com.epam.totalizator.entity.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * (@code OracleEventDAO  ) provides the methods of
 * manipulating the accounts data
 */
public class OracleEventDAO  extends AbstractDAO<Integer,Event> implements EventDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    public OracleEventDAO(boolean transactionIf) {
        super(transactionIf);
    }

    //TODO
    @Override
    public ArrayList<Event> selectEventsOfKind(String kindOfSport, int isPlayed) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Event> selectEventsOfType(String eventType) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Event> selectAllEvents(int isPlayed) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Event> selectMatchesWithoutCoefficients() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Event> takeEvents(ResultSet set) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Event> takeEventsAndCoefficients(ResultSet set) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addNewEvent(String eventType, String kindOfSport, String description, String firstCompetitor, String secondCompetitor, String dateTime) throws DAOException {

    }

    @Override
    public List<Event> selectAll() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateEvent(int eventID, String kindOfSport, String description, String firstCompetitor, String secondCompetitor, String dateTime) throws DAOException {

    }

    @Override
    public void deleteEventByID(int eventID) throws DAOException {

    }

    @Override
    public void insertEventResults(int eventID, int firstScore, int secondScore) throws DAOException {

    }

    @Override
    public Event selectByKey(Integer key) throws DAOException {
        throw new UnsupportedOperationException();
    }
}

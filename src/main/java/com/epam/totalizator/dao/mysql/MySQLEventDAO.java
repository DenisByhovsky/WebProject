package com.epam.totalizator.dao.mysql;

import com.epam.totalizator.config.SQLFieldConstant;
import com.epam.totalizator.dao.AbstractDAO;
import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.interfaces.EventDAO;
import com.epam.totalizator.entity.Coefficient;
import com.epam.totalizator.entity.Event;
import com.epam.totalizator.pool.ConnectionPool;
import com.epam.totalizator.pool.ProxyConnection;
import com.epam.totalizator.exception.PoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * (@code MySQLEventDAO) provides the methods of
 * manipulating the accounts data in the (@code events)
 * table.
 */
public class MySQLEventDAO extends AbstractDAO<Integer,Event> implements EventDAO {

    private static final Logger LOGGER = LogManager.getLogger(MySQLEventDAO.class.getName());

    private final String SELECT_ALL_EVENTS = "SELECT * FROM events ";

    private final  String INSERT_NEW_EVENT="INSERT INTO events (event_type, kind_of_sport, description,first_competitor, second_competitor, start_date)  VALUES (?,?,?,?,?,?)";

    private static final String UPDATE_EVENT = "UPDATE events SET description = ?, first_competitor = ?, second_competitor = ?, start_date = ?, kind_of_sport = ? WHERE id = ?";

    private static final String DELETE_EVENT = "DELETE FROM events WHERE id = ?";

   private  final String  SELECT_EVENT_BY_ID ="SELECT id, event_type, kind_of_sport, description, first_competitor, second_competitor, first_score, second_score, start_date, coefficients_id, is_played FROM events WHERE id = ?";

    private  final String   INSERT_RESULTS="UPDATE events SET first_score = ?, second_score = ?, is_played = 1  WHERE events.id = ?";

    private  final String   SELECT_ALL= "SELECT e.id, e.event_type, e.kind_of_sport, e.description, e.first_competitor, e.second_competitor, e.first_score, e.second_score, e.start_date, e.coefficients_id, e.is_played, c.id, c.win_first, c.win_second, c.nobody, c.first_or_nobody, c.second_or_nobody, c.first_or_second FROM events AS e JOIN coefficients AS c ON e.coefficients_id = c.id WHERE e.is_played = ? AND c.id != 0";

    private final String  SELECT_WITHOUT_COEFFICIENTS="SELECT id, event_type, kind_of_sport, description,first_competitor, second_competitor, first_score, second_score, start_date, coefficients_id, is_played from events where coefficients_id = 0";

    private final String  SELECT_EVENTS_OF_TYPE="SELECT e.id, e.event_type, e.kind_of_sport, e.description, e.first_competitor, e.second_competitor, e.first_score, e.second_score, e.start_date, e.coefficients_id, e.is_played, c.id, c.win_first, c.win_second, c.nobody, c.first_or_nobody, c.second_or_nobody, c.first_or_second FROM events AS e JOIN coefficients AS c ON e.coefficients_id = c.id WHERE e.event_type = ? AND c.id != 0 AND e.is_played = 0";

    private  final String SELECT_EVENTS_IS_PLAYED="SELECT e.id, e.event_type, e.kind_of_sport, e.description, e.first_competitor, e.second_competitor, e.first_score, e.second_score, e.start_date, e.coefficients_id, e.is_played, c.id, c.win_first, c.win_second, c.nobody, c.first_or_nobody, c.second_or_nobody, c.first_or_second FROM events AS e JOIN coefficients AS c ON e.coefficients_id = c.id WHERE e.kind_of_sport = ?  AND c.id != 0 AND e.is_played = ?";

    public  MySQLEventDAO(boolean transactionFlag) {
        super(transactionFlag);
    }

    @Override
    public List<Event> selectAll() throws DAOException {
        ArrayList<Event> events = new ArrayList<>();
        try(ProxyConnection connection= ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_EVENTS)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Event event = new Event();
                event.setId(Integer.parseInt(resultSet.getString(SQLFieldConstant.ID)));
                event.setEventType(resultSet.getString(SQLFieldConstant.Event.EVENT_TYPE));
                event.setKindOfSport(resultSet.getString(SQLFieldConstant.Event.KIND_OF_SPORT));
                event.setDescription(resultSet.getString(SQLFieldConstant.Event.DESCRIPTION));
                event.setFirstCompetitor(resultSet.getString(SQLFieldConstant.Event.FIRST_COMPETITOR));
                event.setSecondCompetitor(resultSet.getString(SQLFieldConstant.Event.SECOND_COMPETITOR));
                event.setFirstScore(Integer.parseInt(resultSet.getString(SQLFieldConstant.Event.FIRST_SCORE)));
                event.setSecondScore(Integer.parseInt(resultSet.getString(SQLFieldConstant.Event.SECOND_SCORE)));
                event.setStartDate(resultSet.getDate(SQLFieldConstant.Event.START_DATE));
                event.setStartTime(resultSet.getTime(SQLFieldConstant.Event.START_DATE));
                events.add(event);
                for(Event ev:events) {
                  LOGGER.log(Level.INFO,"All events : ",ev.toString());
                }
            }
            return events;
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in select all events"+e);
        }
    }

    @Override
    public void addNewEvent(String eventType, String kindOfSport, String description, String firstCompetitor, String secondCompetitor, String dateTime) throws DAOException{
        try(ProxyConnection proxyConnection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_NEW_EVENT)) {
            preparedStatement.setString(1, eventType);
            preparedStatement.setString(2, kindOfSport);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, firstCompetitor);
            preparedStatement.setString(5, secondCompetitor);
            preparedStatement.setString(6, dateTime);
            preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO,"Add new event ");
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in add new events"+e);
        }
    }

    @Override
    public ArrayList<Event> selectEventsOfKind(String kindOfSport, int isPlayed) throws DAOException{
        ArrayList<Event> events;
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_EVENTS_IS_PLAYED)){
            statement.setString(1, kindOfSport);
            statement.setInt(2, isPlayed);
            ResultSet set = statement.executeQuery();
            events = takeEventsAndCoefficients(set);
            for(Event ev: events){
                LOGGER.log(Level.INFO,"Events of a kind ", ev.toString());
            }
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in select events of kind"+e);
        }
        return events;
    }

    @Override
    public ArrayList<Event> selectEventsOfType(String eventType) throws DAOException{
        ArrayList<Event> events;
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_EVENTS_OF_TYPE)){
            statement.setString(1, eventType);
            ResultSet set = statement.executeQuery();
            events = takeEventsAndCoefficients(set);

        }catch (SQLException | PoolException e){
            throw new DAOException("Error in select events of type"+e);
        }
        for(Event ev: events) {
            LOGGER.log(Level.INFO,"Events of a type" ,ev);
        }
        return events;
    }

    @Override
    public ArrayList<Event> selectAllEvents(int isPlayed) throws DAOException {
        ArrayList<Event> events;
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL)){
            statement.setInt(1, isPlayed);
            ResultSet set = statement.executeQuery();
            events = takeEventsAndCoefficients(set);

        }catch (SQLException | PoolException e){
            throw new DAOException("Error in select all events"+e);
        }
        for(Event ev: events){
            LOGGER.log(Level.INFO,"All events ", ev.toString());
        }
        return events;
    }

    @Override
    public ArrayList<Event> selectMatchesWithoutCoefficients() throws DAOException {
        ArrayList<Event> events;
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_WITHOUT_COEFFICIENTS)){
            ResultSet set = statement.executeQuery();
            events = takeEvents(set);
            for(Event ev: events) { LOGGER.log(Level.INFO, ev);}
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in select mathes without coefficients"+e);
        }
        return events;
    }

    @Override
    public ArrayList<Event> takeEvents(ResultSet resultSet) throws SQLException {
        ArrayList<Event> events = new ArrayList<>();
        Event event;
        while (resultSet.next()) {
            event = new Event();
            event.setEventType(resultSet.getString(SQLFieldConstant.Event.EVENT_TYPE));
            event.setKindOfSport(resultSet.getString(SQLFieldConstant.Event.KIND_OF_SPORT));
            event.setDescription(resultSet.getString(SQLFieldConstant.Event.DESCRIPTION));
            event.setFirstCompetitor(resultSet.getString(SQLFieldConstant.Event.FIRST_COMPETITOR));
            event.setSecondCompetitor(resultSet.getString(SQLFieldConstant.Event.SECOND_COMPETITOR));
            event.setKindOfSport(resultSet.getString(SQLFieldConstant.Event.KIND_OF_SPORT));
            event.setStartDate(resultSet.getDate(SQLFieldConstant.Event.START_DATE));
            event.setStartTime(resultSet.getTime(SQLFieldConstant.Event.START_DATE));
            event.setFirstScore(resultSet.getInt(SQLFieldConstant.Event.FIRST_SCORE));
            event.setSecondScore(resultSet.getInt(SQLFieldConstant.Event.SECOND_SCORE));
            event.setId(resultSet.getInt(SQLFieldConstant.ID));
            events.add(event);
            for(Event ev: events){
                LOGGER.log(Level.INFO, "Event extracted:", ev);
            }
        }
        return events;
    }

    @Override
    public ArrayList<Event> takeEventsAndCoefficients(ResultSet set) throws SQLException{
        ArrayList<Event> events = new ArrayList<>();
        Event event;
        Coefficient coefficient;
        while (set.next()){
            event = new Event();
            event.setEventType(set.getString(SQLFieldConstant.Event.EVENT_TYPE));
            event.setKindOfSport(set.getString(SQLFieldConstant.Event.KIND_OF_SPORT));
            event.setDescription(set.getString(SQLFieldConstant.Event.DESCRIPTION));
            event.setFirstCompetitor(set.getString(SQLFieldConstant.Event.FIRST_COMPETITOR));
            event.setSecondCompetitor(set.getString(SQLFieldConstant.Event.SECOND_COMPETITOR));
            event.setKindOfSport(set.getString(SQLFieldConstant.Event.KIND_OF_SPORT));
            event.setStartDate(set.getDate(SQLFieldConstant.Event.START_DATE));
            event.setStartTime(set.getTime(SQLFieldConstant.Event.START_DATE));
            event.setFirstScore(set.getInt(SQLFieldConstant.Event.FIRST_SCORE));
            event.setSecondScore(set.getInt(SQLFieldConstant.Event.SECOND_SCORE));
            event.setId(set.getInt(SQLFieldConstant.ID));
            coefficient = new Coefficient();
            coefficient.setWinFirst(set.getDouble(SQLFieldConstant.Coefficient.WIN_FIRST));
            coefficient.setWinSecond(set.getDouble(SQLFieldConstant.Coefficient.WIN_SECOND));
            coefficient.setFirstOrNobody(set.getDouble(SQLFieldConstant.Coefficient.FIRST_OR_NOBODY));
            coefficient.setNobody(set.getDouble(SQLFieldConstant.Coefficient.NOBODY));
            coefficient.setFirstOrSecond(set.getDouble(SQLFieldConstant.Coefficient.FIRST_OR_SECOND));
            coefficient.setSecondOrNobody(set.getDouble(SQLFieldConstant.Coefficient.SECOND_OR_NOBODY));
            event.setCoefficient(coefficient);
            events.add(event);
            for(Event ev: events){
                LOGGER.log(Level.INFO, "Event  and coeff extracted:", ev);
            }
        }
        return events;
    }

    @Override
    public void updateEvent(int eventID, String kindOfSport, String description, String firstCompetitor, String secondCompetitor, String dateTime) throws DAOException{
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_EVENT)){
            statement.setString(1, description);
            statement.setString(2, firstCompetitor);
            statement.setString(3, secondCompetitor);
            statement.setString(4, dateTime);
            statement.setString(5, kindOfSport);
            statement.setInt(6, eventID);
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Event updated:");
        }catch (PoolException | SQLException e){
            throw new DAOException("Error in update event"+e);
        }
    }

    @Override
    public void deleteEventByID(int eventID) throws DAOException {
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_EVENT)){
            statement.setInt(1, eventID);
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Delete event by id:",eventID);
        }catch (PoolException | SQLException e){
            throw new DAOException("Error in delete event by id"+e);
        }
    }

    @Override
    public void insertEventResults(int eventID, int firstScore, int secondScore) throws DAOException{
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_RESULTS)){
            statement.setInt(1, firstScore);
            statement.setInt(2, secondScore);
            statement.setInt(3, eventID);
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Insert event res");
        }catch (PoolException | SQLException e){
            throw new DAOException("Error in insert wvwnt result"+e);
        }
    }

    @Override
    public Event selectByKey(Integer eventID) throws DAOException{
        Event event;
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_EVENT_BY_ID)) {
            statement.setInt(1, eventID);
            ResultSet set = statement.executeQuery();
            event = takeEvents(set).get(0);
            LOGGER.log(Level.INFO,event.toString());
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in select event by key"+e);
        }
        return event;
    }

}

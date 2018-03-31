package com.epam.totalizator.dao.interfaces;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.entity.Coefficient;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface (@code CoefficientDAO) manipulate the events' coefficients in the (@code coefficients) table.
 * @author Denis Byhovsky
 */
public interface CoefficientDAO {
    /**
     * Method insert coefficients to the table.
     * @param first First win
     * @param nobody Dead heat
     * @param second Second win
     * @param firstOrNobody First Or Nobody
     * @param secondOrNobody Second or Nobody
     * @param firsOrSecond First Or Second
     * @return int the id of inserted row
     * @throws DAOException
     */
    int insertCoefficients(String first, String nobody, String second, String firstOrNobody, String firsOrSecond, String secondOrNobody) throws DAOException;
    /**
     * Method update event coefficients with eventId.
     * @param eventId id of the event
     * @param first First win
     * @param nobody Dead heat
     * @param second Second win
     * @param fon First Or Nobody
     * @param fos First Or Second
     * @param son Second or Nobody
     * @return int the id of inserted row
     * @throws DAOException
     */
    void updateCoefficients(int eventId, String first, String nobody, String second, String fon, String fos, String son) throws DAOException;
    /**
     * Method arrange coefficients of of existing match with eventID
     * @param eventID id of the event
     * @param coefficientID id of the coefficients
     * @throws DAOException
     */
    void arrangeCoefficients(int eventID, int coefficientID)throws DAOException;
    /**
     * Method update coefficients for bets that have already been made .
     * @param bets
     * @throws DAOException
     */
    void updateCoefficientForBets(ArrayList<Bet> bets) throws DAOException;

    Coefficient selectByKey(Integer key) throws DAOException ;

    List<Coefficient> selectAll() throws DAOException;
}

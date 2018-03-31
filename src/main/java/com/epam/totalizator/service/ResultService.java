package com.epam.totalizator.service;

import com.epam.totalizator.exception.ServiceException;
/**
 * Result service interface.
 */
    public interface  ResultService {
    /**
     * Method put bets results.
     * @param eventID
     * @param firstScore
     * @param secondScore
     * @throws ServiceException
     */
    void putBetsResults(int eventID, int firstScore, int secondScore) throws ServiceException ;
    /**
     * Method put event results.
     * @param eventID
     * @param firstScore
     * @param secondScore
     * @throws ServiceException
     */
    void putResults(int eventID, int firstScore, int secondScore) throws ServiceException;
    /**
     * Method calculate bet  results.
     * @param typeBet
     * @param firstScore
     * @param secondScore
     * @return Transaction
     * @throws ServiceException
     */
    int determineTheResult(String typeBet, int firstScore, int secondScore) throws ServiceException ;
}

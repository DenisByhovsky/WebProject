package com.epam.totalizator.service;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.entity.Event;
import com.epam.totalizator.exception.PoolException;
import com.epam.totalizator.exception.ServiceException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Bet service interface.
 */
public interface BetService {
    /**
     * Method delete  bet.
     * @param bets
     * @param id
     * @return ArrayList
     */
    void deleteBet(ArrayList<Bet> bets, int id) ;
    /**
     * Method fetch  all lost bets of account.
     * @param account
     * @return ArrayList
     * @throws ServiceException
     */
    ArrayList<Bet> fetchLostBets(Account account) throws ServiceException;
    /**
     * Method get  all lost and won bets of account.
     * @param account
     * @return ArrayList
     * @throws ServiceException
     */
    ArrayList<Bet> getWonAndLostBets(Account account) throws ServiceException;
    /**
     * Method service new bet.
     * @param account
     * @return ArrayList
     * @throws DAOException
     */
    Bet serviceNewBet(Account account, String type, Event event, BigDecimal coef, BigDecimal amount);
    /**
     * Method fetch all won bets of account.
     * @param account
     * @return ArrayList
     * @throws ServiceException
     */
    ArrayList<Bet> fetchWonBets(Account account) throws ServiceException;
    /**
     * Method get all  bets of account.
     * @param login
     * @return ArrayList
     * @throws ServiceException
     */
    ArrayList<Bet> fetchAllBetsOfAccount(String login) throws ServiceException ;
    /**
     * Method get all  bets of same currency.
     * @param currency
     * @return ArrayList
     * @throws ServiceException
     */
    ArrayList<Bet> fetchAllBetsOfCurrency(String currency) throws ServiceException;
    /**
     * Method get  bet of by id.
     * @param id
     * @return bet
     * @throws ServiceException
     */
    Bet fetchBetById(int id) throws ServiceException, SQLException, PoolException;
    /**
     * Method  make new bet.
     * @param account
     * @param type
     * @param coef
     * @param amount
     * @param event
     * @return Bet
     * @throws ServiceException
     */
    Bet makeNewBet(Account account, String type, BigDecimal coef, BigDecimal amount, Event event) throws ServiceException ;
    /**
     * Method  cancel account bet.
     * @param account
     * @param amount
     * @throws ServiceException
     */
    void cancelBet(Account account, int id, BigDecimal amount) throws ServiceException;
    /**
     * Method  update event coefficients by id.
     * @param eventID
     * @param first
     * @param nobody
     * @param second
     * @param firstOrNobody
     * @param firstOrSecond
     * @param secondOrNobody
     * @throws ServiceException
     */
    void updateCoefficients(int eventID, String first, String nobody, String second, String firstOrNobody, String firstOrSecond, String secondOrNobody) throws ServiceException;
    /**
     * Method  update bet coefficient.
     * @param bet
     * @param first
     * @param nobody
     * @param second
     * @param firstOrNobody
     * @param firstOrSecond
     * @param secondOrNobody
     * @throws ServiceException
     */
    void updateCoefficient(Bet bet, String first, String nobody, String second, String firstOrNobody, String firstOrSecond, String secondOrNobody) throws ServiceException ;
}

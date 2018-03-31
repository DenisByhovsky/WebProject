package com.epam.totalizator.dao.interfaces;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.exception.PoolException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Bet DAO
 * @author Denis Byhovsky
 */
public interface BetDAO {
    /**
     * Create bet
     * Method to create a new bet in the system as a single transaction.
     * @param account
     * @param bet
     * @return int
     * @throws DAOException
     */
    int insertBet(Account account, Bet bet) throws DAOException;
    /**
     * Delete bet
     * Method to delete bet in the system as a single transaction.
     * @param account
     * @param id
     * @param amount
     * @throws DAOException
     */
    void deleteBet(Account account, int id, BigDecimal amount) throws DAOException;
    /**
     * Method select the winning bets of account.
     * @param account
     * @return ArrayList
     * @throws DAOException
     */
    ArrayList<Bet> selectWonBetsOfAccount(Account account) throws DAOException;
    /**
     * Method select  lost bets of account.
     * @param account
     * @return ArrayList
     * @throws DAOException
     */
    ArrayList<Bet> selectLostBetsOfAccount(Account account) throws DAOException;
    /**
     * Method select  lost bets with result of account.
     * @param account
     * @return ArrayList
     * @throws DAOException
     */
    ArrayList<Bet> selectBetsWithResult(Account account, int result) throws DAOException;
    /**
     * Method select all bets of account.
     * @param login
     * @return ArrayList
     * @throws DAOException
     */
    ArrayList<Bet> selectAllBetsOfAccount(String login) throws DAOException;
    /**
     * Method select all bets on match.
     * @param eventID
     * @return ArrayList
     * @throws DAOException
     */
    ArrayList<Bet> selectAllBetsOnMatch(int eventID) throws DAOException;
    /**
     * Method update the bets  coefficients if they have been changed.
     * @param allBetsOfMatch
     * @throws DAOException
     */
    void updateBetsResults(ArrayList<Bet> allBetsOfMatch) throws DAOException;
    /**
     * Method select all bets which were made by accounts with the invoices
     * of (@code currency)
     * @param currency
     * @return ArrayList
     * @throws DAOException
     */
    ArrayList<Bet> selectBetsOfCurrency(String currency) throws DAOException;
    /**
     * Method select playing bets of Account
     * @param account
     * @return ArrayList
     * @throws DAOException
     */
    ArrayList<Bet> selectPlayingBetsOfAccount(Account account) throws DAOException;

    Bet selectByKey(Integer id) throws DAOException, SQLException, PoolException;

    List<Bet> selectAll() throws DAOException;
    /**
     * Method get all bets
     * @param set
     * @return ArrayList
     * @throws SQLException
     */
    ArrayList<Bet> takeBets(ResultSet set) throws SQLException;
}

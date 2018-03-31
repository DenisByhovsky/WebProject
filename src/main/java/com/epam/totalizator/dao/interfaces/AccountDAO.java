package com.epam.totalizator.dao.interfaces;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.entity.Account;
import java.util.ArrayList;

/** Account DAO
 * @author Denis Byhovsky
 */
public interface AccountDAO  {
    /**
     * Method that takes account by login
     * @param login
     * @return account entity
     * @throws DAOException
     */
    Account selectByKey(String login) throws DAOException;
    /**
     * Login user
     * Method for user authorization in the system.
     * If the database has account and this account is (@code Client)
     * the method also initializes the (@code AccountInvoice).
     * @param login String
     * @param password String
     * @return Account
     * @throws DAOException
     */
    Account loginAccount(String login, String password) throws DAOException;
    /**
     * Create account
     * Method to create a new account in the system, and then create (@code AccountInvoice)
     * @param account
     * @throws DAOException
     */
    void insertAccount(Account account) throws DAOException;
    /**
     * Change password
     * @param login
     * @param newPassword
     * @return boolean
     * @throws DAOException
     */
    boolean updatePassword(String newPassword, String login) throws DAOException;
    /**
     * Method that takes all accounts from table 'accounts'
     * @return list of accounts
     * @throws DAOException
     */
    ArrayList<Account> selectAll() throws DAOException;
}
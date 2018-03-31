package com.epam.totalizator.service;

import com.epam.totalizator.entity.Account;
import com.epam.totalizator.exception.ServiceException;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Account service interface.
 */
public interface AccountService {
    /**
     * Method check passwords.
     * @param pass password
     * @param confirmPassword password to confirm
     * @return boolean
     */
    boolean checkPasswords(String pass, String confirmPassword);
    /**
     * Method fetch all  accounts as role Client.
     * @return ArrayList
     * @throws ServiceException
     */
    ArrayList<Account> fetchAllAccounts()throws ServiceException;
    /**
     * Authenticate the login and password. Check account in database and show all bets info
     * @param login
     * @param password
     * @return Account
     * @throws ServiceException
     */
    Account loginAccount(String login, String password) throws ServiceException ;
    /**
     * Checks if the account with login is existed.
     * @param login
     * @return boolean
     * @throws ServiceException
     */
    boolean checkAccountIsExist(String login) throws ServiceException;
    /**
     * Method update password of account.
     * @param newPassword new password
     * @param login login of the account
     * @return boolean
     * @throws ServiceException
     */
    boolean updatePassword(String newPassword, String login) throws ServiceException ;
    /**
     * Method create  new account.
     * @param login
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param birthday
     * @param curr
     * @return boolean
     * @throws ServiceException
     */
    void createNewAccount(String login, String password, String email, String firstName, String lastName, Date birthday, String curr, String phone, String documentNumb ,String documentType)  throws ServiceException ;
}

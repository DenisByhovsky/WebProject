package com.epam.totalizator.service.impl;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.factory.DAOFactory;
import com.epam.totalizator.dao.interfaces.AccountDAO;
import com.epam.totalizator.dao.mysql.MySQLBetDAO;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountInvoice;
import com.epam.totalizator.service.AccountService;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.utill.Encryptor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;

import static com.epam.totalizator.entity.AccountRole.CLIENT;
import static org.apache.logging.log4j.Level.INFO;

/**
 * Account service class.
 */
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class.getName());



    private AccountDAO accountDAO;


    public  AccountServiceImpl() {

    accountDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getAccountDAO(); }

    public boolean checkPasswords(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public ArrayList<Account> fetchAllAccounts()throws ServiceException {
        try {
            return accountDAO.selectAll();
        }catch (DAOException e){
            throw new ServiceException("Error in method fetch all account"+e);
        }
    }

    public Account loginAccount(String login, String password) throws ServiceException {
        Account account;
        try {
            password =  Encryptor.sha1Encrypt(password);
            account = accountDAO.loginAccount(login, password);
            if (account != null && account.getRole() == CLIENT) {
                account.setBets(new MySQLBetDAO(true).selectPlayingBetsOfAccount(account));
                LOGGER.log(INFO,"Authenticate success");
            }
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR,"Authenticate failed");
            throw new ServiceException("Error in authenticate account" +e);
        }
        return account;
    }



    public boolean checkAccountIsExist(String login) throws ServiceException {
        try {
            Account account = accountDAO.selectByKey(login);
            LOGGER.log(Level.INFO, "Check if exist");
            return account!=null;
        } catch (DAOException e) {
          throw  new ServiceException("Error in check account exist");
        }
    }

    public boolean updatePassword(String newPassword, String login) throws ServiceException {
        try{
            LOGGER.log(Level.INFO, "Pass updated");
            return accountDAO.updatePassword(newPassword, login);
        }catch (DAOException e){
            throw new ServiceException("Error in update password");
        }
    }

    public void createNewAccount(String login, String password, String email, String firstName, String lastName, Date birthday, String currency, String phone, String documentNumb ,String documentType){
        try {
            Account account = new Account();
            AccountInvoice accountInvoice = new AccountInvoice();
            accountInvoice.setCurrency(currency);
            account.setLogin(login);
            account.setPassword(password);
            account.setEmail(email);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setBirthday(birthday);
            account.setRole(CLIENT);
            account.setPhone(phone);
            account.setDocumentNumb(documentNumb);
            account.setDocumentType(documentType);
            account.setInvoice(accountInvoice);
            accountDAO.insertAccount(account);
            LOGGER.log(Level.ERROR,"Successfully creating new acc");
      } catch (DAOException e) {
            LOGGER.log(Level.ERROR,"Fail in registration", e);
     }
    }
}
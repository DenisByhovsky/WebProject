package com.epam.totalizator.dao.interfaces;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountInvoice;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.entity.Transaction;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * The interface (@code InvoiceDAO) manipulate the invoices' in the (@code invoices) table.
 * @author Denis Byhovsky
 */
public interface InvoiceDAO {
    /**
     * Method select all information about drawback by (@code account).
     * @param account
     * @return ArrayList of Transactions
     * @throws DAOException
     */
    ArrayList<Transaction> selectAccountDrawback(Account account)throws DAOException;
    /**
     * This method extract all information about transactions.
     * @return ArrayList.
     * @throws SQLException
     */
    ArrayList<Transaction> recoverTransactions(ResultSet set) throws SQLException;

    List<AccountInvoice> selectAll() throws DAOException;

    AccountInvoice selectByKey(Integer key) throws DAOException;
    /**
     * This method get information about invoices that admin has.
     * @return ArrayList of invoices.
     * @throws DAOException
     */
    ArrayList<AccountInvoice> fetchAdminInvoices() throws DAOException;
    /**
     * Method select the information about all deposits by (@code account).
     * @param account
     * @return ArrayList of Transactions
     * @throws DAOException
     */
    ArrayList<Transaction> selectAccountDeposits(Account account) throws DAOException;
    /**
     * This method update invoices of account with winning bets.
     * @param wonBets list of winning bets
     * @throws DAOException
     */
    void updateWonInvoices(ArrayList<Bet> wonBets) throws DAOException;
    /**
     * Method select information about all transactions by (@code account).
     * @param account
     * @return ArrayList of Transactions
     * @throws DAOException
     */
    ArrayList<Transaction> selectAllTransactions(Account account) throws DAOException;
    /**
     * Method make deposit operation and update invoice.
     * @param account
     * @param type type of transaction.
     * @param amount
     * @return boolean
     * @throws DAOException
     */
    boolean makeDeposit(Account account, String type, BigDecimal amount) throws DAOException;
    /**
     * Method make drawback operation and update invoice.
     * @param account
     * @param type type of transaction.
     * @param amount
     * @return boolean
     * @throws DAOException
     */
    boolean drawback(Account account, String type, BigDecimal amount) throws DAOException;


}

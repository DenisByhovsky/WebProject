package com.epam.totalizator.service.impl;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.factory.DAOFactory;
import com.epam.totalizator.dao.interfaces.InvoiceDAO;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountInvoice;
import com.epam.totalizator.entity.Transaction;
import com.epam.totalizator.service.InvoiceService;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.utill.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Event service class.
 */
public class InvoiceServiceImpl implements InvoiceService{

    private static final Logger LOGGER = LogManager.getLogger(InvoiceServiceImpl.class.getName());
    private InvoiceDAO invoiceDAO;

    public InvoiceServiceImpl() {
            invoiceDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getInvoiceDAO();
    }

    public Transaction makeDeposit(Account account, String type, BigDecimal amount) throws ServiceException{
        Transaction transaction;
        try {
            BigDecimal currentBalance = account.getInvoice().getBallance();
            BigDecimal newBalance = currentBalance.add(amount);
            boolean flag;
            flag = invoiceDAO.makeDeposit(account, type, amount);
            if(flag){
                account.getInvoice().setBallance(newBalance);
                transaction = new Transaction();
                transaction.setAmount(amount);
                transaction.setDepositType(type);
            }else {
                throw new ServiceException("Make Deposit");
            }
        }catch (DAOException e){
            throw new ServiceException("Error in make deposit"+e);
        }
        return transaction;
    }

    public ArrayList<AccountInvoice> fetchAdminInvoices() throws ServiceException {
        try {
            return invoiceDAO.fetchAdminInvoices();
        }catch (DAOException e){
            throw new ServiceException("Error in fetch admin invoices"+e);
        }
    }

    public ArrayList<Transaction> fetchAccountDeposits(Account account) throws ServiceException {
            try {
                return invoiceDAO.selectAccountDeposits(account);
            }catch (DAOException e){
                throw new ServiceException("Error in fetch account deposits"+e);
            }
        }

        public ArrayList<Transaction> fetchAccountDrawback(Account account) throws ServiceException {
            try {
                return invoiceDAO.selectAccountDrawback(account);
            }catch (DAOException e){
                throw new ServiceException("Error in fetch account with draw"+e);
            }
        }

        public ArrayList<Transaction> fetchAllTransactions(Account account) throws ServiceException{
            try {
                return invoiceDAO.selectAllTransactions(account);
            }catch (DAOException e){
                throw new ServiceException("Error in fetch all transactions"+e);
            }
        }

        public Transaction drawback(Account account, String type, BigDecimal amount) throws ServiceException {
            Transaction transaction;
            try {
                BigDecimal currentBalance = account.getInvoice().getBallance();
                BigDecimal newBalance = currentBalance.subtract(amount);
                boolean flag;
                if (Validator.checkCash(amount, account.getInvoice())) {
                    flag =invoiceDAO.drawback(account, type, amount);
                } else {
                    return null;
                }
                if (flag) {
                    account.getInvoice().setBallance(newBalance);
                    transaction = new Transaction();
                    transaction.setAmount(amount);
                    transaction.setDepositType(type);
                } else {
                    throw new ServiceException("Error with withdraw.");
                }
            } catch (DAOException e) {
                throw new ServiceException("Withdraw error"+e);
            }
            return transaction;
        }


}

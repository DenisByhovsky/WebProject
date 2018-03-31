package com.epam.totalizator.service;

import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountInvoice;
import com.epam.totalizator.entity.Transaction;
import com.epam.totalizator.exception.ServiceException;
import java.math.BigDecimal;
import java.util.ArrayList;
/**
 * Invoice service interface.
 */
public interface  InvoiceService {
 /**
  * Method make deposit event.
  * @param account
  * @param amount
  * @param type
  * @return Transaction
  * @throws ServiceException
  */
    Transaction makeDeposit(Account account, String type, BigDecimal amount) throws ServiceException ;
 /**
  * Method get all deposit events of account invoice.
  * @param account
  * @return ArrayList
  * @throws ServiceException
  */
    ArrayList<Transaction> fetchAccountDeposits(Account account) throws ServiceException ;
 /**
  * Method get all drawback events of account.
  * @param account
  * @return ArrayList
  * @throws ServiceException
  */
    ArrayList<Transaction> fetchAccountDrawback(Account account) throws ServiceException ;
 /**
  * Method fetch all transactions.
  * @param account
  * @return ArrayList
  * @throws ServiceException
  */
    ArrayList<Transaction> fetchAllTransactions(Account account) throws ServiceException;
 /**
  * Method make drawback event.
  * @param account
  * @param type
  * @param amount
  * @return Transaction
  * @throws ServiceException
  */
    Transaction drawback(Account account, String type, BigDecimal amount) throws ServiceException;
 /**Methof get all admin invoices
  * @return ArrayList
  * @throws ServiceException
  */
    ArrayList<AccountInvoice> fetchAdminInvoices() throws  ServiceException;
}

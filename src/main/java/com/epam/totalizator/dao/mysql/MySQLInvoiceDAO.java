package com.epam.totalizator.dao.mysql;

import com.epam.totalizator.config.SQLFieldConstant;
import com.epam.totalizator.dao.AbstractDAO;
import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.interfaces.InvoiceDAO;
import com.epam.totalizator.entity.*;
import com.epam.totalizator.pool.ConnectionPool;
import com.epam.totalizator.pool.ProxyConnection;
import com.epam.totalizator.exception.PoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * (@code MySQLInvoiceDAO) provides the methods of
 * manipulating the accounts data in the (@code invoices)
 * table.
 */
public class MySQLInvoiceDAO  extends AbstractDAO<Integer,AccountInvoice> implements InvoiceDAO {

    private static final Logger LOGGER = LogManager.getLogger(MySQLInvoiceDAO.class.getName());

    private final String SELECT_ALL_INVOICE = "SELECT * FROM invoices";

    private final String SELECT_DEPOSITS = "SELECT t.date, t.type, t.amount, t.id, t.invoices_id, t.tran_type FROM totalizator.transactions AS t WHERE t.invoices_id = ? AND t.tran_type = ?";

    private final String SELECT_ALL="SELECT t.date, t.type, t.amount, t.id, t.invoices_id, t.tran_type FROM transactions AS t WHERE t.invoices_id = ?";

    private final String REDUCE_INVOICE="UPDATE invoices set ballance = ballance - ? where accounts_login = ?";

    private final String INSERT_TRANSACTION ="INSERT INTO totalizator.transactions (date, type, amount, invoices_id, tran_type)   VALUES (NOW(), ?, ?, ?, ?)";

    private final String REFILL_INVOICES ="UPDATE invoices set ballance = ballance + ? where accounts_login = ?";

    private  final String SELECT_INVOICES = "SELECT id, inv_currency, ballance, accounts_login from invoices where accounts_login = 'Vulkan2'";
    private final String UPDATE_WON_INVOICES = "UPDATE invoices SET ballance = ballance + ? where accounts_login = ?";

    private final String UPDATE_ADMIN_INVOICES ="UPDATE invoices SET ballance = ballance - ? where accounts_login = 'Vulkan2' AND inv_currency = ?";;

    public MySQLInvoiceDAO(boolean transactionFlag) {
        super(transactionFlag);
    }

    @Override
    public List<AccountInvoice> selectAll() throws DAOException {
        ArrayList<AccountInvoice> invoices = new ArrayList<>();
        try(ProxyConnection connection= ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_INVOICE)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                AccountInvoice accountInvoice = new AccountInvoice();
                accountInvoice.setId(Integer.parseInt(resultSet.getString(SQLFieldConstant.Invoice.INVOICE_ID)));
                accountInvoice.setCurrency(resultSet.getString(SQLFieldConstant.Invoice.INVOICE_CURRENCY));
                accountInvoice.setBallance(resultSet.getBigDecimal(SQLFieldConstant.Invoice.BALLANCE));
                invoices.add( accountInvoice);
                for(AccountInvoice ze:invoices) {
                        LOGGER.log(Level.INFO, "Event extracted:", ze);
                }
            }
            return invoices;
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in select all invoices" +e);
        }
    }

    @Override
    public ArrayList<Transaction> selectAccountDeposits(Account account) throws DAOException{
        ArrayList<Transaction> result;
        int id = account.getInvoice().getId();
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_DEPOSITS)) {
            statement.setInt(1, id);
            statement.setString(2, Transaction.TransactionType.DEPOSIT.getValue());
            ResultSet set = statement.executeQuery();
            result = recoverTransactions(set);
            LOGGER.log(Level.INFO, "Acc deposit success");
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in select accounts deposit" +e);
        }
        return result;
    }

    @Override
    public ArrayList<Transaction> selectAccountDrawback(Account account)throws DAOException {
        ArrayList<Transaction> result;
        int id = account.getInvoice().getId();
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_DEPOSITS)) {
            statement.setInt(1, id);
            statement.setString(2, Transaction.TransactionType.DRAWBACK.getValue());
            ResultSet set = statement.executeQuery();
            result = recoverTransactions(set);
            LOGGER.log(Level.INFO, "Select account drawback", result);
        }catch (SQLException | PoolException e){
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public ArrayList<Transaction> selectAllTransactions(Account account) throws DAOException {
        ArrayList<Transaction> result;
        int id = account.getInvoice().getId();
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            result = recoverTransactions(set);
            LOGGER.log(Level.INFO, "Select all trans ");
        }catch (SQLException | PoolException e){
            throw new DAOException("Error select all transactions"+e);
        }
        return result;
    }

    @Override
    public ArrayList<Transaction> recoverTransactions(ResultSet set) throws SQLException{
        ArrayList<Transaction> result = new ArrayList<>();
        Transaction transaction;
        while (set.next()){
            transaction = new Transaction();
            transaction.setDate(set.getDate(SQLFieldConstant.Transaction.DATE));
            transaction.setTime(set.getTime(SQLFieldConstant.Transaction.DATE));
            transaction.setDepositType(set.getString(SQLFieldConstant.Transaction.TYPE));
            transaction.setAmount(set.getBigDecimal(SQLFieldConstant.Transaction.AMOUNT));
            transaction.setTransactionType(set.getString(SQLFieldConstant.Transaction.TRANSACTION_TYPE));
            result.add(0,transaction);
            for(Transaction tran: result){
                LOGGER.log(Level.INFO, "Event extracted:", tran);
            }
        }
        return result;
    }

    //TODO
    @Override
    public AccountInvoice selectByKey(Integer key) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<AccountInvoice> fetchAdminInvoices()throws DAOException {
        ArrayList<AccountInvoice> invoices = new ArrayList<>();
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_INVOICES);
        ) {
            ResultSet set = statement.executeQuery();
            while (set.next()){
                AccountInvoice invoice = new AccountInvoice();
                invoice .setCurrency(set.getString(SQLFieldConstant.Invoice.INVOICE_CURRENCY));
                invoice .setBallance(set.getBigDecimal(SQLFieldConstant.Invoice.BALLANCE));
                invoices.add(invoice);
            }
        }
        catch (SQLException | PoolException e){
            throw new DAOException("Error in fetch admin invoices"+e);
        }
        for(AccountInvoice ev: invoices){
            LOGGER.log(Level.INFO,ev);
        }
        return invoices;
    }

    @Override
    public void updateWonInvoices(ArrayList<Bet> wonBets) throws DAOException{
        boolean flag = false;
        ProxyConnection connection  = null;
        PreparedStatement statement = null;
        PreparedStatement updStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_WON_INVOICES);
            updStatement = connection.prepareStatement(UPDATE_ADMIN_INVOICES);
            connection.setAutoCommit(false);
            for (Bet b: wonBets) {
                statement.setBigDecimal(1, b.getExpectedWin());
                statement.setString(2, b.getAccountLogin());
                statement.executeUpdate();
                updStatement.setBigDecimal(1, b.getExpectedWin());
                updStatement.setString(2, b.getCurrency());
                updStatement.executeUpdate();
            }
            connection.commit();
            flag = true;
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in update won bets"+e);
        }
        finally {
            try{
                if (!flag){
                    connection.rollback();
                }
                connection.setAutoCommit(true);
            }catch (SQLException e){
                    LOGGER.log(Level.ERROR, "Connection error"+e);
            }
            closeStatement(statement);
            closeStatement(updStatement);
            closeConnection(connection);
        }
    }

    @Override
    public boolean makeDeposit(Account account, String type, BigDecimal amount) throws DAOException{
        boolean flag = false;
        ProxyConnection connection = null;
        PreparedStatement statementInvoice = null;
        PreparedStatement statementDeposit = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statementInvoice = connection.prepareStatement(REFILL_INVOICES);
            statementInvoice.setString(1, amount.toString());
            statementInvoice.setString(2, account.getLogin());
            statementDeposit = connection.prepareStatement(INSERT_TRANSACTION);
            statementDeposit.setString(1, type);
            statementDeposit.setString(2, amount.toString());
            statementDeposit.setString(3, String.valueOf(account.getInvoice().getId()));
            statementDeposit.setString(4, Transaction.TransactionType.DEPOSIT.getValue());
            statementInvoice.executeUpdate();
            statementDeposit.executeUpdate();
            connection.commit();
            flag = true;
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in deposit "+e);
        }
        finally {
            try{
                if (!flag){
                    connection.rollback();
                }
                connection.setAutoCommit(true);
            }catch (SQLException e){
                LOGGER.log(Level.ERROR, "Error in connection"+e);
            }
            LOGGER.log(Level.INFO, "Make deposit");
            closeStatement(statementDeposit);
            closeStatement(statementInvoice);
            closeConnection(connection);
        }
        return flag;
    }

    @Override
    public boolean drawback(Account account, String type, BigDecimal amount) throws DAOException{
        boolean flag = false;
        ProxyConnection connection = null;
        PreparedStatement statementInvoice = null;
        PreparedStatement statementWithdraw = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statementInvoice = connection.prepareStatement(REDUCE_INVOICE);
            statementWithdraw = connection.prepareStatement(INSERT_TRANSACTION);
            connection.setAutoCommit(false);
            statementInvoice.setString(1, amount.toString());
            statementInvoice.setString(2, account.getLogin());
            statementWithdraw.setString(1, type);
            statementWithdraw.setString(2, amount.toString());
            statementWithdraw.setString(3, String.valueOf(account.getInvoice().getId()));
            statementWithdraw.setString(4, Transaction.TransactionType.DRAWBACK.getValue());
            statementInvoice.executeUpdate();
            statementWithdraw.executeUpdate();
            connection.commit();
            flag = true;
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in drawback "+e);
        }finally {
            try {
                if (!flag){
                    connection.rollback();
                }
                connection.setAutoCommit(true);
            }catch (SQLException e){
                LOGGER.log(Level.ERROR, "Problems with connection"+e);
            }
            closeStatement(statementInvoice);
            closeStatement(statementWithdraw);
            closeConnection(connection);
            LOGGER.log(Level.ERROR, "Deposit done");
        }
        return flag;
    }
}

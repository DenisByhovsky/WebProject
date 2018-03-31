package com.epam.totalizator.dao.mysql;

import com.epam.totalizator.config.SQLFieldConstant;
import com.epam.totalizator.dao.AbstractDAO;
import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.interfaces.AccountDAO;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountInvoice;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.pool.ConnectionPool;
import com.epam.totalizator.pool.ProxyConnection;
import com.epam.totalizator.exception.PoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * (@code MySQLAccountDAO) provides the methods of
 * manipulating the accounts data in the (@code accounts)
 * table.
 */
public class MySQLAccountDAO  extends AbstractDAO<String,Account> implements AccountDAO {

    private static final Logger LOGGER = LogManager.getLogger(MySQLAccountDAO.class.getName());


    public static final String SELECT_ALL_ACCOUNTS = "SELECT login,password, first_name,last_name,email, role, birthday, create_date ,doc_numb, phone, doc_type FROM accounts";
    private static final String SELECT_ACCOUNT = "SELECT a.login, a.password, a.email, a.birthday, a.create_date, a.role, a.first_name, a.last_name, a.phone, a.doc_numb, a.doc_type, i.id, i.inv_currency, i.ballance FROM accounts AS a JOIN invoices AS i ON a.login = i.accounts_login WHERE a.login = ? AND a.password = ?";

    private static final String INSERT_ACCOUNT = "INSERT INTO accounts (login, password, first_name, last_name, email, role, birthday,create_date,  phone, doc_type, doc_numb) VALUES(?,?,?,?,?,?,?, NOW(),?,?,?)";
    private static final String INSERT_INVOICE = "INSERT INTO invoices (inv_currency, accounts_login) VALUES (?,?)";
    private static final String CHECK_IF_EXIST = "SELECT login FROM accounts WHERE login = ?";
    private static final String UPDATE_PASSWORD = "UPDATE accounts SET password = ? WHERE login = ?";

    public MySQLAccountDAO(boolean transactionFlag) {
        super(transactionFlag);
    }

    @Override
    public ArrayList<Account> selectAll() throws DAOException {
        ArrayList<Account> list = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACCOUNTS)) {
            ResultSet resultSet = statement.executeQuery();
            Account account;
            AccountRole accountRole;
            while (resultSet.next()) {
                if (AccountRole.valueOf(resultSet.getString(SQLFieldConstant.Account.ROLE)) == AccountRole.CLIENT) {
                    account = new Account();
                    account.setLogin(resultSet.getString(SQLFieldConstant.Account.LOGIN));
                    account.setPassword(resultSet.getString(SQLFieldConstant.Account.PASSWORD));
                    account.setFirstName(resultSet.getString(SQLFieldConstant.Account.FIRST_NAME));
                    account.setLastName(resultSet.getString(SQLFieldConstant.Account.LAST_NAME));
                    account.setEmail(resultSet.getString(SQLFieldConstant.Account.EMAIL));
                    account.setBirthday(resultSet.getDate(SQLFieldConstant.Account.BIRTHDAY));
                    account.setCreateDate(resultSet.getDate(SQLFieldConstant.Account.CREATE_DATE));
                    account.setDocumentNumb(resultSet.getString(SQLFieldConstant.Account.DOC_NUMB));
                    account.setDocumentNumb(resultSet.getString(SQLFieldConstant.Account.DOC_TYPE));
                    account.setDocumentNumb(resultSet.getString(SQLFieldConstant.Account.PHONE));
                    list.add(account);
                    for (Account acc : list) {
                        LOGGER.log(Level.INFO, acc.toString());
                    }
                }
            }
            return list;
        } catch (SQLException | PoolException e) {
            throw new DAOException("Not selected all",e);
        }
    }

    @Override
    public Account selectByKey(String login) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_IF_EXIST)) {
            //
            statement.setString(1,login);
            ResultSet set = statement.executeQuery();
            if (set.next()) {

                return new Account();
            } else {
                return null;
            }
        } catch (SQLException | PoolException e) {
            throw new DAOException("Err in selected by key"+e);
        }

    }

    @Override
    public Account loginAccount(String login, String password) throws DAOException {
        Account account = null;
        AccountInvoice invoice;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNT)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                account = new Account();
                account.setLogin(set.getString(SQLFieldConstant.Account.LOGIN));
                account.setPassword(set.getString(SQLFieldConstant.Account.PASSWORD));
                account.setEmail(set.getString(SQLFieldConstant.Account.EMAIL));
                account.setFirstName(set.getString(SQLFieldConstant.Account.FIRST_NAME));
                account.setLastName(set.getString(SQLFieldConstant.Account.LAST_NAME));
                account.setBirthday(set.getDate(SQLFieldConstant.Account.BIRTHDAY));
                account.setCreatingDate(set.getDate(SQLFieldConstant.Account.CREATE_DATE));
                account.setDocumentNumb(set.getString(SQLFieldConstant.Account.DOC_NUMB));
                account.setDocumentNumb(set.getString(SQLFieldConstant.Account.DOC_TYPE));
                account.setDocumentNumb(set.getString(SQLFieldConstant.Account.PHONE));
                account.setRole(AccountRole.valueOf(set.getString(SQLFieldConstant.Account.ROLE)));
                if (account.getRole().equals(AccountRole.CLIENT)) {
                    invoice = new AccountInvoice();
                    invoice.setCurrency(set.getString(SQLFieldConstant.Invoice.INVOICE_CURRENCY));
                    invoice.setId(set.getInt(SQLFieldConstant.Invoice.INVOICE_ID));
                    invoice.setBallance(set.getBigDecimal(SQLFieldConstant.Invoice.BALLANCE));
                    account.setInvoice(invoice);
                }
            }
        } catch (SQLException | PoolException e) {
            throw new DAOException("Authenticate account exception: " + e);
        }
        return account;
    }

    @Override
    public void insertAccount(Account account) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ACCOUNT);
             PreparedStatement invoicesStatement = connection.prepareStatement(INSERT_INVOICE)) {
            statement.setString(1, account.getLogin());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getFirstName());
            statement.setString(4, account.getLastName());
            statement.setString(5, account.getEmail());
            statement.setString(6, account.getRole().toString());
            statement.setDate(7, account.getBirthday());
            statement.setString(8, account.getDocumentNumb());
            statement.setString(9, account.getPhone());
            statement.setString(10, account.getDocumentType());

            invoicesStatement.setString(1, account.getInvoice().getCurrency());
            invoicesStatement.setString(2, account.getLogin());
            statement.executeUpdate();

            invoicesStatement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DAOException("Creating new account Exception: " + e);
        }
    }

    @Override
    public boolean updatePassword(String newPassword, String login) throws DAOException {
        boolean flag;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setString(2, login);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException | PoolException e) {
            throw new DAOException("Updating password Exception" + e);
        }
        return flag;
    }
}
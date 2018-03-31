package com.epam.totalizator.dao.oracle;

import com.epam.totalizator.dao.AbstractDAO;
import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.interfaces.AccountDAO;
import com.epam.totalizator.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

/**
 * (@code OracleAccountDAO ) provides the methods of
 * manipulating the accounts data
 */
public class OracleAccountDAO  extends AbstractDAO<String,Account> implements AccountDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    //TODO
    public OracleAccountDAO(boolean isForTransaction) {
        super(isForTransaction);
    }

    @Override
    public ArrayList<Account> selectAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Account selectByKey(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Account loginAccount(String login, String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertAccount(Account account) throws DAOException {
    }

    @Override
    public boolean updatePassword(String newPassword, String login) {
        return false;
    }
}

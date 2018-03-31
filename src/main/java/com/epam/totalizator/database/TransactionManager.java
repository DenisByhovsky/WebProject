package com.epam.totalizator.database;

import com.epam.totalizator.dao.AbstractDAO;
import com.epam.totalizator.pool.ConnectionPool;
import com.epam.totalizator.pool.ProxyConnection;
import com.epam.totalizator.exception.PoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Transaction MANAGER.
 */
public class TransactionManager {

    private static final Logger LOGGER = LogManager.getLogger(TransactionManager.class.getName());

    private List<AbstractDAO> daos;
    private ProxyConnection connection;
    public TransactionManager(AbstractDAO dao, AbstractDAO... daos) throws PoolException {
        this.daos = new ArrayList<>();
        connection = ConnectionPool.getInstance().getConnection();
        this.daos.add(dao);
        Collections.addAll(this.daos, daos);
    }
    /**
     * Begin transaction method
     */
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            daos.forEach(dao -> dao.setConnection(connection));
            LOGGER.log(Level.INFO, "Transaction has been began.");
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Can't begin transaction. " + e.getMessage(), e);
        }
    }
    /**
     * End transaction method
     */
    public void endTransaction() throws PoolException {
        try {
            connection.setAutoCommit(true);

            ConnectionPool.getInstance().returnConnection(connection);
            LOGGER.log(Level.INFO, "Transaction has been ended.");
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Can't finish transaction", e);
        }
    }
    /**
     * Commit transaction method
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Can't commit transaction", e);
        }
    }
    /**
     * Rollback transaction method
     */
    public void rollback() {
        try {
            connection.rollback();
            LOGGER.log(Level.INFO, "Transaction has been rolled back.");
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Can't rollback transaction", e);
        }
    }
}

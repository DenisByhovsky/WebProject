package com.epam.totalizator.dao;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.pool.ConnectionPool;
import com.epam.totalizator.pool.ProxyConnection;
import com.epam.totalizator.exception.PoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Abstract class that describes structure of classes that included in DAO layer
 * @author  Denis Byhovsky
 * @param <K> key
 * @param <T> type of entity
 */
public abstract class AbstractDAO<K,T> {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Used connection.
     */
    protected ProxyConnection connection;

    /**
     * Method that takes all entities found in table of database
     * @return list
     * @throws DAOException
     */
    public abstract List<T> selectAll() throws DAOException;

    /**
     * Method that finds entity by unique field
     * @param key is any unique field
     * @return entity founded
     * @throws DAOException
     * @throws SQLException
     * @throws PoolException
     */
    public abstract T selectByKey(K key) throws DAOException, SQLException, PoolException;

    /**
     * Set connection
     */
    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

    /**
     * Default constructor connection.
     */
    protected AbstractDAO(boolean transactionFlag)  {
        if(!transactionFlag) {
            try {
                this.connection = ConnectionPool.getInstance().getConnection();
            } catch (PoolException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Close  Statement method.
     * @param st
     */
    public void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
                LOGGER.log(Level.INFO, "Statement closed" );
            } catch (SQLException e) {
            }
        }
    }

    /**
     * Close Prepared Statement method.
     * @param ps
     */
    public void closePreparedStatement(PreparedStatement ps) {
            if (ps != null) {
                try {
                    ps.close();
                    LOGGER.log(Level.INFO, "Prepared Statement closed" );
                } catch (SQLException e) {
                }
            }
    }

    /**
     * Return back connection into pool.
     * @param connection
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.log(Level.INFO, "Connection closed" );
            } catch (SQLException e) {
            }
        }
    }
}
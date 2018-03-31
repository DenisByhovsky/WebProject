package com.epam.totalizator.pool;

import com.epam.totalizator.exception.PoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Connects users with database
 */
public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class.getName());

    private final int WAITING_TIME = 1000;
    /**
     * Instance of ConnectionPool.
     */
    private static ConnectionPool instance;
    /**
     * Reentrant lock.
     */
    private static Lock lock = new ReentrantLock();
    private static AtomicBoolean active = new AtomicBoolean(false);
    private static AtomicBoolean closed = new AtomicBoolean(false);
    private final String URL;
    private final Properties properties;
    /**
     * Secure connections queue.
     */
    private BlockingQueue<ProxyConnection> connections;
    private int size;

    /**
     * Default constructor.
     */
    private ConnectionPool() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pr");
        URL = resourceBundle.getString("pr.url");
        size = Integer.parseInt(resourceBundle.getString("pool.size"));
        properties = new Properties();
        properties.put("user", resourceBundle.getString("pr.user"));
        properties.put("password", resourceBundle.getString("pr.password"));
        properties.put("encoding", resourceBundle.getString("pr.encoding"));
        properties.put("autoReconnect", resourceBundle.getString("pr.autoReconnect"));
        properties.put("useSSL", resourceBundle.getString("pr.useSSL"));
        properties.put("useUnicode", resourceBundle.getString("pr.useUnicode"));
        properties.put("useJDBCCompliantTimezoneShift", resourceBundle.getString("pr.useJDBCCompliantTimezoneShift"));
        properties.put("useLegacyDatetimeCode", resourceBundle.getString("pr.useLegacyDatetimeCode"));
        properties.put("serverTimezone", resourceBundle.getString("pr.serverTimezone"));

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            LOGGER.log(Level.INFO, "Driver registered");
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error in registering driver");
        }

        connections = new ArrayBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            addConnection();
        }
        if (connections.isEmpty()) {
            throw new RuntimeException("Error in pool creation");
        }

    }

    /**
     * Get instance of ConnectionPool.
     *
     * @return instance
     */
    public static ConnectionPool getInstance() {
        if (!active.get()) {
            lock.lock();
            if (instance == null) {
                try {
                    instance = new ConnectionPool();
                    active.getAndSet(true);
                } finally {
                    lock.unlock();
                }
            }
        }
        return instance;
    }

    private void addConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, properties);
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            connections.add(proxyConnection);
            LOGGER.log(Level.INFO, "Add connection");
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Connection added fail");
        }
    }

    /**
     * Fetch available connection from connection list.
     *
     * @return connection
     */
    public ProxyConnection getConnection() throws PoolException {
        if (!closed.get()) {
            try {
                ProxyConnection connection = connections.poll(WAITING_TIME, TimeUnit.SECONDS);
                if (connection != null) {
                    return connection;
                } else {
                    throw new PoolException("Timeout!");
                }
            } catch (InterruptedException e) {
                throw new PoolException("Connection interrupted");
            }
        }
        throw new PoolException("Timeout");
    }

    /**
     * Release used connection.
     *
     * @param connection
     * @see ProxyConnection
     */
    public void returnConnection(ProxyConnection connection) throws PoolException {
        if (connection != null) {
            connections.add(connection);//&? poll
        } else {
            throw new PoolException("Fail connection");
        }
    }

    /**
     * Destroy connection pool.
     */
    public void destroyPool() {
        try {
            while (!connections.isEmpty()) {
                getConnection().closeConnection();
                DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
            }
            instance = null;
            connections = null;
            closed.getAndSet(true);
            LOGGER.log(Level.ERROR, "Pool closed");
        } catch (PoolException | SQLException e) {
            LOGGER.log(Level.ERROR, "Pool wasnt destroyed");

        }
    }

    /**
     * Get pool size.
     */
    public int getPoolSize() {
        return size;
    }

    /**
     * Get connection count.
     */
    public int getConnectionCount() {
        return connections.size();
    }

}
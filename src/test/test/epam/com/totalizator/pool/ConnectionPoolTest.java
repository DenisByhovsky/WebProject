package test.epam.com.totalizator.pool;

import com.epam.totalizator.pool.ConnectionPool;
import com.epam.totalizator.pool.ProxyConnection;
import com.epam.totalizator.exception.PoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;


import static org.testng.Assert.assertEquals;

public class ConnectionPoolTest {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolTest.class.getName());

    private static ConnectionPool pool;
    @BeforeClass
    public static void initPoolTest()
    {  pool = ConnectionPool.getInstance();
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Assert.assertNotNull(pool);
            Assert.assertNotSame(0, ConnectionPool.getInstance().getPoolSize());
        } catch (Exception e) {
            LOGGER.log(Level.ERROR,e);

        }
    }

    @Test
    public void testSingleton(){
        ConnectionPool anotherPool = ConnectionPool.getInstance();
        Assert.assertSame(pool, anotherPool);
    }

    @Test
    public void poolSizeTest() {
        int expectedSize = 16;
        assertEquals(expectedSize, ConnectionPool.getInstance().getPoolSize());
    }

    @Test
    public void testConnection(){
        try {
            Connection connection = pool.getConnection();
            Assert.assertNotNull(connection);
            connection.close();
        }catch (PoolException | SQLException e){
            Assert.fail();
        }
    }


    @Test
    public void returnConnectionToPoolTest() {
        try {
            int connectionsCount = ConnectionPool.getInstance().getConnectionCount();
            ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
            assertEquals(connectionsCount - 1, ConnectionPool.getInstance().getConnectionCount());
            ConnectionPool.getInstance().returnConnection(proxyConnection);
            assertEquals(connectionsCount, ConnectionPool.getInstance().getConnectionCount());
        } catch (Exception e) {
            LOGGER.log(Level.ERROR,e);
        }

    }
}
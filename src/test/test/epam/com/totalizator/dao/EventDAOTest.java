package test.epam.com.totalizator.dao;

import com.epam.totalizator.config.SQLFieldConstant;
import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.mysql.MySQLEventDAO;
import com.epam.totalizator.database.TransactionManager;
import com.epam.totalizator.entity.Event;
import com.epam.totalizator.exception.PoolException;
import com.epam.totalizator.pool.ConnectionPool;
import com.epam.totalizator.pool.ProxyConnection;
import com.mysql.jdbc.PreparedStatement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.ResultSet;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Test dao
 */

@RunWith(MockitoJUnitRunner.class)
public class EventDAOTest {

    //TODO
    @Spy
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Mock
    private ProxyConnection conn;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet resultSet;

    private Event event;

@Before
public void setUp() throws Exception {
    Assert.assertNotNull(pool);
    doReturn(conn).when(pool).getConnection();
    when(conn.prepareStatement(any(String.class))).thenReturn(stmt);
    event = new Event();
    event.setDescription("Mock");
    when(resultSet.first()).thenReturn(true);
    when(resultSet.getString(SQLFieldConstant.Event.DESCRIPTION)).thenReturn(event.getDescription());
    when(stmt.executeQuery()).thenReturn(resultSet);
}

    @Test(expected = NullPointerException.class)
    public void createNullSuitTest() throws DAOException {
        new MySQLEventDAO(false).selectByKey(null);
    }

    @Test
    public void doEventTest() throws DAOException, PoolException {
        MySQLEventDAO eventDAO = new MySQLEventDAO(true);
        TransactionManager transactionManager = new TransactionManager(eventDAO);
        transactionManager.beginTransaction();
        try {
            Event s = eventDAO.selectByKey(1);
            Assert.assertEquals(s.getDescription(), event.getDescription());
        } finally {
            transactionManager.rollback();
            transactionManager.endTransaction();
        }
    }
}

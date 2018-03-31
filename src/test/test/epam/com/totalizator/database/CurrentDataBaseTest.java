package test.epam.com.totalizator.database;

import com.epam.totalizator.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.sql.*;

/**
 * Class testing test database
 */
public class CurrentDataBaseTest {

    private static final Logger LOGGER = LogManager.getLogger(CurrentDataBaseTest.class.getName());

    private static final  String USER = "root";
    private static final  String PASSWORD = "root";
    private static final  String DATABASE_URL = "jdbc:mysql://localhost:3306/totalizator?autoReconnect=" +
            "true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private Connection connection;
    private static Statement statement;
    private static ResultSet rs;

  @BeforeClass
   public void setUp() {
     connection = null;
       try {
         Class.forName("com.mysql.jdbc.Driver");
         LOGGER.log(Level.INFO,"Connecting to Database...");
         connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
         if (connection != null) {
         LOGGER.log(Level.INFO,"Connected to the Database..."); }
         } catch (SQLException  | ClassNotFoundException e) {
          LOGGER.log(Level.ERROR,e);
     }
  }

 @Test
 public void getInvoiceFromDataBase() throws DAOException {
     try {
        String query = "select * from invoices";
        statement = connection.createStatement();
        rs = statement.executeQuery(query);

         while(rs.next()){
           int invoiceId= rs.getInt("id");
           String invoiceCurrency= rs.getString("inv_currency");
           String accountLogin=rs.getString("accounts_login");
           BigDecimal ballance= rs.getBigDecimal(3);
           LOGGER.log(Level.INFO,invoiceId+"\t"+invoiceCurrency+"\t"+accountLogin+"\t"+"\t"+ballance);
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

 @AfterClass
 public void tearDown() throws DAOException {
    if (connection != null) {
      try {
       LOGGER.log(Level.INFO,"Closing Database Connection...");
        statement.close();
          connection.close();
            } catch (SQLException e) {
            throw new DAOException(e);
                }
            }
      }
}

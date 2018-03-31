package com.epam.totalizator.dao.mysql;

import com.epam.totalizator.config.SQLFieldConstant;
import com.epam.totalizator.dao.AbstractDAO;
import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.interfaces.CoefficientDAO;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.entity.Coefficient;
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
import java.util.List;

/**
 * (@code MySQLCoefficientDAO) provides the methods of
 * manipulating the accounts data in the (@code coefficients)
 * table.
 */
public class MySQLCoefficientDAO extends AbstractDAO<Integer,Coefficient> implements CoefficientDAO {

    private static final Logger LOGGER = LogManager.getLogger(MySQLCoefficientDAO.class.getName());

    private static final String SELECT_ALL_COEFFICIENT= "SELECT * FROM coefficients ";

    private static final String INSERT_COEFFICIENTS = "INSERT INTO coefficients ( win_first, win_second, nobody,first_or_nobody,              second_or_nobody, first_or_second) VALUES (?,?,?,?,?,?)";

    private static final String SELECT_ID = "SELECT LAST_INSERT_ID() FROM coefficients";

    private  static final String ARRANGE_COEFFICIENTS="UPDATE events SET events.coefficients_id = ? WHERE events.id = ?";

    private static final String UPDATE_COEFFICIENTS= "UPDATE events AS e JOIN coefficients AS c ON e.coefficients_id = c.id SET c             .win_first=?, c.win_second=?, c.nobody=?, c.first_or_nobody = ?, c.second_or_nobody = ?, c.first_or_second = ? where e.id = ?";
    private static final String UPDATE_BETS_COEFFICIENTS="UPDATE bets SET bet_coef = ?, expected_win = ? WHERE bet_id = ?";

   public MySQLCoefficientDAO(boolean transactionFlag) {
        super(transactionFlag);
    }

    @Override
    public List<Coefficient> selectAll() throws DAOException {
        ArrayList<Coefficient> coefficients = new ArrayList<>();
        try(ProxyConnection connection =  ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_COEFFICIENT)){
            ResultSet resultSet = statement.executeQuery();
            Coefficient coefficient;
            while (resultSet.next()){
                coefficient = new Coefficient();
                coefficient.setFirstOrNobody(Double.valueOf(resultSet.getString(SQLFieldConstant.Coefficient.FIRST_OR_NOBODY)));
                coefficient.setNobody(Double.valueOf(resultSet.getString(SQLFieldConstant.Coefficient.NOBODY)));
                coefficient.setFirstOrSecond(Double.valueOf(resultSet.getString(SQLFieldConstant.Coefficient.FIRST_OR_SECOND)));
                coefficient.setSecondOrNobody(Double.valueOf(resultSet.getString(SQLFieldConstant.Coefficient.SECOND_OR_NOBODY)));
                coefficient.setWinSecond(Double.valueOf(resultSet.getString(SQLFieldConstant.Coefficient.WIN_SECOND)));
                coefficient.setWinFirst(Double.valueOf(resultSet.getString(SQLFieldConstant.Coefficient.WIN_FIRST)));
                coefficients.add(coefficient);
                for(Coefficient coef:coefficients) {
                    LOGGER.log(Level.INFO, "All coefficients",coef.toString());
                }
            }
            return coefficients;
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in select all coefficients");
        }
    }

    @Override
    public int insertCoefficients(String first, String nobody, String second, String firstOrNobody, String firsOrSecond, String secondOrNobody) throws DAOException {
        int insertID=-1;
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_COEFFICIENTS);
            PreparedStatement idStatement = connection.prepareStatement(SELECT_ID)) {
            statement.setString(1, first);
            statement.setString(2, second);
            statement.setString(3, nobody);
            statement.setString(4, firstOrNobody);
            statement.setString(5, firsOrSecond);
            statement.setString(6, secondOrNobody);
            statement.executeUpdate();
            ResultSet set = idStatement.executeQuery();
            if(set.next()){
                insertID = set.getInt(1);
            }
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in insert coefficients"+e);
        }
        LOGGER.log(Level.INFO, "insert ID: ");
        return insertID;
        }

    @Override
    public void updateCoefficients(int eventId, String first, String nobody, String second, String fon, String fos, String son) throws DAOException{
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_COEFFICIENTS)) {
            statement.setString(1, first);
            statement.setString(2, second);
            statement.setString(3, nobody);
            statement.setString(4, fon);
            statement.setString(5, son);
            statement.setString(6, fos);
            statement.setInt(7, eventId);
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Updated coef ");
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in update coefficients"+e);
        }
    }

    @Override
    public void arrangeCoefficients(int eventID, int coefficientID) throws DAOException{
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(ARRANGE_COEFFICIENTS)) {
            statement.setInt(1, coefficientID);
            statement.setInt(2, eventID);
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Arrange coef ");
        }catch (SQLException | PoolException e){
            throw new DAOException("Error in arerange coefficients"+e);
        }
    }

    @Override
    public void updateCoefficientForBets(ArrayList<Bet> bets) throws DAOException{
        try(ProxyConnection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BETS_COEFFICIENTS)) {
            for (Bet bet: bets) {
                statement.setBigDecimal(1, bet.getCoefficient());
                statement.setBigDecimal(2, bet.getExpectedWin());
                statement.setInt(3, bet.getId());
                statement.executeUpdate();
                LOGGER.log(Level.INFO, "Update coef for bets");
            }
        }catch (PoolException | SQLException e){
            throw new DAOException("Error in update coefficients for bet"+e);
        }
    }

    //TODO
    @Override
    public Coefficient selectByKey(Integer key) throws DAOException {
        throw new UnsupportedOperationException();
    }
}

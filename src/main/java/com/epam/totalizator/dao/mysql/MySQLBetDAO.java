package com.epam.totalizator.dao.mysql;

import com.epam.totalizator.config.SQLFieldConstant;
import com.epam.totalizator.dao.AbstractDAO;
import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.interfaces.BetDAO;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.entity.Event;
import com.epam.totalizator.pool.ConnectionPool;
import com.epam.totalizator.pool.ProxyConnection;
import com.epam.totalizator.exception.PoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (@code MySQLBetDAO) provides the methods of
 * manipulating the accounts data in the (@code bets)
 * table.
 */
public class MySQLBetDAO extends AbstractDAO<Integer, Bet> implements BetDAO {

    private static final Logger LOGGER = LogManager.getLogger(MySQLBetDAO.class.getName());

    private static final String SELECT_BET_BY_ID = "SELECT b.bet_id, b.accounts_login, b.events_id, b.amount_count, b.expected_win, b.result, b.bet_date, b.bet_coef, b.bet_type, b.bet_currency, e.id, e.event_type, e.kind_of_sport, e.description, e.first_competitor, e.second_competitor, e.first_score, e.second_score, e.start_date, e.coefficients_id, e.is_played FROM bets AS b JOIN events AS e ON b.events_id = e.id WHERE b.bet_id = ?";


    private static final String SELECT_ALL_BETS_BY_ID = "SELECT a.login, a.password, a.email, a.birthday, a.create_date, a.role, a.first_name, a.last_name, a.doc_numb, a.doc_type, a.phone, i.id, i.inv_currency, i.ballance FROM accounts AS a JOIN invoices as i  ON a.login = i.accounts_login WHERE a.login = ? AND a.password = ?";

    private static final String SELECT_ALL_BETS = "SELECT * FROM bets ";
    private static final String INSERT_BET = "INSERT INTO totalizator.bets (accounts_login, events_id, amount_count,expected_win,bet_date, bet_coef, bet_type, bet_currency) VALUES (?,?,?,?, NOW(),?,?,?)";

    private static final String ADD_TO_ADMIN = "UPDATE invoices SET invoices.ballance = invoices.ballance + ? WHERE accounts_login = 'Vulkan2' AND inv_currency = ?";

    private static final String SELECT_ID = "SELECT LAST_INSERT_ID() FROM bets";

    private static final String UPDATE_INVOICE = "UPDATE invoices SET ballance = ? WHERE accounts_login = ?";

    private static final String DELETE_BET = "DELETE FROM bets WHERE bet_id = ?";
    private static final String SUBSTRACT_FROM_ADMIN = "UPDATE invoices SET invoices.ballance = invoices.ballance - ? WHERE accounts_login = 'Vulkan2' AND inv_currency = ?";

    private static final String SELECT_EVENTS = "SELECT b.bet_id, b.accounts_login, b.events_id, b.amount_count, b.expected_win, b.result, b.bet_date, b.bet_coef, b.bet_type, b.bet_currency, e.id, e.event_type, e.kind_of_sport, e.description, e.first_competitor, e.second_competitor, e.first_score, e.second_score, e.start_date, e.coefficients_id, e.is_played FROM bets AS b JOIN events AS e ON b.events_id = e.id WHERE + b.accounts_login = ? AND b.result = ? ORDER BY b.bet_id";

    private static final String SELECT_ALL_OF_ACCOUNT = "SELECT b.bet_id, b.accounts_login, b.events_id, b.amount_count,b.expected_win, b.result, b.bet_date, b.bet_coef, b.bet_type, b.bet_currency, e.id, e.event_type,e.kind_of_sport, e.description, e.first_competitor, e.second_competitor, e.first_score, e.second_score, e.start_date, e.coefficients_id, e.is_played FROM bets AS b JOIN events AS e ON b.events_id = e.id WHERE b.accounts_login = ?";

    private static final String UPDATE_BETS_RESULTS = "UPDATE bets SET result = ? WHERE accounts_login = ? AND bet_date = ?";

    private static final String SELECT_BETS_OF_CURRENCY = "SELECT b.bet_id, b.accounts_login, b.events_id, b.amount_count, b.expected_win, b.result, b.bet_date, b.bet_coef, b.bet_type, b.bet_currency, e.id, e.event_type, e.kind_of_sport, e.description, e.first_competitor, e.second_competitor, e.first_score, e.second_score,e.start_date, e.coefficients_id, e.is_played FROM bets AS b JOIN events AS e ON b.events_id = e.id WHERE b.bet_currency = ? ORDER BY b.accounts_login";

    private static final String SELECT_ALL_BETS_EVENT = "SELECT bet_id, accounts_login, events_id, amount_count, expected_win," + " result, bet_date, bet_coef, bet_type, bet_currency FROM bets WHERE events_id = ? AND result = ?";

    public MySQLBetDAO(boolean transactionFlag) {
        super(transactionFlag);
    }

    @Override
    public List<Bet> selectAll() throws DAOException {
        ArrayList<Bet> bets = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BETS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bet bet = new Bet();
                bet.setId(Integer.parseInt(resultSet.getString(SQLFieldConstant.Bet.BET_ID)));
                bet.setBetDate(resultSet.getDate(SQLFieldConstant.Bet.BET_DATE));
                bet.setAmountCount(resultSet.getBigDecimal(SQLFieldConstant.Bet.AMOUNT_COUNT));
                bet.setExpectedWin(resultSet.getBigDecimal(SQLFieldConstant.Bet.EXPECTED_WIN));
                bet.setResult(Integer.valueOf(resultSet.getString(SQLFieldConstant.Bet.RESULT)));
                bet.setTypeBet(resultSet.getString(SQLFieldConstant.Bet.BET_TYPE));
                bet.setDateOfMatch(resultSet.getString(SQLFieldConstant.Bet.BET_DATE));
                bet.setCoefficient(resultSet.getBigDecimal(SQLFieldConstant.Bet.BET_COEFFICIENT));
                bet.setCurrency(resultSet.getString(SQLFieldConstant.Bet.CURRENCY));
                bet.setAccountLogin(resultSet.getString(SQLFieldConstant.Bet.ACCOUNTS_LOGIN));
                bets.add(bet);
                for (Bet ae : bets) {
                    LOGGER.log(Level.INFO, "All bets", ae.toString());
                }
            }
            return bets;
        } catch (SQLException | PoolException e) {
            throw new DAOException("Cant select all bets" + e);
        }
    }

    @Override
    public Bet selectByKey(Integer id) throws DAOException {
        Bet bet;
        try (
                ProxyConnection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BET_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet set = preparedStatement.executeQuery();
            bet = takeBets(set).get(0);
        } catch (PoolException | SQLException e) {
            throw new DAOException("Fail in select by key" + e);
        }
        return bet;
    }

    @Override
    public ArrayList<Bet> takeBets(ResultSet set) throws SQLException {
        ArrayList<Bet> bets = new ArrayList<>();
        Bet bet;
        Event event;
        while (set.next()) {
            bet = new Bet();
            event = new Event();
            try {
                event.setFirstCompetitor(set.getString(SQLFieldConstant.Event.FIRST_COMPETITOR));
                event.setSecondCompetitor(set.getString(SQLFieldConstant.Event.SECOND_COMPETITOR));
                event.setFirstScore(set.getInt(SQLFieldConstant.Event.FIRST_SCORE));
                event.setSecondScore(set.getInt(SQLFieldConstant.Event.SECOND_SCORE));
                event.setStartDate(set.getDate(SQLFieldConstant.Event.START_DATE));
                event.setStartTime(set.getTime(SQLFieldConstant.Event.START_DATE));
                event.setKindOfSport(set.getString(SQLFieldConstant.Event.KIND_OF_SPORT));
                bet.setEvent(event);
                bet.setTypeBet(set.getString(SQLFieldConstant.Bet.BET_TYPE));
                bet.setExpectedWin(set.getBigDecimal(SQLFieldConstant.Bet.EXPECTED_WIN));
                bet.setAccountLogin(set.getString(SQLFieldConstant.Bet.ACCOUNTS_LOGIN));
                bet.setBetDate(set.getDate(SQLFieldConstant.Bet.BET_DATE));
                bet.setBetTime(set.getTime(SQLFieldConstant.Bet.BET_DATE));
                bet.setAmountCount(set.getBigDecimal(SQLFieldConstant.Bet.AMOUNT_COUNT));
                bet.setCoefficient(set.getBigDecimal(SQLFieldConstant.Bet.BET_COEFFICIENT));
                bet.setResult(set.getInt(SQLFieldConstant.Bet.RESULT));
                bet.setId(set.getInt(SQLFieldConstant.Bet.BET_ID));
                bets.add(0, bet);
                LOGGER.log(Level.INFO, "Succcess in extract bets");
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Fail in extract bets" + e);
            }
        }
        return bets;
    }

    @Override
    public int insertBet(Account account, Bet bet) throws DAOException {
        boolean flag = false;
        int insertID = 0;
        ProxyConnection proxyConnection = null;
        PreparedStatement statementInsertBet = null;
        PreparedStatement statementUpdateInvoice = null;
        PreparedStatement statementAdd = null;
        PreparedStatement statementId = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            proxyConnection.setAutoCommit(false);
            statementInsertBet = proxyConnection.prepareStatement(INSERT_BET);
            statementInsertBet.setString(1, account.getLogin());
            statementInsertBet.setInt(2, bet.getEvent().getId());
            statementInsertBet.setBigDecimal(3, bet.getAmountCount());
            statementInsertBet.setBigDecimal(4, bet.getExpectedWin());

            statementInsertBet.setBigDecimal(5, bet.getCoefficient());
            statementInsertBet.setString(6, bet.getTypeBet());
            statementInsertBet.setString(7, bet.getCurrency());
            statementInsertBet.executeUpdate();

            statementUpdateInvoice = proxyConnection.prepareStatement(UPDATE_INVOICE);
            BigDecimal newBallance = account.getInvoice().getBallance().subtract(bet.getAmountCount());
            statementUpdateInvoice.setBigDecimal(1, newBallance);
            statementUpdateInvoice.setString(2, account.getLogin());
            statementUpdateInvoice.executeUpdate();

            statementAdd = proxyConnection.prepareStatement(ADD_TO_ADMIN);
            statementAdd.setBigDecimal(1, bet.getAmountCount());
            statementAdd.setString(2, bet.getCurrency());
            statementAdd.executeUpdate();

            statementId = proxyConnection.prepareStatement(SELECT_ID);
            ResultSet set = statementId.executeQuery();
            if (set.next()) {
                insertID = set.getInt(1);
            } else {
                throw new DAOException("The bet does not inserted");
            }
            proxyConnection.commit();
            flag = true;
            account.getInvoice().setBallance(newBallance);
        } catch (SQLException | PoolException e) {
            throw new DAOException("Cant insert bet" + e);
        } finally {
            try {
                if (!flag) {
                    proxyConnection.rollback();
                }
                proxyConnection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            closeStatement(statementInsertBet);
            closeStatement(statementUpdateInvoice);
            closeStatement(statementAdd);
            closeStatement(statementId);
            closeConnection(proxyConnection);
        }
        LOGGER.log(Level.INFO, "insertID : ", insertID);
        return insertID;
    }

    @Override
    public void deleteBet(Account account, int id, BigDecimal amount) throws DAOException {
        boolean flag = false;
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        PreparedStatement statementUpdate = null;
        PreparedStatement statementSubstract = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            proxyConnection.setAutoCommit(false);
            statement = proxyConnection.prepareStatement(DELETE_BET);
            statement.setInt(1, id);
            statement.executeUpdate();

            statementUpdate = proxyConnection.prepareStatement(UPDATE_INVOICE);
            BigDecimal newBallance = account.getInvoice().getBallance().add(amount);
            statementUpdate.setBigDecimal(1, newBallance);
            statementUpdate.setString(2, account.getLogin());
            statementUpdate.executeUpdate();

            statementSubstract = proxyConnection.prepareStatement(SUBSTRACT_FROM_ADMIN);
            statementSubstract.setBigDecimal(1, amount);
            statementSubstract.setString(2, account.getInvoice().getCurrency());
            statementSubstract.executeUpdate();

            proxyConnection.commit();
            flag = true;
            if (flag) {
                account.getInvoice().setBallance(newBallance);
            } else {
                proxyConnection.rollback();
            }
            proxyConnection.setAutoCommit(true);
            closeStatement(statement);
            closeStatement(statementUpdate);
            closeStatement(statementSubstract);
            closeConnection(proxyConnection);
            LOGGER.log(Level.INFO, "Delete bet : ", id);
        } catch (SQLException | PoolException e) {
            throw new DAOException("Fail in delete bet operation" + e);
        }
    }

    @Override
    public ArrayList<Bet> selectBetsWithResult(Account account, int result) throws DAOException {
        ArrayList<Bet> bets;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_EVENTS)) {
            preparedStatement.setString(1, account.getLogin());
            preparedStatement.setInt(2, result);
            ResultSet set = preparedStatement.executeQuery();
            bets = takeBets(set);
            for (Bet bt : bets) {
                LOGGER.log(Level.INFO, "Bets with res : ", bt);
            }
        } catch (SQLException | PoolException e) {
            throw new DAOException("Error in select bets with res" + e);
        }
        return bets;
    }

    @Override
    public ArrayList<Bet> selectPlayingBetsOfAccount(Account account) throws DAOException {
        ArrayList<Bet> bets;
        bets = selectBetsWithResult(account, SQLFieldConstant.NOT_FINISHED_BET);
        for (Bet bt : bets) {
            LOGGER.log(Level.INFO, "Playing bets of acc : ", bt);
        }
        return bets;
    }

    @Override
    public ArrayList<Bet> selectWonBetsOfAccount(Account account) throws DAOException {
        ArrayList<Bet> bets;
        bets = selectBetsWithResult(account, SQLFieldConstant.WON_BET);
        for (Bet bt : bets) {
            LOGGER.log(Level.INFO, "Won bets of acc : ", bt);
        }
        return bets;
    }

    @Override
    public ArrayList<Bet> selectLostBetsOfAccount(Account account) throws DAOException {
        ArrayList<Bet> bets;
        bets = selectBetsWithResult(account, SQLFieldConstant.LOST_BET);
        for (Bet bt : bets) {
            LOGGER.log(Level.INFO, "Lost bets of acc : ", bt);
        }
        return bets;
    }

    @Override
    public ArrayList<Bet> selectAllBetsOfAccount(String login) throws DAOException {
        ArrayList<Bet> bets;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_OF_ACCOUNT)) {
            preparedStatement.setString(1, login);
            ResultSet set = preparedStatement.executeQuery();
            bets = takeBets(set);
        } catch (SQLException | PoolException e) {
            throw new DAOException("Error in select all bets of acc " + e);
        }
        return bets;
    }

    @Override
    public ArrayList<Bet> selectAllBetsOnMatch(int eventID) throws DAOException {
        ArrayList<Bet> bets = new ArrayList<>();
        Bet bet;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_BETS_EVENT)) {
            preparedStatement.setInt(1, eventID);
            preparedStatement.setInt(2, SQLFieldConstant.NOT_FINISHED_BET);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                bet = new Bet();
                bet.setId(set.getInt(SQLFieldConstant.Bet.BET_ID));
                bet.setResult(set.getInt(SQLFieldConstant.Bet.RESULT));
                bet.setTypeBet(set.getString(SQLFieldConstant.Bet.BET_TYPE));
                bet.setAmountCount(set.getBigDecimal(SQLFieldConstant.Bet.AMOUNT_COUNT));
                bet.setCoefficient(set.getBigDecimal(SQLFieldConstant.Bet.BET_COEFFICIENT));
                bet.setExpectedWin(set.getBigDecimal(SQLFieldConstant.Bet.EXPECTED_WIN));
                bet.setDateOfMatch(set.getString(SQLFieldConstant.Bet.BET_DATE));
                bet.setAccountLogin(set.getString(SQLFieldConstant.Bet.ACCOUNTS_LOGIN));
                bet.setCurrency(set.getString(SQLFieldConstant.Bet.CURRENCY));
                bets.add(bet);
            }
            for (Bet ae : bets) {
                LOGGER.log(Level.INFO, "All bets on match", ae.toString());
            }
        } catch (SQLException | PoolException e) {
            throw new DAOException("Cant select all betc on match" + e);
        }
        return bets;
    }

    @Override
    public void updateBetsResults(ArrayList<Bet> allBetsOfMatch) throws DAOException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_BETS_RESULTS)
        ) {
            for (Bet b : allBetsOfMatch) {
                preparedStatement.setInt(1, b.getResult());
                preparedStatement.setString(2, b.getAccountLogin());
                preparedStatement.setString(3, b.getDateOfMatch());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | PoolException e) {
            throw new DAOException("Error in update bets res" + e);
        }
    }

    @Override
    public ArrayList<Bet> selectBetsOfCurrency(String currency) throws DAOException {
        ArrayList<Bet> bets;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BETS_OF_CURRENCY)
        ) {
            preparedStatement.setString(1, currency);
            ResultSet set = preparedStatement.executeQuery();
            bets = takeBets(set);
            for (Bet ae : bets) {
                LOGGER.log(Level.INFO, "Bets of curr", ae.toString());
            }
        } catch (SQLException | PoolException e) {
            throw new DAOException("Err in select bets of currency" + e);
        }
        return bets;
    }
}
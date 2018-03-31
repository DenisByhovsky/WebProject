package com.epam.totalizator.service.impl;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.factory.DAOFactory;
import com.epam.totalizator.dao.interfaces.BetDAO;
import com.epam.totalizator.dao.interfaces.CoefficientDAO;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.entity.Event;
import com.epam.totalizator.exception.PoolException;
import com.epam.totalizator.service.BetService;
import com.epam.totalizator.service.ServiceConstant;
import com.epam.totalizator.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Bet service class.
 */
public class BetServiceImpl implements BetService{

    private static final Logger LOGGER = LogManager.getLogger(BetServiceImpl.class.getName());

    private BetDAO betDAO;
    private CoefficientDAO coefficientDAO;
    private static final int SCALE = 2;

    public BetServiceImpl() {
        betDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getBetDAO();
        coefficientDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getCoefficientDAO();
    }

        public Bet makeNewBet(Account account, String type, BigDecimal coef, BigDecimal amount, Event event) throws ServiceException {
            Bet bet;
            try {
                bet = serviceNewBet(account, type, event, coef, amount);
                int betId = betDAO.insertBet(account, bet);
                bet.setId(betId);
                account.addBet(bet);
                LOGGER.log(Level.INFO, "Bet was made");
            }catch (DAOException e){
                throw new ServiceException("Error in make new bet"+e);
            }
            return bet;
        }

        public ArrayList<Bet> fetchWonBets(Account account) throws ServiceException{
            try {
                LOGGER.log(Level.INFO,"Bet results was put");
                return betDAO.selectWonBetsOfAccount(account);
            }catch (DAOException e){
                throw new ServiceException("Error in fetch won bets"+e);
            }
        }


        public ArrayList<Bet> fetchLostBets(Account account) throws ServiceException{
            try {
                return betDAO.selectLostBetsOfAccount(account);
            }catch (DAOException e){
                throw new ServiceException("Error in fetch lost bets"+e);
            }
        }

        public ArrayList<Bet> getWonAndLostBets(Account account) throws ServiceException{
            ArrayList<Bet> result;
            try{
                result = betDAO.selectAllBetsOfAccount(account.getLogin());
                LOGGER.log(Level.INFO, "Won and lost bets done");
            }
            catch (DAOException e){
                throw new ServiceException("Error in get won and lost bets"+e);
            }
            return result;
        }

        public Bet serviceNewBet(Account account, String type, Event event, BigDecimal coef, BigDecimal amount) {
            BigDecimal expectedWin = amount.multiply(coef);
            expectedWin = expectedWin.setScale(SCALE, RoundingMode.HALF_DOWN);
            Bet bet = new Bet();
            bet.setEvent(event);
            bet.setAmountCount(amount);
            bet.setCoefficient(coef);
            bet.setExpectedWin(expectedWin);
            bet.setTypeBet(type);
            bet.setCurrency(account.getInvoice().getCurrency());
            LOGGER.log(Level.INFO, "Bet was serviced", bet.toString());
            return bet;
        }

        public ArrayList<Bet> fetchAllBetsOfAccount(String login) throws ServiceException {
        try {
            return betDAO.selectAllBetsOfAccount(login);
        } catch (DAOException e) {
            throw new ServiceException("Error in fetch all bets of account"+ e);
        }
    }
        public ArrayList<Bet> fetchAllBetsOfCurrency(String currency) throws ServiceException{
            try{
                return betDAO.selectBetsOfCurrency(currency);
            }
            catch (DAOException e){
                throw new ServiceException("Error in fetch all bets of currency" +e);
            }
        }

        public Bet fetchBetById(int id) throws ServiceException, SQLException, PoolException {
            try {
                return betDAO.selectByKey(id);
            }catch (DAOException e){
                throw new ServiceException("Error in fetc bet by id");
            }
        }

        public void cancelBet(Account account, int id, BigDecimal amount) throws ServiceException{
            try{
                betDAO.deleteBet(account, id, amount);
                deleteBet(account.getBets(), id);
            }catch (DAOException e){
                throw new ServiceException("Error in cancel bet"+e);
            }
        }

       public void deleteBet(ArrayList<Bet> bets, int id) {
            for (int i = 0; i < bets.size(); i++){
                if (bets.get(i).getId() == id){
                    bets.remove(i);
                    i--;
                    LOGGER.log(Level.INFO, "Bet was removed");
                }
            }
        }

        public void updateCoefficients(int eventID, String first, String nobody, String second, String firstOrNobody, String firstOrSecond, String secondOrNobody) throws ServiceException{
            try {
                ArrayList<Bet> bets = betDAO.selectAllBetsOnMatch(eventID);
                for(Bet bet: bets){
                    updateCoefficient(bet, first, nobody, second, firstOrNobody, firstOrSecond,secondOrNobody);
                    LOGGER.log(Level.INFO,"update coeff", bet);
                }
                coefficientDAO .updateCoefficientForBets(bets);
            }catch (DAOException e){
                throw new ServiceException("Error in update coefficients");
            }
        }

        public void updateCoefficient(Bet bet, String first, String nobody, String second, String firstOrNobody, String firstOrSecond, String secondOrNobody) throws ServiceException {
            switch (bet.getTypeBet()) {
                case ServiceConstant.ResultType.TYPE_FIRST_VICTORY:
                    bet.setCoefficient(new BigDecimal(first));
                    bet.setExpectedWin(bet.getAmountCount().multiply(new BigDecimal(first)));
                    LOGGER.log(Level.INFO, "First victory");
                    break;
                case ServiceConstant.ResultType.TYPE_FIRST_OR_NOBODY:
                    bet.setCoefficient(new BigDecimal(firstOrNobody));
                    bet.setExpectedWin(bet.getAmountCount().multiply(new BigDecimal(firstOrNobody)));
                    LOGGER.log(Level.INFO, "First or nobody");
                    break;
                case ServiceConstant.ResultType.TYPE_FIRST_OR_SECOND:
                    bet.setCoefficient(new BigDecimal(firstOrSecond));
                    bet.setExpectedWin(bet.getAmountCount().multiply(new BigDecimal(firstOrSecond)));
                    LOGGER.log(Level.INFO, "First or second");
                    break;
                case ServiceConstant.ResultType.TYPE_SECOND_VICTORY:
                    bet.setCoefficient(new BigDecimal(second));
                    bet.setExpectedWin(bet.getAmountCount().multiply(new BigDecimal(second)));
                    LOGGER.log(Level.INFO, "Second victory");
                    break;
                case ServiceConstant.ResultType.TYPE_SECOND_OR_NOBODY:
                    bet.setCoefficient(new BigDecimal(secondOrNobody));
                    bet.setExpectedWin(bet.getAmountCount().multiply(new BigDecimal(secondOrNobody)));
                    LOGGER.log(Level.INFO, "Second or nobody");
                    break;
                case ServiceConstant.ResultType.TYPE_DEAD_HEAT:
                    bet.setCoefficient(new BigDecimal(nobody));
                    bet.setExpectedWin(bet.getAmountCount().multiply(new BigDecimal(nobody)));
                    LOGGER.log(Level.INFO, "Dead heat");
                    break;
                default:
                    throw new ServiceException("This Type of Bet  is incorrect.");
            }
        }

}

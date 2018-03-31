package com.epam.totalizator.service.impl;

import com.epam.totalizator.exception.DAOException;
import com.epam.totalizator.dao.factory.DAOFactory;
import com.epam.totalizator.dao.interfaces.BetDAO;
import com.epam.totalizator.dao.interfaces.EventDAO;
import com.epam.totalizator.dao.interfaces.InvoiceDAO;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.service.ResultService;
import com.epam.totalizator.service.ServiceConstant;
import com.epam.totalizator.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Result service class.
 */
public class ResultServiceImpl implements ResultService {

    private static final Logger LOGGER = LogManager.getLogger(ResultServiceImpl.class.getName());
    private EventDAO eventDAO;
    private BetDAO betDAO;
    private InvoiceDAO invoiceDAO;
    public ResultServiceImpl(){
        eventDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEventDAO();
        betDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getBetDAO();
        invoiceDAO= DAOFactory.getDAOFactory(DAOFactory.MYSQL).getInvoiceDAO();
    }


    public void putBetsResults(int eventID, int firstScore, int secondScore) throws ServiceException {
        ArrayList<Bet> allBetsOfMatch;
        ArrayList<Bet> wonBets = new ArrayList<>();
        try{
            allBetsOfMatch =betDAO.selectAllBetsOnMatch(eventID);
            int result;
            for (Bet b: allBetsOfMatch){
                result = determineTheResult(b.getTypeBet(), firstScore, secondScore);
                b.setResult(result);
                if (result == ServiceConstant.ResultBet.WON_BET){
                    wonBets.add(b);
                }
            }
            betDAO.updateBetsResults(allBetsOfMatch);
            invoiceDAO.updateWonInvoices(wonBets);
            LOGGER.log(Level.INFO,"Bet results was put");

        }catch (DAOException e){
            throw new ServiceException("Error in put bets results"+e);
        }
    }

    public void putResults(int eventID, int firstScore, int secondScore) throws ServiceException {
        try {
            eventDAO .insertEventResults(eventID, firstScore, secondScore);
        }catch (DAOException e){
            throw new ServiceException("Error in put results"+e);
        }
    }

    public int determineTheResult(String typeBet, int firstScore, int secondScore) throws ServiceException {
        int result;
        switch (typeBet) {
            case ServiceConstant.ResultType.TYPE_FIRST_VICTORY:
                if (firstScore > secondScore) {
                    result = ServiceConstant.ResultBet.WON_BET;
                } else {
                    result = ServiceConstant.ResultBet.LOST_BET;
                }
                break;
            case ServiceConstant.ResultType.TYPE_FIRST_OR_NOBODY:
                if (firstScore >= secondScore) {
                    result = ServiceConstant.ResultBet.WON_BET;
                } else {
                    result = ServiceConstant.ResultBet.LOST_BET;
                }
                break;
            case ServiceConstant.ResultType.TYPE_FIRST_OR_SECOND:
                if (firstScore != secondScore) {
                    result = ServiceConstant.ResultBet.WON_BET;
                } else {
                    result = ServiceConstant.ResultBet.LOST_BET;
                }
                break;
            case ServiceConstant.ResultType.TYPE_SECOND_VICTORY:
                if (secondScore > firstScore) {
                    result = ServiceConstant.ResultBet.WON_BET;
                } else {
                    result = ServiceConstant.ResultBet.LOST_BET;
                }
                break;
            case ServiceConstant.ResultType.TYPE_DEAD_HEAT:
                if (firstScore == secondScore) {
                    result = ServiceConstant.ResultBet.WON_BET;
                } else {
                    result = ServiceConstant.ResultBet.LOST_BET;
                }
                break;

            case ServiceConstant.ResultType.TYPE_SECOND_OR_NOBODY:
                if (secondScore >= firstScore) {
                    result = ServiceConstant.ResultBet.WON_BET;
                } else {
                    result = ServiceConstant.ResultBet.LOST_BET;
                }
                break;
            default:
                throw new ServiceException("Typebet is incorrect ");
        }
        LOGGER.log(Level.INFO,"Result was determined");
        return result;
    }
    }
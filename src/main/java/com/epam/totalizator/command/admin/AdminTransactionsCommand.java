package com.epam.totalizator.command.admin;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AdminTransactionsCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(AdminTransactionsCommand.class.getName());

    static final String CURRENCY = "currency";
    static final String BETS = "bets";

    public AdminTransactionsCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String currency = request.getParameter(CURRENCY);
        ArrayList<Bet> bets = null;
        try {
            bets = ServiceFactory.getInstance().getBetService().fetchAllBetsOfCurrency(currency);
        }catch (ServiceException e){
            LOGGER .error("Err in transaction command");
        }
        String page = request.getSession().getAttribute(PAGE).toString();
        LOGGER.log(Level.INFO,"Transaction success comm");
        request.setAttribute(BETS, bets);
        return page;
    }
}
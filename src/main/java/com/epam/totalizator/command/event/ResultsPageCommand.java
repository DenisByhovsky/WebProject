package com.epam.totalizator.command.event;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.TypeEvent;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;


public class ResultsPageCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(ResultsPageCommand.class.getName());

    static final String ACTION_EVENTS = "actionEvents";

    public ResultsPageCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        try {
            TypeEvent events = ServiceFactory.getInstance().getEventService().fetchAllFinishedMatches();
            request.setAttribute(ACTION_EVENTS, events.getDescriptions());
        }catch (ServiceException e){
            LOGGER.error("Error in results comm"+e);
        }
        page = ServiceFactory.getInstance().getEventService().determineResultsPage(account);
        LOGGER.log(Level.INFO,"Success res comm");
        return page;
    }
}
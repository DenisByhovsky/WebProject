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

public class SortResultsCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(SortResultsCommand.class.getName());

    static final String ACTION_EVENTS = "actionEvents";
    static final String KIND_OF_SPORT = "kindOfSport";

    public SortResultsCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        try {
            String kindOfSport = request.getParameter(KIND_OF_SPORT);
            TypeEvent events = ServiceFactory.getInstance().getEventService().fetchResultsOfKind(kindOfSport);
            request.setAttribute(ACTION_EVENTS, events.getDescriptions());
        }catch (ServiceException e){
            LOGGER.error("Err in sort res command"+e);
        }
        LOGGER.log(Level.INFO,"Success sort res comm");
        page = ServiceFactory.getInstance().getEventService().determineResultsPage(account);

        return page;
    }
}
package com.epam.totalizator.command.admin;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.entity.TypeEvent;
import com.epam.totalizator.manager.ManagerHandler;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class BringInResultsPageCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(BringInResultsPageCommand.class.getName());

    static final String ACTION_EVENTS = "actionEvents";

    public BringInResultsPageCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        String page = null;
        if (account != null && account.getRole() == AccountRole.ADMIN) {
            try {
                TypeEvent events = ServiceFactory.getInstance().getEventService().fetchAllUnfinishedMatches();
                request.setAttribute(ACTION_EVENTS, events.getDescriptions());
                page = ManagerHandler.getPage(ManagerHandler.PUT_RESULTS);
            } catch (ServiceException e) {
                LOGGER.error("Err in put res comm",e);
            }
        }else{
            page = ManagerHandler.getPage(ManagerHandler.LOGIN_PAGE);
        }
        LOGGER.log(Level.INFO,"Success");
        return page;
    }
}
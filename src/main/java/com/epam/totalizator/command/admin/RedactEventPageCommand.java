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

public class RedactEventPageCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(RedactEventPageCommand.class.getName());

    public RedactEventPageCommand(){ }
    static final String ACTION_EVENTS = "actionEvents";

    @Override
    public String execute(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        String page;
        if (account != null && account.getRole() == AccountRole.ADMIN) {
            page = ManagerHandler.getPage(ManagerHandler.ADMIN_EDIT_EVENT);
            try {

                TypeEvent events = ServiceFactory.getInstance().getEventService().fetchMatchesWithoutCoefficients();
                request.setAttribute(ACTION_EVENTS , events.getDescriptions());
            } catch (ServiceException e) {
                LOGGER.error("Err in edit event comm");
            }
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(Level.INFO,"Success");
        return page;
    }
}
package com.epam.totalizator.command.bookmaker;

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

public class UpdatePageCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(UpdatePageCommand.class.getName());

    static final String ACTION_EVENTS = "actionEvents";

    public UpdatePageCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
         TypeEvent events = null;
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        if (account != null && account.getRole() == AccountRole.BOOKMAKER) {
            page = ManagerHandler.getPage(ManagerHandler.BOOKMAKER_UPDATE_PAGE);
            try {
                events = ServiceFactory.getInstance().getEventService().fetchAllUnfinishedMatches();
            } catch (ServiceException e) {
                LOGGER.error("Error in update comm",e);
            }
            request.setAttribute(ACTION_EVENTS, events.getDescriptions());
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(Level.INFO,"Sucess update");
        return page;
    }
}
package com.epam.totalizator.command.page;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.TypeEvent;
import com.epam.totalizator.manager.ManagerHandler;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MainPageCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(MainPageCommand.class.getName());

    static final String ACTION_EVENTS = "actionEvents";

    public MainPageCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute(ACCOUNT);
        if(account != null){
            try {
                TypeEvent events;
                LOGGER.log(Level.INFO,"Role",account.getRole());
                switch (account.getRole()){
                    case ADMIN:
                        page = ManagerHandler.getPage(ManagerHandler.ADMIN_MAIN_PAGE);
                        events = ServiceFactory.getInstance().getEventService().fetchAllUnfinishedMatches();
                        request.setAttribute(ACTION_EVENTS, events.getDescriptions());
                        break;
                    case BOOKMAKER:
                        page = ManagerHandler.getPage(ManagerHandler.BOOKMAKER_ARRANGE_PAGE);
                        events = ServiceFactory.getInstance().getEventService().fetchMatchesWithoutCoefficients();
                        request.setAttribute(ACTION_EVENTS, events.getDescriptions());
                        break;
                    case CLIENT:
                        page = ManagerHandler.getPage(ManagerHandler.CLIENT_MAIN_PAGE);
                        break;
                    default:
                        page = ManagerHandler.getPage(ManagerHandler.MAIN_PAGE);
                }
            }catch (ServiceException e){
                LOGGER.error("Err in main page"+e);
            }
        }else {
            page = ManagerHandler.getPage(ManagerHandler.MAIN_PAGE);
        }
        return page;
    }
}
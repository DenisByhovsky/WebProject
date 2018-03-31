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


public class CybersportPageCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(CybersportPageCommand.class.getName());

    static final String ACTION_EVENTS = "actionEvents";
    static final String EVENT_TYPE_CYBERSPORT = "cybersport";

    public CybersportPageCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Account account = (Account)request.getSession().getAttribute(ACCOUNT);
        TypeEvent events = null;
        try {
            events = ServiceFactory.getInstance().getEventService().fetchAllEventsOfType(EVENT_TYPE_CYBERSPORT);
        }catch (ServiceException e){
            LOGGER.error("Error in cybersport"+e);
        }
        if(account != null){
            page = ManagerHandler.getPage(ManagerHandler.CLIENT_CYBERSPORT_PAGE);
        }else {
            page = ManagerHandler.getPage(ManagerHandler.LOGIN_PAGE);
        }
        request.setAttribute(ACTION_EVENTS , events.getDescriptions());
        LOGGER.log(Level.INFO,"Cybersport success comm");
        return page;
    }
}
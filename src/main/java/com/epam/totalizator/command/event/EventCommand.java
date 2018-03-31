package com.epam.totalizator.command.event;

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

public class EventCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(EventCommand.class.getName());

    static final String ACTION_EVENTS = "actionEvents";
    static final String KIND_OF_SPORT = "kindOfSport";
    static final String EVENT_TYPE = "event_type";
    static final String EVENT_TYPE_SPORT = "sport";
    static final String EVENT_TYPE_CYBERSPORT = "cybersport";

    public EventCommand(){ }

    public String execute(HttpServletRequest request) {
        String page = null;
        String eventType = request.getParameter(EVENT_TYPE);
        String kindOfSport = request.getParameter(KIND_OF_SPORT);
        TypeEvent events = new TypeEvent();
        try {
            events = ServiceFactory.getInstance().getEventService().fetchMatchesOfType(kindOfSport);
            events.setEventType(eventType);
            request.setAttribute(ACTION_EVENTS, events.getDescriptions());
        }catch (ServiceException e){
            LOGGER.error(e);
        }
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        if (account != null) {
            if(EVENT_TYPE_SPORT.equals(events.getEventType())) {
                page = ManagerHandler.getPage(ManagerHandler.CLIENT_SPORT_PAGE);
            }else if (EVENT_TYPE_CYBERSPORT.equals(events.getEventType())){
                page = ManagerHandler.getPage(ManagerHandler.CLIENT_CYBERSPORT_PAGE);
            }
        }
        else{
            page = ManagerHandler.getPage(ManagerHandler.LOGIN_PAGE);
        }
        LOGGER.log(Level.INFO,"Succes event comm",page);
        return page;
    }
}
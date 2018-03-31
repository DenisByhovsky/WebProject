package com.epam.totalizator.command.client;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Event;
import com.epam.totalizator.manager.ManagerHandler;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class OpenBetCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(OpenBetCommand.class.getName());

    static final String EVENT = "event";
    static final String COEF = "coef";
    static final String BET_TYPE = "betType";

    public OpenBetCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        Event event = null;
        int eventID = Integer.parseInt(request.getParameter(EVENT));
        double coefficient = Double.parseDouble(request.getParameter(COEF));
        String type = request.getParameter(BET_TYPE);
        try {
            event = ServiceFactory.getInstance().getEventService().fetchEventById(eventID);
        }catch (ServiceException e){
            LOGGER.error("Err in open bet command"+e);
        }
        request.setAttribute(EVENT, event);
        request.setAttribute(COEF, coefficient);
        request.setAttribute(BET_TYPE, type);
        LOGGER.log(Level.INFO,"Success  open bet command");
        return ManagerHandler.getPage(ManagerHandler.CLIENT_BET_EXECUTION);
    }
}
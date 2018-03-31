package com.epam.totalizator.command.admin;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;


public class DeleteEventCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(DeleteEventCommand.class.getName());

    static final String EVENTID = "eventID";

    public DeleteEventCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        String page;
        if (account != null && account.getRole() == AccountRole.ADMIN){
            page = request.getSession().getAttribute(PAGE).toString();
            int eventID = Integer.parseInt(request.getParameter(EVENTID));
            try {
                ServiceFactory.getInstance().getEventService().deleteEventByID(eventID);
            }catch (ServiceException e){
                LOGGER.error("Err in delete match comm" +e);
            }
        }
        else{
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(Level.INFO,"Success  delete match comm");
        return page;
    }
}
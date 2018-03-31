package com.epam.totalizator.command.admin;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import com.epam.totalizator.utill.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class RedactEventCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(RedactEventCommand.class.getName());

    static final String FAIL = "fail";
    static final String KIND_OF_SPORT = "kindOfSport";
    static final String DESCRIPTION = "description";
    static final String FIRST_COMPETITOR = "first_competitor";
    static final String SECOND_COMPETITOR = "second_competitor";
    static final String DATE = "date";
    static final String TIME = "time";
    static final String EVENTID = "eventID";

    public RedactEventCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        String page;
        if (account != null && account.getRole() == AccountRole.ADMIN){
            page = request.getSession().getAttribute(PAGE).toString();
            int eventID = Integer.parseInt(request.getParameter(EVENTID));
            String kindOfSport = request.getParameter(KIND_OF_SPORT);
            String description = request.getParameter(DESCRIPTION);
            String firstCompetitor = request.getParameter(FIRST_COMPETITOR);
            String secondCompetitor = request.getParameter(SECOND_COMPETITOR);
            String date = request.getParameter(DATE);
            String time = request.getParameter(TIME);
            String dateTime = date + " " + time;
            if (Validator.checkNewMatch(date, time)) {
                try {
                    ServiceFactory.getInstance().getEventService().updateEvent(eventID, kindOfSport, description, firstCompetitor, secondCompetitor, dateTime);
                    request.setAttribute(EVENTID, eventID);
                }catch (ServiceException e){
                    LOGGER.error("Err in edit event comm" +e);
                }
            }else {
                request.setAttribute(EVENTID, eventID);
                request.setAttribute(FAIL, FAIL);
            }
        }else{
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(Level.INFO,"Event edited successfully");
        return page;
    }
}
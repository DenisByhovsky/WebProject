package com.epam.totalizator.command.admin;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import com.epam.totalizator.utill.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import static org.apache.logging.log4j.Level.INFO;

public class AddEventCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(AddEventCommand.class.getName());

    static final String KIND_OF_SPORT = "kindOfSport";
    static final String EVENT_TYPE = "event_type";
    static final String DESCRIPTION = "description";
    static final String FIRST_COMPETITOR = "first_competitor";
    static final String SECOND_COMPETITOR = "second_competitor";
    static final String DATE = "date";
    static final String TIME = "time";
    static final String SPORT = "sport";
    static final String SPORT_SUCCESS = "sportSuccess";
    static final String CYBER_SUCCESS = "cyberSuccess";
    static final String CYBER_FAIL = "cyberFail";
    static final String SPORT_FAIL = "sportFail";
    static final String SUCCESS_ADDING = "add.success";
    static final String ADDING_FAIL = "add.fail";


    public AddEventCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        if (account != null && account.getRole().equals(AccountRole.ADMIN)) {
            page = request.getSession().getAttribute(PAGE).toString();
            String eventType = request.getParameter(EVENT_TYPE);
            String kindOfSport = request.getParameter(KIND_OF_SPORT);
            String description = request.getParameter(DESCRIPTION);
            String firstCompetitor = request.getParameter(FIRST_COMPETITOR);
            String secondCompetitor = request.getParameter(SECOND_COMPETITOR);
            String date = request.getParameter(DATE);
            String time = request.getParameter(TIME);
            String dateTime = date + " " + time;
           if (Validator.checkNewMatch(date, time)) {
                try {
                    ServiceFactory.getInstance().getEventService().addNewEvent(eventType, kindOfSport, description, firstCompetitor, secondCompetitor, dateTime);
                    if (eventType.equals(SPORT)) {
                        request.setAttribute(SPORT_SUCCESS, SUCCESS_ADDING);
                    } else {
                        request.setAttribute(CYBER_SUCCESS, SUCCESS_ADDING);
                    }
                }
                catch (ServiceException e) {
                    LOGGER.error("Err in add event command" +e);
                }
           }else {
                if (eventType.equals(SPORT)) {
                    request.setAttribute(SPORT_FAIL, ADDING_FAIL);
                } else {
                    request.setAttribute(CYBER_FAIL, ADDING_FAIL);
               }
           }
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(INFO,"Success add new event comm",page);
        return page;
    }
}
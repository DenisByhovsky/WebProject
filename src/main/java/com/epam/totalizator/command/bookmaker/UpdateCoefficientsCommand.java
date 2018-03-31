package com.epam.totalizator.command.bookmaker;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.entity.Event;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import com.epam.totalizator.utill.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class UpdateCoefficientsCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(UpdateCoefficientsCommand.class.getName());

    static final String FAIL = "fail";
    static final String EVENT_ID = "event_id";
    static final String WIN_FIRST = "win_first";
    static final String NOBODY = "nobody";
    static final String WIN_SECOND = "win_second";
    static final String FIRST_OR_NOBODY = "first_or_nobody";
    static final String FIRST_OR_SECOND = "first_or_second";
    static final String SECOND_OR_NOBODY = "second_or_nobody";
    static final String EVENTID = "eventID";

    public UpdateCoefficientsCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        String page;
        if (account != null && account.getRole() == AccountRole.BOOKMAKER) {
            page = request.getSession().getAttribute(PAGE).toString();
            try {
                int eventID = Integer.parseInt(request.getParameter(EVENT_ID));
                Event event = ServiceFactory.getInstance().getEventService().selectEventById(eventID);
                if (Validator.checkEventDate(event)) {
                    String first = Validator.checkCoefficient(request.getParameter(WIN_FIRST));
                    String nobody = Validator.checkCoefficient(request.getParameter(NOBODY));
                    String second = Validator.checkCoefficient(request.getParameter(WIN_SECOND));
                    String fon = Validator.checkCoefficient(request.getParameter(FIRST_OR_NOBODY));
                    String fos = Validator.checkCoefficient(request.getParameter(FIRST_OR_SECOND));
                    String son = Validator.checkCoefficient(request.getParameter(SECOND_OR_NOBODY));
                    ServiceFactory.getInstance().getEventService().updateCoefficients(eventID, first, nobody, second, fon, fos, son);
                    ServiceFactory.getInstance().getBetService().updateCoefficients(eventID, first, nobody, second, fon, fos, son);
                    request.setAttribute(EVENTID, eventID);
                }else {
                    request.setAttribute(FAIL, FAIL);
                    request.setAttribute(EVENTID, eventID);
                }
            } catch (ServiceException e) {
                LOGGER.error("Err in update coef comm"+e);
            }
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(Level.INFO,"Success update coeff comm");
        return page;
    }
}
package com.epam.totalizator.command.admin;

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


public class BringInResultsCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(BringInResultsCommand.class.getName());

    static final String FIRST_SCORE = "score-first";
    static final String SECOND_SCORE = "score-second";
    static final String EVENTID = "eventID";
    static final int BAD_RESULT = -1;

    public BringInResultsCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        String page;
        if (account != null && account.getRole() == AccountRole.ADMIN) {
            page = request.getSession().getAttribute(PAGE).toString();
            int eventID = Integer.parseInt(request.getParameter(EVENTID));
            try{
                Event event = ServiceFactory.getInstance().getEventService().selectEventById(eventID);
                if (!Validator.checkEventDate(event)) {
                    int firstScore = Validator.checkScoreCount(request.getParameter(FIRST_SCORE));
                    int secondScore = Validator.checkScoreCount(request.getParameter(SECOND_SCORE));
                    if (firstScore != BAD_RESULT && secondScore != BAD_RESULT) {
                        ServiceFactory.getInstance().getResultService().putResults(eventID, firstScore, secondScore);
                        ServiceFactory.getInstance().getResultService().putBetsResults(eventID, firstScore, secondScore);
                    }else {
                        request.setAttribute(EVENTID, eventID);
                    }
                }else {
                    request.setAttribute(EVENTID, eventID);
                }
            } catch (ServiceException e) {
                LOGGER.error("Err in put results comm"+e);
            }
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(Level.INFO,"Success in put res comm");
        return page;
    }
}
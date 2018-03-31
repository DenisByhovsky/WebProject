package com.epam.totalizator.command.client;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.entity.Event;
import com.epam.totalizator.manager.ManagerHandler;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import com.epam.totalizator.utill.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class MakeBetCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(MakeBetCommand.class.getName());

    static final String FAIL = "fail";
    static final String EVENT = "event";
    static final String COEFFICIENT = "coef";
    static final String AMOUNT = "amount";
    static final String NEW_BET = "newBet";
    static final String BET_TYPE = "betType";
    static final String NO_MONEY = "no.money";
    static final String MATCH_START= "match.start";


    public MakeBetCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(ACCOUNT);
        if (account != null && account.getRole() == AccountRole.CLIENT){
            int eventID = Integer.parseInt(request.getParameter(EVENT));
            BigDecimal coefficient = new BigDecimal(request.getParameter(COEFFICIENT));
            BigDecimal amount = new BigDecimal(request.getParameter(AMOUNT));
            String betType = request.getParameter(BET_TYPE);
           try {
                Event event = ServiceFactory.getInstance().getEventService().selectEventById(eventID);
                if(!Validator.checkEventDate(event)){
                 return getIncorrectBetPage(request, event, coefficient, betType, MATCH_START);
              }
               if (!Validator.checkCash(amount, account.getInvoice())) {
                    return getIncorrectBetPage(request, event, coefficient, betType, NO_MONEY);
                }
                Bet bet = ServiceFactory.getInstance().getBetService().makeNewBet(account, betType, coefficient, amount, event);
               if (bet != null){
                    page = ManagerHandler.getPage(ManagerHandler.SUCCESS_BET);
                    session.setAttribute(NEW_BET, bet);
                }
            }catch (ServiceException e){
               LOGGER.error("Err in make bet command"+e);
            }
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        request.setAttribute(DISPATCHER_TYPE, SEND_REDIRECT);
        LOGGER.log(Level.INFO,"Success make bet command");
        return page;
    }

    private String getIncorrectBetPage(HttpServletRequest request, Event event, BigDecimal coef, String betType, String problem){
        request.setAttribute(EVENT, event);
        request.setAttribute(FAIL, problem);
        request.setAttribute(COEFFICIENT, coef);
        request.setAttribute(BET_TYPE, betType);
        return ManagerHandler.getPage(ManagerHandler.CLIENT_BET_EXECUTION );
    }
}
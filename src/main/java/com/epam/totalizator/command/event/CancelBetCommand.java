package com.epam.totalizator.command.event;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.exception.PoolException;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import com.epam.totalizator.utill.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;

public class CancelBetCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(CancelBetCommand.class.getName());

    static final String FAIL = "fail";
    static final String BET = "bet";
    static final String AMOUNT = "amount";
    static final String BET_FAIL = "bet.failed";

    public CancelBetCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        if (account != null && account.getRole() == AccountRole.CLIENT) {
            int betID = Integer.parseInt(request.getParameter(BET));
            BigDecimal amount = new BigDecimal(request.getParameter(AMOUNT));
            try {
                Bet bet = ServiceFactory.getInstance().getBetService().fetchBetById(betID);
                if (Validator.checkEventDate(bet.getEvent())) {
                    ServiceFactory.getInstance().getBetService().cancelBet(account, betID, amount);
                }else {
                    request.setAttribute(FAIL, BET_FAIL);
                }
            } catch (ServiceException e) {
                LOGGER.error("Error in cansel bet"+e);
            } catch (SQLException | PoolException e) {
                LOGGER.error("Error in SQL cansel bet"+e);
            }
            page = request.getSession().getAttribute(PAGE).toString();
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(Level.INFO,"Success bet canseled comm");
        return page;
    }
}
package com.epam.totalizator.command.client;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.manager.ManagerHandler;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class BetInfoCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(BetInfoCommand.class.getName());

    static final String BETS = "bets";
    static final String WIN = "win";
    static final String LOSE = "lose";
    static final String BET_TYPE = "betType";

    public BetInfoCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        ArrayList<Bet> result = null;
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        if(account != null && account.getRole() == AccountRole.CLIENT) {
            try {
                String betType = request.getParameter(BET_TYPE);
                switch (betType) {
                    case WIN:
                        result = ServiceFactory.getInstance().getBetService().fetchWonBets(account);
                        break;
                    case LOSE:
                        result = ServiceFactory.getInstance().getBetService().fetchLostBets(account);
                        break;
                    default:
                        result = ServiceFactory.getInstance().getBetService().getWonAndLostBets(account);
                }
            }catch (ServiceException e){
                LOGGER.error("Err in info bet command");
            }
            page = ManagerHandler.getPage(ManagerHandler.CLIENT_BET_INFO);
            request.setAttribute(BETS, result);
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(Level.INFO,"Success bet description command");
        return page;
    }
}
package com.epam.totalizator.command.admin;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.entity.Bet;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class BetsCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(BetsCommand.class.getName());

    static final String LOGIN = "login";
    static final String BETS = "bets";

    public BetsCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        if(account != null && account.getRole() == AccountRole.ADMIN){
            page = request.getSession().getAttribute(PAGE).toString();
            String login = request.getParameter(LOGIN);
            try{
                ArrayList<Bet> bets = ServiceFactory.getInstance().getBetService().fetchAllBetsOfAccount(login);
                request.setAttribute(BETS, bets);
            }catch (ServiceException e){
                LOGGER.error("Err in bets account command");
            }
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        return page;
    }
}
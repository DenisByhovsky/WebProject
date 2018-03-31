package com.epam.totalizator.command.authorization;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.manager.ManagerHandler;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import static org.apache.logging.log4j.Level.ERROR;
import static org.apache.logging.log4j.Level.INFO;

public class SignInCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class.getName());

    static final String LOGIN = "login";
    static final String PASSWORD = "password";
    static final String INCORRECT_PASS_OR_LOGIN = "login.fail";
    static final String ACCOUNT = "account";
    static final String FAIL = "fail";

    public SignInCommand(){ }

    /**
     * Command that authenticate user and redirect it to main page depending on it's role.
     * This command can be used by all.
     * @author Denis Byhovsky
     */

    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(LOGIN);
        String pass = request.getParameter(PASSWORD);
        try {

            Account account = ServiceFactory.getInstance().getAccountService().loginAccount(login, pass);
            if (account != null) {
                request.getSession().setAttribute(ACCOUNT, account);
                switch (account.getRole()) {
                    case CLIENT:
                        page = ManagerHandler.getPage(ManagerHandler.CLIENT_MAIN_PAGE);
                        LOGGER.log(INFO,"Login as client");
                        break;

                    case ADMIN:
                        page = ManagerHandler.getPage(ManagerHandler.ADMIN_MAIN_PAGE);
                        LOGGER.log(INFO,"Login as admin");
                        break;

                    case BOOKMAKER:
                        page =ManagerHandler.getPage(ManagerHandler.BOOKMAKER_UPDATE_PAGE);
                        LOGGER.log(INFO,"Login as bookmaker");
                        break;
                    default:
                        page = ManagerHandler.getPage(ManagerHandler.MAIN_PAGE);
                }
            } else {
                request.setAttribute(FAIL, INCORRECT_PASS_OR_LOGIN);
                page = ManagerHandler.getPage(ManagerHandler.LOGIN_PAGE);
                LOGGER.log(INFO,"Incorrect pass or login");
           }
        }catch (ServiceException e){
          LOGGER.log(ERROR,"Exception inlogin command" + e);
        page = ManagerHandler.getPage(ManagerHandler.LOGIN_PAGE);
       }
        return page;
    }
}

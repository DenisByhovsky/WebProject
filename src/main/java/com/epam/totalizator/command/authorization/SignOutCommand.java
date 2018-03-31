package com.epam.totalizator.command.authorization;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.manager.ManagerHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(SignOutCommand.class.getName());

    public SignOutCommand() { }

    /**
    * Command that invalidates current session.
    * This command can be used only by authorized users.
    * @author Denis Byhovsky
    */
    public String execute(HttpServletRequest request) {
        HttpSession currentSession = request.getSession(false);
        if (currentSession  != null) {
            currentSession .invalidate();
        }
        String page = ManagerHandler.getPage(ManagerHandler.MAIN_PAGE);
        LOGGER.log(Level.INFO,"Success signout command");
        return page;

    }
}
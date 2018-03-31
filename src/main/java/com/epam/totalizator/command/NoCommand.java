package com.epam.totalizator.command;

import com.epam.totalizator.manager.ManagerHandler;

import javax.servlet.http.HttpServletRequest;

public class NoCommand implements  ActionCommand{
        public String execute(HttpServletRequest request) {
            String page = ManagerHandler.getPage(ManagerHandler.LOGIN_PAGE);
            request.setAttribute(ATTR_PAGE, SEND_REDIRECT);
            return page;
        }

}

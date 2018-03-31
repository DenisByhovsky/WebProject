package com.epam.totalizator.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {

    String DISPATCHER_TYPE = "type";
    String SEND_REDIRECT = "redirect";
    String FORWARD_PAGE = "forward";
    String ATTR_PAGE = "toPage";
    String ACCOUNT = "account";
    String PAGE = "page";
    String CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE = "/controller?command=redirect_to_main_page";

    String execute(HttpServletRequest request) ;

}
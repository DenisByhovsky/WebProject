package com.epam.totalizator.manager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ResourceBundle;

/**
 * Manages pages.
 */
public class ManagerHandler {

    private static final Logger LOGGER = LogManager.getLogger(ManagerHandler.class.getName());

    private static ManagerHandler managerHandler;
    private static ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "pages";

    public static final String MAIN_PAGE = "MAIN_PAGE";
    public static final String LOGIN_PAGE = "LOGIN_PAGE";
    public static final String SIGNUP_PAGE = "SIGNUP_PAGE";

    //CLIENT
    public static final String CLIENT_SPORT_PAGE = "CLIENT_SPORT_PAGE";
    public static final String CLIENT_CYBERSPORT_PAGE = "CLIENT_CYBERSPORT_PAGE";
    public static final String CLIENT_BET_EXECUTION= "CLIENT_BET_EXECUTION";
    public static final String CLIENT_BET_INFO = "CLIENT_BET_INFO";
    public static final String CLIENT_BALANCE_INFO = "CLIENT_BALANCE_INFO";
    public static final String CLIENT_MAIN_PAGE = "CLIENT_MAIN_PAGE";
    public static final String CLIENT_SUCCESS_PAGE = "CLIENT_SUCCESS_PAGE";
    public static final String CLIENT_FAIL_PAGE = "CLIENT_FAIL_PAGE";

    //BOOKMAKER
    public static final String BOOKMAKER_ARRANGE_PAGE = "BOOKMAKER_ARRANGE_PAGE";
    public static final String BOOKMAKER_UPDATE_PAGE = "BOOKMAKER_UPDATE_PAGE";

    //ADMIN
    public static final String PUT_RESULTS = "PUT_RESULTS";
    public static final String ADMIN_MAIN_PAGE = "ADMIN_MAIN_PAGE";
    public static final String ADMIN_RESULTS = "ADMIN_RESULTS";
    public static final String ADMIN_EDIT_EVENT = "ADMIN_EDIT_EVENT";
    public static final String RESULTS_CLIENT = "CLIENT_RESULTS";
    public static final String SUCCESS_BET = "SUCCESS_BET";
    public static final String RESUTLS_PAGE = "RESUTLS_PAGE";
    public static final String INVOICE_STATUS = "INVOICE_STATUS";

    private ManagerHandler() {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }

    public static String getPage(String key) {
        if (managerHandler == null) {
            managerHandler = new ManagerHandler();
        }
        LOGGER.log(Level.INFO,"get Page : ",resourceBundle.getString(key));
        return resourceBundle.getString(key);
    }

}
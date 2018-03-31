package com.epam.totalizator.command;

import java.util.Arrays;

public enum Operation{

        NO_COMMAND,
        LOGIN,
        LOGOUT,
        SIGNUP,
        REDIRECT_TO_MAIN_PAGE,
        REDIRECT_TO_SPORT_PAGE,
        REDIRECT_TO_CYBERSPORT_PAGE,
        REDIRECT_TO_UPDATE,
        EVENT,
        TRANSACTION,
        INVOICE_INFO,
        OPEN_BET,
        MAKE_BET,
        BET_INFO,
        CHANGE_PASSWORD,
        ADD_MATCH,
        ARRANGE_COEFFS,
        UPDATE_COEFFS,
        PUT_RESULT_PAGE,
        PUT_RESULTS,
        RESULTS_PAGE,
        REDIRECT_TO_INVOICE_STATUS,
        ADMIN_TRANSACTIONS,
        CANCEL_BET,
        EDIT_EVENT_PAGE,
        EDIT_MATCH,
        DELETE_MATCH,
        SORT_RESULTS_INFO,
        SHOW_ACCOUNT_BETS;

    public static Operation convert(String command) {
            Operation operation = Arrays.stream(values()).
                filter(it -> it.name().equalsIgnoreCase(command)).findFirst().orElse(REDIRECT_TO_MAIN_PAGE);
        return operation;
    }
}

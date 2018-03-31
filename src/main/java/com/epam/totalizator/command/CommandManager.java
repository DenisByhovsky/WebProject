package com.epam.totalizator.command;

import com.epam.totalizator.command.admin.*;
import com.epam.totalizator.command.authorization.SignInCommand;
import com.epam.totalizator.command.authorization.SignOutCommand;
import com.epam.totalizator.command.authorization.SignUpCommand;
import com.epam.totalizator.command.bookmaker.ArrangeCoefficientsCommand;
import com.epam.totalizator.command.bookmaker.UpdateCoefficientsCommand;
import com.epam.totalizator.command.bookmaker.UpdatePageCommand;
import com.epam.totalizator.command.client.*;
import com.epam.totalizator.command.event.*;
import com.epam.totalizator.command.page.CybersportPageCommand;
import com.epam.totalizator.command.page.MainPageCommand;
import com.epam.totalizator.command.page.SportPageCommand;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;
import static com.epam.totalizator.command.Operation.*;

public class CommandManager {

    private static final Logger LOGGER = LogManager.getLogger(CommandManager.class.getName());

    private static Map<Operation, ActionCommand> map =  new HashMap<Operation, ActionCommand>();

    private CommandManager() { }

    static {
        map.put(LOGIN, new SignInCommand());
        map.put(SIGNUP, new SignUpCommand());
        map.put(Operation.LOGOUT, new SignOutCommand());
        map.put(Operation.BET_INFO, new BetInfoCommand());
        map.put(Operation.REDIRECT_TO_SPORT_PAGE, new SportPageCommand());
        map.put(Operation.REDIRECT_TO_CYBERSPORT_PAGE, new CybersportPageCommand());
        map.put(Operation.DELETE_MATCH, new DeleteEventCommand());
        map.put(Operation.SORT_RESULTS_INFO, new SortResultsCommand());
        map.put(Operation.SHOW_ACCOUNT_BETS, new BetsCommand());
        map.put(Operation.CHANGE_PASSWORD, new ChangePasswordCommand());
        map.put(Operation.ADD_MATCH, new AddEventCommand());
        map.put(Operation.REDIRECT_TO_UPDATE, new UpdatePageCommand());
        map.put(Operation.EVENT, new EventCommand());
        map.put(Operation.REDIRECT_TO_MAIN_PAGE, new MainPageCommand());
        map.put(Operation.TRANSACTION, new TransactionCommand());
        map.put(Operation.INVOICE_INFO, new InvoiceDataCommand());
        map.put(Operation.OPEN_BET, new OpenBetCommand());
        map.put(Operation.MAKE_BET, new MakeBetCommand());
        map.put(Operation.ARRANGE_COEFFS, new ArrangeCoefficientsCommand());
        map.put(Operation.UPDATE_COEFFS, new UpdateCoefficientsCommand());
        map.put(Operation.PUT_RESULT_PAGE, new BringInResultsPageCommand());
        map.put(Operation.PUT_RESULTS, new BringInResultsCommand());
        map.put(Operation.RESULTS_PAGE, new ResultsPageCommand());
        map.put(Operation.REDIRECT_TO_INVOICE_STATUS, new InvoiceInfoCommand());
        map.put(Operation.ADMIN_TRANSACTIONS, new AdminTransactionsCommand());
        map.put(Operation.CANCEL_BET, new CancelBetCommand());
        map.put(Operation.EDIT_EVENT_PAGE, new RedactEventPageCommand());
        map.put(Operation.EDIT_MATCH, new RedactEventCommand());
        map.put(Operation.NO_COMMAND, new NoCommand());
        LOGGER.log(Level.INFO,"Put command in map");
    }

    public static ActionCommand takeActionCommand(String command){
        Operation operation= convert(command);
        LOGGER.log(Level.INFO,"Converted action command");
        if (operation== null) {
           operation = Operation.NO_COMMAND;
            LOGGER.log(Level.INFO,"No such command, redirect to login page");
        }
        return map.get(operation);
    }
}
package com.epam.totalizator.command.event;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.entity.Transaction;
import com.epam.totalizator.manager.ManagerHandler;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class InvoiceDataCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(InvoiceDataCommand.class.getName());

    static final String DEPOSIT = "deposit";
    static final String DRAWBACK= "drawback";
    static final String PARAM_INFO_TYPE = "info_type";
    static final String TRANSACTIONS = "transactions";

    public InvoiceDataCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        ArrayList<Transaction> result = null;
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        if(account != null && account.getRole() == AccountRole.CLIENT) {
            try {
                String infoType = request.getParameter(PARAM_INFO_TYPE);
                switch (infoType) {
                    case DEPOSIT:
                        result = ServiceFactory.getInstance().getInvoiceService().fetchAccountDeposits(account);
                        break;
                    case DRAWBACK:
                        result = ServiceFactory.getInstance().getInvoiceService().fetchAccountDrawback(account);
                        break;
                    default:
                        result = ServiceFactory.getInstance().getInvoiceService().fetchAllTransactions(account);
                }
            }catch (ServiceException e){
                LOGGER.error("Err in invoice inf command"+e);
            }
            page = ManagerHandler.getPage(ManagerHandler.CLIENT_BALANCE_INFO);
            request.setAttribute(TRANSACTIONS, result);
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(Level.INFO,"Success invoice descript comm");
        return page;
    }
}
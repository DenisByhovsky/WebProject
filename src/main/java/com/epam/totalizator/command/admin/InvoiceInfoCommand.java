package com.epam.totalizator.command.admin;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountInvoice;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.manager.ManagerHandler;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class InvoiceInfoCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(InvoiceInfoCommand.class.getName());

    static final String INVOICES = "invoices";
    static final String ACCOUNTS = "accounts";

    public InvoiceInfoCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Account account = (Account) request.getSession().getAttribute(ACCOUNT);
        if (account != null && account.getRole() == AccountRole.ADMIN) {
            page = ManagerHandler.getPage(ManagerHandler.INVOICE_STATUS);
            try {
                ArrayList<AccountInvoice> invoices = ServiceFactory.getInstance().getInvoiceService().fetchAdminInvoices();
                request.setAttribute(INVOICES, invoices);
                ArrayList<Account> accounts = ServiceFactory.getInstance().getAccountService().fetchAllAccounts();
                request.setAttribute(ACCOUNTS, accounts);
            } catch (ServiceException e) {
                LOGGER.error("Invoices stat error comm" +e);
            }
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(Level.INFO,"Sucess invoices stat comm");
        return page;
    }
}
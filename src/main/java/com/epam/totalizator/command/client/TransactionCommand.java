package com.epam.totalizator.command.client;

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
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class TransactionCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(TransactionCommand.class.getName());

    static final String TRANSACTION_TYPE = "transType";
    static final String DEPOSIT = "deposit";
    static final String DRAWBACK= "drawback";
    static final String TYPE = "type";
    static final String DEP_AMOUNT = "dep-amount";
    static final String TRANSACTION = "transaction";

    public TransactionCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(ACCOUNT);
        if (account != null && account.getRole() == AccountRole.CLIENT) {
            String type = request.getParameter(TYPE);
            BigDecimal amount = new BigDecimal(request.getParameter(DEP_AMOUNT));
            String transactionType = request.getParameter(TRANSACTION_TYPE);
            try {
                switch (transactionType) {
                    case DEPOSIT:
                        Transaction deposit = ServiceFactory.getInstance().getInvoiceService().makeDeposit(account, type, amount);
                        if (deposit != null) {
                            deposit.setTransactionType(transactionType);
                            session.setAttribute(TRANSACTION, deposit);
                            page = ManagerHandler.getPage(ManagerHandler.CLIENT_SUCCESS_PAGE);
                        }
                        break;
                    case DRAWBACK:
                        Transaction drawback = ServiceFactory.getInstance().getInvoiceService().drawback(account, type, amount);
                        if (drawback != null) {
                            drawback.setTransactionType(transactionType);
                            session.setAttribute(TRANSACTION, drawback);
                            page = ManagerHandler.getPage(ManagerHandler.CLIENT_SUCCESS_PAGE);
                        } else {
                            page = ManagerHandler.getPage(ManagerHandler.CLIENT_FAIL_PAGE);
                        }
                        break;
                }
            }catch (ServiceException e){
                LOGGER.error("Error in trans comm"+e);
            }
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        request.setAttribute(ActionCommand.DISPATCHER_TYPE, ActionCommand.SEND_REDIRECT);
        LOGGER.log(Level.INFO,"Succes trans comm");
        return page;
    }
}
package com.epam.totalizator.service.factory;

import com.epam.totalizator.service.*;
import com.epam.totalizator.service.impl.*;

/** Service Factory
 * Factory creates and returns  service object.
 */
public class ServiceFactory {

    private ServiceFactory() { }

    private static class ServiceFactoryHolder {
        private static final ServiceFactory instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.instance;
    }

    public AccountService getAccountService() {
        return new AccountServiceImpl();
    }

    public InvoiceService getInvoiceService() {
        return new InvoiceServiceImpl();
    }

    public BetService getBetService() {
        return new BetServiceImpl();
    }

    public EventService getEventService() {
        return new EventServiceImpl();
    }

    public ResultService getResultService() {
        return  new ResultServiceImpl();
    }

}
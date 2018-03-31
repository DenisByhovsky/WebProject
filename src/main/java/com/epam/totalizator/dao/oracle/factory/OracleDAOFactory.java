package com.epam.totalizator.dao.oracle.factory;

import com.epam.totalizator.dao.factory.DAOFactory;
import com.epam.totalizator.dao.interfaces.*;

/** OracleDAO Factory
 * Factory creates and returns dao objects
 * @author Denis Byhovsky
 */

public class OracleDAOFactory extends DAOFactory {

    private OracleDAOFactory(){}

    public static class OracleDAOFactoryHolder {
        private static final OracleDAOFactory instance = new OracleDAOFactory();
    }

    public static OracleDAOFactory getInstance() {
        return OracleDAOFactoryHolder.instance;
    }

    //TODO
    @Override
    public AccountDAO getAccountDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BetDAO getBetDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CoefficientDAO getCoefficientDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public InvoiceDAO getInvoiceDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public EventDAO getEventDAO() {
        throw new UnsupportedOperationException();
    }
}

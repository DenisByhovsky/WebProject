package com.epam.totalizator.dao.mysql.factory;

import com.epam.totalizator.dao.factory.DAOFactory;
import com.epam.totalizator.dao.interfaces.*;
import com.epam.totalizator.dao.mysql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** MySQLDAO Factory
 * Factory creates and returns dao objects
 * @author Denis Byhovsky
 */

public class MySQLDAOFactory extends DAOFactory {

    private static final Logger LOGGER = LogManager.getLogger(MySQLDAOFactory.class.getName());

    private MySQLDAOFactory(){}

    public static class MySQLDAOFactoryHolder {
        private static final MySQLDAOFactory instance = new MySQLDAOFactory();
    }

    public static MySQLDAOFactory getInstance() {
        return MySQLDAOFactoryHolder.instance;
    }

    @Override
    public AccountDAO getAccountDAO() {
        return  new MySQLAccountDAO(false);
    }

    @Override
    public BetDAO getBetDAO() { return new  MySQLBetDAO(false); }

    @Override
    public CoefficientDAO getCoefficientDAO() {
        return new  MySQLCoefficientDAO(false);
    }

    @Override
    public InvoiceDAO getInvoiceDAO() {
        return new MySQLInvoiceDAO(false);
    }

    @Override
    public EventDAO getEventDAO() {
        return  new MySQLEventDAO(false);
    }
}

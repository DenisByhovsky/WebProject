package com.epam.totalizator.dao.factory;

import com.epam.totalizator.dao.interfaces.*;
import com.epam.totalizator.dao.mysql.factory.MySQLDAOFactory;
import com.epam.totalizator.dao.oracle.factory.OracleDAOFactory;

/** DAO Factory
 * Factory creates and returns an instance of  MYSQL or ORACLE dao factory
 * Factory returns dao objects.
 * @author  Denis Byhovsky
 */

public abstract class DAOFactory {

    public static final int MYSQL= 1;
    public static final int ORACLE = 2;

    public abstract AccountDAO getAccountDAO();
    public abstract BetDAO getBetDAO();
    public abstract CoefficientDAO getCoefficientDAO();
    public abstract InvoiceDAO getInvoiceDAO();
    public abstract EventDAO getEventDAO();

    public static DAOFactory getDAOFactory(
            int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return  MySQLDAOFactory.getInstance();
            case ORACLE:
                return  OracleDAOFactory.getInstance();
            default:
                return null;
        }
    }
}
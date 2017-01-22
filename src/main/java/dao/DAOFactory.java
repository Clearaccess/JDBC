package dao;

import dao.oracleDAO.OracleDAOFactory;

/**
 * Created by Aleksandr_Vaniukov on 1/17/2017.
 */
public abstract class DAOFactory {
    public static final int ORACLE=1;
    public abstract UserDAO getUserDAO();
    public abstract ItemDAO getItemDAO();
    public abstract BidDAO getBidDAO();

    public static DAOFactory getDAOFactory(int whichFactory){
        switch(whichFactory){
            case ORACLE: return new OracleDAOFactory();
            default: return null;
        }
    }
}

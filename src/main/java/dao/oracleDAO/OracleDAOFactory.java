package dao.oracleDAO;

import dao.BidDAO;
import dao.DAOFactory;
import dao.ItemDAO;
import dao.UserDAO;
import dao.oracleDAO.OracleBidDAO;
import dao.oracleDAO.OracleItemDAO;
import dao.oracleDAO.OracleUserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Aleksandr_Vaniukov on 1/17/2017.
 */
public class OracleDAOFactory extends DAOFactory {

    private static final String DB_DRIVER="oracle.jdbc.OracleDriver";
    private static final String DB_URL="jdbc:oracle:thin:@localhost:1521/xe";
    private static final String DB_USER="ALEXANDER";
    private static final String DB_PASSWORD="440276";

    static{
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection createConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        return conn;
    }

    public UserDAO getUserDAO(){
        return new OracleUserDAO();
    }

    public ItemDAO getItemDAO(){
        return new OracleItemDAO();
    }

    public BidDAO getBidDAO(){
        return new OracleBidDAO();
    }
}

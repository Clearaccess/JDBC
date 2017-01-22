package dao.oracleDAO;

import dao.DAOFactory;
import dao.UserDAO;
import to.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr_Vaniukov on 1/17/2017.
 */
public class OracleUserDAO implements UserDAO {

    public void insert(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "INSERT INTO Users(user_Id,full_name,billing_Address,login,password) VALUES(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, user.getUserId());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getBillingAddress());
            ps.setString(4, user.getLogin());
            ps.setString(5, user.getPassword());
            ps.executeUpdate();
        } finally {
            ps.close();
            conn.close();
        }
    }

    public void update(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "UPDATE Users SET full_name=?,billing_Address=?,login=?,password=? WHERE user_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getBillingAddress());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());
            ps.setLong(5, user.getUserId());
            ps.executeUpdate();
        } finally {
                ps.close();
                conn.close();
        }
    }

    public void delete(User user) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "DELETE FROM Users WHERE user_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, user.getUserId());
            ps.executeUpdate();
        } finally {
                ps.close();
                conn.close();
        }
    }

    public User getById(long id) throws SQLException {

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
            conn= OracleDAOFactory.createConnection();
            String sql="SELECT * FROM Users WHERE user_Id=?";
            ps=conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs=ps.executeQuery();
            rs.next();

            User user=new User();

            user.setUserId(rs.getLong("user_Id"));
            user.setFullName(rs.getString("full_Name"));
            user.setBillingAddress(rs.getString("billing_Address"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));

            return user;

        } finally {
                rs.close();
                ps.close();
                conn.close();
        }
    }

    public User getByLogin(String login) throws SQLException {

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
            conn= OracleDAOFactory.createConnection();
            String sql="SELECT * FROM Users WHERE login=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,login);
            rs=ps.executeQuery();
            rs.next();

            User user=new User();

            user.setUserId(rs.getLong("user_Id"));
            user.setFullName(rs.getString("full_Name"));
            user.setBillingAddress(rs.getString("billing_Address"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));

            return user;

        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
    }

    public ArrayList<User> getAll() throws SQLException {

        ArrayList<User>listUsers=new ArrayList<User>();
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;

        try{
            conn= OracleDAOFactory.createConnection();
            String sql="SELECT * FROM Users";
            st=conn.createStatement();
            rs=st.executeQuery(sql);

            while(rs.next()){
                User user=new User();
                user.setUserId(rs.getLong("user_Id"));
                user.setFullName(rs.getString("full_Name"));
                user.setBillingAddress(rs.getString("billing_Address"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                listUsers.add(user);
            }

            return listUsers;

        } finally {
                rs.close();
                st.close();
                conn.close();
        }
    }
}

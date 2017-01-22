package dao.oracleDAO;

import dao.ItemDAO;
import to.Item;
import to.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Aleksandr_Vaniukov on 1/17/2017.
 */
public class OracleItemDAO implements ItemDAO {
    public void insert(Item item) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "INSERT INTO Items(item_Id, seller_Id, title, description, start_Price, time_Left, start_Bidding_Date, buy_It_Now, bid_Increment) VALUES(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, item.getItemId());
            ps.setLong(2, item.getSellerId());
            ps.setString(3, item.getTitle());
            ps.setString(4, item.getDescription());
            ps.setDouble(5, item.getStartPrice());
            ps.setLong(6, item.getTimeLeft());
            ps.setDate(7, item.getStartBiddingDate());
            ps.setBoolean(8, item.isBuyItNow());
            ps.setDouble(9, item.getBidIncrement());
            ps.executeUpdate();
        } finally {
                ps.close();
                conn.close();
        }
    }

    public void update(Item item) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "UPDATE Items SET seller_Id=?, title=?, description=?, start_Price=?, time_Left=?, start_Bidding_Date=?, buy_It_Now=?, bid_Increment=? WHERE item_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, item.getSellerId());
            ps.setString(2, item.getTitle());
            ps.setString(3, item.getDescription());
            ps.setDouble(4, item.getStartPrice());
            ps.setLong(5, item.getTimeLeft());
            ps.setDate(6, item.getStartBiddingDate());
            ps.setBoolean(7, item.isBuyItNow());
            ps.setDouble(8, item.getBidIncrement());
            ps.setLong(9, item.getItemId());
            ps.executeUpdate();
        } finally {
                ps.close();
                conn.close();
        }
    }

    public void delete(Item item) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "DELETE FROM Items WHERE item_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, item.getItemId());
            ps.executeUpdate();
        } finally {
                ps.close();
                conn.close();
        }
    }

    public Item getById(long id) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "SELECT * FROM Items WHERE item_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs=ps.executeQuery();
            rs.next();

            Item item=new Item();

            item.setItemId(rs.getLong("item_Id"));
            item.setSellerId(rs.getLong("seller_Id"));
            item.setTitle(rs.getString("title"));
            item.setDescription(rs.getString("description"));
            item.setTimeLeft(rs.getLong("time_Left"));
            item.setStartPrice(rs.getDouble("start_Price"));
            item.setStartBiddingDate(rs.getDate("start_Bidding_Date"));
            item.setBuyItNow(rs.getBoolean("buy_It_Now"));
            item.setBidIncrement(rs.getDouble("bid_Increment"));

            return item;

        } finally {
                rs.close();
                ps.close();
                conn.close();
        }
    }

    public ArrayList<Item> getItemsBySubstr(String substr) throws SQLException {

        ArrayList<Item>listItems=new ArrayList<Item>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "SELECT * FROM Items WHERE title LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+substr+"%");
            rs=ps.executeQuery();

            while(rs.next()){

                Item item=new Item();

                item.setItemId(rs.getLong("item_Id"));
                item.setSellerId(rs.getLong("seller_Id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setTimeLeft(rs.getLong("time_Left"));
                item.setStartPrice(rs.getDouble("start_Price"));
                item.setStartBiddingDate(rs.getDate("start_Bidding_Date"));
                item.setBuyItNow(rs.getBoolean("buy_It_Now"));
                item.setBidIncrement(rs.getDouble("bid_Increment"));

                listItems.add(item);
            }

            return listItems;

        } finally {
                rs.close();
                ps.close();
                conn.close();
        }
    }

    public ArrayList<Item> getItemsBySellerId(long id) throws SQLException {

        ArrayList<Item>listItemsOfSeller=new ArrayList<Item>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "SELECT * FROM Items WHERE seller_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs=ps.executeQuery();

            while(rs.next()){

                Item item=new Item();

                item.setItemId(rs.getLong("item_Id"));
                item.setSellerId(rs.getLong("seller_Id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setTimeLeft(rs.getLong("time_Left"));
                item.setStartPrice(rs.getDouble("start_Price"));
                item.setStartBiddingDate(rs.getDate("start_Bidding_Date"));
                item.setBuyItNow(rs.getBoolean("buy_It_Now"));
                item.setBidIncrement(rs.getDouble("bid_Increment"));

                listItemsOfSeller.add(item);
            }

            return listItemsOfSeller;

        } finally {
                rs.close();
                ps.close();
                conn.close();
        }
    }

    public ArrayList<Item> getAll() throws SQLException {

        ArrayList<Item> listItems=new ArrayList<Item>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "SELECT * FROM Items";
            st = conn.createStatement();
            rs=st.executeQuery(sql);

            while(rs.next()){

                Item item=new Item();

                item.setItemId(rs.getLong("item_Id"));
                item.setSellerId(rs.getLong("seller_Id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setTimeLeft(rs.getLong("time_Left"));
                item.setStartPrice(rs.getDouble("start_Price"));
                item.setStartBiddingDate(rs.getDate("start_Bidding_Date"));
                item.setBuyItNow(rs.getBoolean("buy_It_Now"));
                item.setBidIncrement(rs.getDouble("bid_Increment"));

                listItems.add(item);
            }

            return listItems;

        } finally {
                rs.close();
                st.close();
                conn.close();
        }
    }
}

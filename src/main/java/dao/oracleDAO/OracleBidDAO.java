package dao.oracleDAO;

import dao.BidDAO;
import to.Bid;
import to.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr_Vaniukov on 1/17/2017.
 */
public class OracleBidDAO implements BidDAO {
    public void insert(Bid bid) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "INSERT INTO Bids(bid_Id,bidder_Id,item_Id,bid) VALUES(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, bid.getBidId());
            ps.setLong(2, bid.getBidderId());
            ps.setLong(3, bid.getItemId());
            ps.setDouble(4, bid.getBid());
            ps.executeUpdate();
        } finally {
                ps.close();
                conn.close();
        }
    }

    public void update(Bid bid) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "UPDATE Bids SET bidder_Id=?, item_Id=?, bid=? WHERE bid_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, bid.getBidderId());
            ps.setLong(2, bid.getItemId());
            ps.setDouble(3, bid.getBid());
            ps.setLong(4, bid.getBidId());
            ps.executeUpdate();
        } finally {
                ps.close();
                conn.close();
        }
    }

    public void delete(Bid bid) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "DELETE FROM Bids WHERE bid_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, bid.getBidId());
            ps.executeUpdate();
        } finally {
                ps.close();
                conn.close();
        }
    }

    public void deleteByItemID(long id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "DELETE FROM Bids WHERE item_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } finally {
            ps.close();
            conn.close();
        }
    }

    public Bid getById(long id) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql = "SELECT * FROM Bids WHERE bid_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            rs.next();
            Bid bid = new Bid();
            bid.setBidId(rs.getLong("bid_Id"));
            bid.setBidderId(rs.getLong("bidder_Id"));
            bid.setItemId(rs.getLong("item_Id"));
            bid.setBid(rs.getDouble("bid"));

            return bid;
        } finally {
                rs.close();
                ps.close();
                conn.close();
        }
    }

    public ArrayList<Bid> getAll() throws SQLException {

        ArrayList<Bid> listBids=new ArrayList<Bid>();
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql="SELECT * FROM Bids";
            st=conn.createStatement();
            rs=st.executeQuery(sql);

            while (rs.next()) {
                Bid bid=new Bid();
                bid.setBidId(rs.getLong("bid_Id"));
                bid.setBidderId(rs.getLong("bidder_Id"));
                bid.setItemId(rs.getLong("item_Id"));
                bid.setBid(rs.getDouble("bid"));
                listBids.add(bid);
            }

            return listBids;
        } finally {
                rs.close();
                st.close();
                conn.close();
        }
    }

    public ArrayList<Bid> getAllByItemId(long id) throws SQLException {

        ArrayList<Bid> listBids=new ArrayList<Bid>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            conn = OracleDAOFactory.createConnection();
            String sql="SELECT * FROM Bids WHERE item_Id=?";
            ps=conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs=ps.executeQuery();

            while (rs.next()) {
                Bid bid=new Bid();
                bid.setBidId(rs.getLong("bid_Id"));
                bid.setBidderId(rs.getLong("bidder_Id"));
                bid.setItemId(rs.getLong("item_Id"));
                bid.setBid(rs.getDouble("bid"));
                listBids.add(bid);
            }

            return listBids;
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
    }
}

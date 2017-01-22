package dao;

import to.Bid;
import to.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr_Vaniukov on 1/17/2017.
 */
public interface BidDAO {
    void insert(Bid bid) throws SQLException;
    void update(Bid bid) throws SQLException;
    void delete(Bid bid) throws SQLException;
    void deleteByItemID(long id) throws SQLException;
    Bid getById(long id) throws SQLException;
    ArrayList<Bid> getAll() throws SQLException;
    ArrayList<Bid> getAllByItemId(long id) throws SQLException;
}

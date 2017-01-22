package dao;

import to.Item;
import to.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Aleksandr_Vaniukov on 1/17/2017.
 */
public interface ItemDAO {
    void insert(Item item) throws SQLException;
    void update(Item item) throws SQLException;
    void delete(Item item) throws SQLException;
    Item getById(long id) throws SQLException;
    ArrayList<Item> getItemsBySubstr(String substr) throws SQLException;
    ArrayList<Item> getItemsBySellerId(long userId) throws SQLException;
    ArrayList<Item> getAll() throws SQLException;
}

package dao;

import to.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr_Vaniukov on 1/17/2017.
 */
public interface UserDAO {

    void insert(User user) throws SQLException;
    void update(User user) throws SQLException;
    void delete(User user) throws SQLException;
    User getById(long id) throws SQLException;
    User getByLogin(String login) throws SQLException;
    ArrayList<User> getAll() throws SQLException;
}

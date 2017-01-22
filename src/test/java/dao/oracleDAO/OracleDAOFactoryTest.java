package dao.oracleDAO;

import dao.BidDAO;
import dao.DAOFactory;
import dao.ItemDAO;
import dao.UserDAO;
import org.junit.Before;
import org.junit.Test;
import to.Bid;
import to.Item;
import to.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Aleksandr_Vaniukov on 1/18/2017.
 */
public class OracleDAOFactoryTest {

    /*
    @Before
    public void init(){

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        BidDAO bidDAO=oracleDAO.getBidDAO();
        ItemDAO itemDAO=oracleDAO.getItemDAO();
        UserDAO userDAO=oracleDAO.getUserDAO();

    }*/

    @Test
    public void getConnect(){
        try {
            OracleDAOFactory.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void getData(){
        try {
            Connection conn=OracleDAOFactory.createConnection();
            Statement st=conn.createStatement();
            assertThat(st.execute("SELECT * FROM Employees"), is(true));
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetAllUsers() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        UserDAO userDAO = oracleDAO.getUserDAO();
        ArrayList<User> users = null;

        try {
            users = userDAO.getAll();

            ArrayList<User> testUsers = new ArrayList<User>();
            testUsers.add(new User(1, "fullName1", "address1", "login1", "password1"));
            testUsers.add(new User(2, "fullName2", "address2", "login2", "password2"));
            testUsers.add(new User(3, "fullName3", "address3", "login3", "password3"));

            assertTrue(users.containsAll(testUsers));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateNewUser() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        UserDAO userDAO = oracleDAO.getUserDAO();
        User testUser=new User(10,"fullName10", "address10", "login10", "password10");

        try {
            userDAO.insert(testUser);
            ArrayList<User> users = userDAO.getAll();
            assertTrue(users.contains(testUser));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                userDAO.delete(testUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testExistUser() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        UserDAO userDAO = oracleDAO.getUserDAO();
        User testUser = new User(4, null, null, "login", "password");

        try {
            userDAO.insert(testUser);
            assertTrue(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                userDAO.delete(testUser);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

    @Test
    public void testGetUserByLogin() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        UserDAO userDAO = oracleDAO.getUserDAO();
        User testUser = new User(4, null, null, "login", "password");

        try {
            User user=userDAO.getByLogin("login");
            assertTrue(user.equals(testUser));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetUserByLoginNotExist() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        UserDAO userDAO = oracleDAO.getUserDAO();
        User testUser = new User(4, null, null, "login", "password");

        try {
            User user=userDAO.getByLogin("login0");
            assertTrue(user.equals(testUser));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetChangeUser() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        UserDAO userDAO = oracleDAO.getUserDAO();

        try {
            User user=userDAO.getByLogin("login");
            user.setFullName("fullName4");
            user.setBillingAddress("address4");
            userDAO.update(user);
            User testUser=userDAO.getByLogin("login");
            assertTrue(user.equals(testUser));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllItems() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        ItemDAO itemDAO = oracleDAO.getItemDAO();

        ArrayList<Item>testItems=new ArrayList<Item>();
        testItems.add(new Item(1,1,"title1","description1",100.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0));
        testItems.add(new Item(2,1,"title2","description2",200.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0));
        testItems.add(new Item(3,2,"title3","description3",300.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0));
        testItems.add(new Item(4,2,"title4","description4",400.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0));

        try {
            ArrayList<Item> items=itemDAO.getAll();
            assertTrue(items.containsAll(testItems));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetAllItemsBySubstr() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        ItemDAO itemDAO = oracleDAO.getItemDAO();

        ArrayList<Item>testItems=new ArrayList<Item>();
        testItems.add(new Item(1,1,"title1","description1",100.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0));
        testItems.add(new Item(2,1,"title2","description2",200.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0));
        testItems.add(new Item(3,2,"title3","description3",300.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0));
        testItems.add(new Item(4,2,"title4","description4",400.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0));

        try {
            ArrayList<Item> items=itemDAO.getItemsBySubstr("itl");
            assertTrue(items.containsAll(testItems));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetItemsById() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        ItemDAO itemDAO = oracleDAO.getItemDAO();

        Item testItem=new Item(1,1,"title1","description1",100.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0);

        try {
            Item item=itemDAO.getById(1);
            assertTrue(item.equals(testItem));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetItemsBySellerId() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        ItemDAO itemDAO = oracleDAO.getItemDAO();

        ArrayList<Item>testItems=new ArrayList<Item>();
        testItems.add(new Item(1,1,"title1","description1",100.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0));
        testItems.add(new Item(2,1,"title2","description2",200.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0));

        try {
            ArrayList<Item> items=itemDAO.getItemsBySellerId(1);
            assertTrue(items.containsAll(testItems));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCreateNewItem() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        ItemDAO itemDAO = oracleDAO.getItemDAO();
        Item testItem=new Item(5,1,"title5","description5",500.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0);

        try {

            itemDAO.insert(testItem);
            ArrayList<Item>items=itemDAO.getAll();
            assertTrue(items.contains(testItem));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                itemDAO.delete(testItem);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testGetNewItem() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        ItemDAO itemDAO = oracleDAO.getItemDAO();
        Item testItem=new Item(5,1,"title5","description5",500.0,0,new Date(new GregorianCalendar(2017,0,1).getTimeInMillis()),true,0.0);

        try {

            itemDAO.insert(testItem);
            ArrayList<Item>items=itemDAO.getAll();
            assertTrue(items.contains(testItem));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                itemDAO.delete(testItem);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testGetAllBidsForItem() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        BidDAO bidDAO = oracleDAO.getBidDAO();

        try {
            ArrayList<Bid>testBids=new ArrayList<Bid>();
            testBids.add(new Bid(1,1,1,100));
            testBids.add(new Bid(2,2,1,200));
            ArrayList<Bid>bids=bidDAO.getAllByItemId(1);
            assertTrue(bids.containsAll(testBids));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDeleteItem() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        ItemDAO itemDAO = oracleDAO.getItemDAO();

        try {
            Item item=itemDAO.getById(1);
            itemDAO.delete(item);
            ArrayList<Item>items = itemDAO.getAll();
            assertTrue(!items.contains(item));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateBid() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        BidDAO bidDAO= oracleDAO.getBidDAO();
        Bid testBid=new Bid(5,1,1,100);

        try {
            bidDAO.insert(testBid);
            ArrayList<Bid>bids = bidDAO.getAllByItemId(1);
            assertTrue(bids.contains(testBid));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                bidDAO.delete(testBid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGetBid() {

        DAOFactory oracleDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        BidDAO bidDAO= oracleDAO.getBidDAO();

        try {
            ArrayList<Bid>testBids=new ArrayList<Bid>();
            testBids.add(new Bid(1,1,1,100));
            testBids.add(new Bid(2,2,1,200));
            testBids.add(new Bid(3,3,1,300));
            testBids.add(new Bid(4,4,1,400));
            ArrayList<Bid>bids = bidDAO.getAllByItemId(1);
            Bid bid= Collections.max(bids);
            assertTrue(testBids.contains(bid));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


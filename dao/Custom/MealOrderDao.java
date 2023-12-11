package dao.Custom;

import dao.CrudDao;

import entity.Meal;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MealOrderDao extends CrudDao<Order,String> {
    public ArrayList<Meal>setMealDetails(String id) throws SQLException;
    public ResultSet loadMealId() throws SQLException;
    public ResultSet loadCustomerId() throws SQLException;
    public ResultSet OrderId() throws Exception;
    public ResultSet checkQty(String id) throws Exception;
    public ArrayList<Order>loadAllOrder() throws SQLException;

}

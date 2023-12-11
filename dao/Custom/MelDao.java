package dao.Custom;

import dao.CrudDao;
import entity.Customer;
import entity.Meal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MelDao extends CrudDao<Meal,String> {
    public ArrayList<Meal> searchMeals(String searchText) throws SQLException;
    public ResultSet autoGenerateId() throws SQLException;
}

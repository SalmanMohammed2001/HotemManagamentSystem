package dao.Custom.impl;

import dao.CrudUtil;
import dao.Custom.MelDao;
import entity.Meal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MelDaoImpl implements MelDao {
    @Override
    public boolean save(Meal meal) throws SQLException {
        return CrudUtil.execute("INSERT INTO Meal VALUES(?,?,?,?)",
               meal.getId(), meal.getName(),meal.getUnitePrice(),meal.getQtyOnHand());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Meal WHERE id=?",id);
    }

    @Override
    public boolean update(Meal meal) throws SQLException {
        return CrudUtil.execute("UPDATE Meal SET name=?,unitePrice=?,qtyOnHand =? where id=?",
                meal.getName(),meal.getUnitePrice(),meal.getQtyOnHand(),meal.getId());
    }

    @Override
    public ArrayList<Meal> searchMeals(String searchText) throws SQLException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Meal WHERE id like ?|| name like?",
                searchText,searchText);
        ArrayList<Meal>arrayList=new ArrayList<>();
        while (set.next()){
            arrayList.add(new Meal(
                    set.getString(1),
                    set.getString(2),
                    set.getDouble(3),
                    set.getInt(4)
            ));
        }
        return  arrayList;

    }

    @Override
    public ResultSet autoGenerateId() throws SQLException {


        ResultSet resultSet = CrudUtil.execute("select  id   from Meal order by  id   desc limit 1");
        return resultSet;

    }
}

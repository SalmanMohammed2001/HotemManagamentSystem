package dao.Custom.impl;

import dao.CrudUtil;
import dao.Custom.MealOrderDao;
import entity.Meal;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MealOrderDaoImpl implements MealOrderDao {
    @Override
    public boolean save(Order o) throws SQLException {
       return CrudUtil.execute(" insert into `Order` values(?,?,?,?)",o.getOrderId(),o.getDate(),o.getCustomer(),o.getTotalCost());
    }

    @Override
    public boolean delete(String searchText) throws SQLException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Order order) throws SQLException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<Meal> setMealDetails(String id) throws SQLException {
        ArrayList<Meal> arrayList = new ArrayList<>();
        ResultSet set = CrudUtil.execute("select * from Meal where id=?", id);
        while (set.next()) {
            arrayList.add(new Meal(
                    set.getString(2),
                    set.getDouble(3),
                    set.getInt(4)

            ));
        }
        return arrayList;
    }

    @Override
    public ResultSet loadMealId() throws SQLException {
        ResultSet set=CrudUtil.execute("select * from  Meal");
        return set;
    }

    @Override
    public ResultSet loadCustomerId() throws SQLException {
        ResultSet set=CrudUtil.execute("select * from Customer");
        return set;
    }

    @Override
    public ResultSet OrderId() throws Exception {
//        " SELECT id FROM`Order` ORDER BY id DESC LIMIT 1"
       return CrudUtil.execute("select  id   from `Order` order by  id   desc limit 1");

    }

    @Override
    public ResultSet checkQty(String id) throws Exception {
        ResultSet set=CrudUtil.execute("select    qtyOnHand from Meal where id=?",id);
        return set;
    }

    @Override
    public ArrayList<Order> loadAllOrder() throws SQLException {
        ArrayList<Order> arrayList=new ArrayList<>();
        ResultSet set=CrudUtil.execute("select * from `Order`");
        while (set.next()){
            arrayList.add(new Order(
                    set.getString(1),
                    new Date(),
                    set.getString(3 ),
                    set.getDouble(4)
            ));

        }
        return arrayList;
    }
}



package dao.Custom.impl;

import dao.CrudUtil;
import dao.Custom.MealOrderDetailsDao;
import entity.ItemDetails;

import java.sql.SQLException;

public class MealOrderDetailsDaoImpl implements MealOrderDetailsDao {
    @Override
    public boolean save(ItemDetails i) throws SQLException {
      return CrudUtil.execute("insert into `Order Details` values(?,?,?,?)",i.getId(),i.getOrderId(),i.getUnitePrice(),i.getQty()) ;
    }

    @Override
    public boolean delete(String searchText) throws SQLException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(ItemDetails i) throws SQLException {
        return CrudUtil.execute("update Meal set qtyOnHand=(qtyOnHand-?)WHERE id=?",i.getQty(),i.getId());
    }
}

package bo.Custom;

import dto.*;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MealOrderBo {
    public ArrayList<MealDto> setMealDetails(String id) throws SQLException;
    public ResultSet loadMealId() throws SQLException;
    public ResultSet loadCustomerId() throws SQLException;
    public ResultSet OrderId() throws Exception;
    public ResultSet checkQty(String id) throws Exception;
    public ArrayList<OrderDto>loadAllOrder() throws SQLException;

    public boolean MealOrderSave(OrderDto o, ArrayList<ItemDetailsDto> details) throws SQLException;


}

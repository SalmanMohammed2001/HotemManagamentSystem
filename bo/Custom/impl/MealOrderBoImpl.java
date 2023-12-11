package bo.Custom.impl;

import bo.Custom.MealOrderBo;
import dao.Custom.MealOrderDao;
import dao.Custom.MealOrderDetailsDao;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.ItemDetailsDto;
import dto.MealDto;
import dto.OrderDto;
import entity.ItemDetails;
import entity.Meal;
import entity.Order;
import javafx.scene.control.Alert;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MealOrderBoImpl implements MealOrderBo {

    private MealOrderDao mealOrderDao= DaoFactory.getInstance().getDao(DaoType.MEALORDER);
    private MealOrderDetailsDao mealOrderDetailsDao=DaoFactory.getInstance().getDao(DaoType.MEALORDERDETAILS);
    @Override
    public ArrayList<MealDto> setMealDetails(String id) throws SQLException {
        ArrayList<Meal>arrayList=mealOrderDao.setMealDetails(id);
        ArrayList<MealDto>dtoArrayList=new ArrayList<>();
        for (Meal m:arrayList){
            dtoArrayList.add(new MealDto(
                  m.getName(),
                  m.getUnitePrice(),
                  m.getQtyOnHand()
            ));
        }
        return dtoArrayList;
    }

    @Override
    public ResultSet loadMealId() throws SQLException {
        return mealOrderDao.loadMealId();
    }

    @Override
    public ResultSet loadCustomerId() throws SQLException {
        return mealOrderDao.loadCustomerId();
    }

    @Override
    public ResultSet OrderId() throws Exception {
        return mealOrderDao.OrderId();
    }

    @Override
    public ResultSet checkQty(String id) throws Exception {
        return mealOrderDao.checkQty(id);
    }

    @Override
    public ArrayList<OrderDto> loadAllOrder() throws SQLException {
        ArrayList<Order>arrayList=mealOrderDao.loadAllOrder();
        ArrayList<OrderDto>dtoArrayList=new ArrayList<>();
        for(Order o:arrayList){
            dtoArrayList.add(new OrderDto(
                    o.getOrderId(),
                    o.getDate(),
                    o.getCustomer(),
                    o.getTotalCost()
            ));

        }
        return dtoArrayList;
    }

    @Override
    public boolean MealOrderSave(OrderDto o, ArrayList<ItemDetailsDto> details) throws SQLException {

        Connection con = null;
        try {

            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);


            boolean isOrderSaved = mealOrderDao.save(new Order(o.getOrderId(), o.getDate(), o.getCustomer(), o.getTotalCost()));
            if (isOrderSaved) {
                boolean isAllUpdate = manageQty(details);
                if (isAllUpdate) {
                    con.commit();
                    new Alert(Alert.AlertType.INFORMATION, "Order places").show();
                    // jesperReport();
                    // clearAll();

                } else {
                    con.setAutoCommit(true);
                    con.rollback();
                    new Alert(Alert.AlertType.WARNING, "try again").show();

                }
            } else {
                con.setAutoCommit(true);
                con.rollback();
                new Alert(Alert.AlertType.WARNING, "tryy again").show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.setAutoCommit(true);
        }

        return false;

    }

    private boolean manageQty(ArrayList<ItemDetailsDto> details) throws SQLException {


            for (ItemDetailsDto d : details
            ) {


                boolean isOrderDetailsSaved = mealOrderDetailsDao.save(new ItemDetails(d.getId(),d.getOrderId(),d.getUnitePrice(),d.getQty()));
                if (isOrderDetailsSaved) {
                    boolean isQtyUpdate = update(d);
                    if (!isQtyUpdate) {
                        return false;

                    }

                } else {
                    return false;
                }


            }


        return true;
    }

    private boolean update(ItemDetailsDto d) throws SQLException {

            return mealOrderDetailsDao.update(new ItemDetails(d.getQty(), d.getId()));

    }

}

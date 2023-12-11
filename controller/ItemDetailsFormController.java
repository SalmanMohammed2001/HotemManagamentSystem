package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.QueryBo;
import dao.Custom.QueryDao;
import dao.Custom.impl.QueryDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.CustomDto;
import entity.Custom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.ItemDetailsTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemDetailsFormController {
    public AnchorPane root;
    public TableView tblItem;
    public TableColumn colCode;
    public TableColumn colUnitePrice;
    public TableColumn colQty;
    public TableColumn ColTotal;

    private QueryBo queryBo= BoFactory.getInstance().getBo(BoType.QUERY);

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ColTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    public void btnBackToHomeAction(ActionEvent actionEvent) {


    }


    public void loadOrder(String orderId) {
        try{
            ObservableList<ItemDetailsTm> oblist= FXCollections.observableArrayList();

      /*      String sql="select o.id,d.MealId,d.orderId,d.unitePrice,d.qty from `Order` o INNER JOIN `Order Details` d ON o.id=d.orderId AND o.id=? ";
            PreparedStatement statement= DBConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1,orderId);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                double tempunitePrice=set.getDouble(4);
                int temQty=set.getInt(5);
                double total=tempunitePrice*temQty;

                oblist.add(new ItemDetailsTm(set.getString(2),tempunitePrice,temQty,total));



            }*/

            ArrayList<CustomDto> arrayList = queryBo.loadItemJoinQuery(orderId);
            for (CustomDto c:arrayList){
                double tempunitePrice=c.getUnitePrice();
                int temQty=c.getQty();
                double total=tempunitePrice*temQty;

                oblist.add(new ItemDetailsTm(c.getId(),tempunitePrice,temQty,total));
            }
            tblItem.setItems(oblist);
            return;


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

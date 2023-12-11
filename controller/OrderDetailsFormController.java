package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.MealOrderBo;
import dao.Custom.MealOrderDao;
import dao.Custom.impl.MealOrderDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.OrderDto;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.OrderTm;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class OrderDetailsFormController {
    public AnchorPane root;
    public TableView tblOrders;
    public TableColumn colOrderId;
    public TableColumn colDate;
    public TableColumn colCustomer;
    public TableColumn colTotalCost;
    public TableColumn ColOption;

   MealOrderBo mealOrderBo= BoFactory.getInstance().getBo(BoType.MEALORDER);


    public void initialize(){

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        ColOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loaOrderDetails();
    }

    private void loaOrderDetails()  {

        try {
            ObservableList<OrderTm>obList= FXCollections.observableArrayList();

            ArrayList<OrderDto> arrayList = mealOrderBo.loadAllOrder();
            for (OrderDto o:arrayList){
                Button btn=new Button("Item Details");
                OrderTm tm=new OrderTm(
                        o.getOrderId(),
                        o.getDate(),
                        o.getCustomer(),
                        o.getTotalCost(),
                        btn);

                obList.add(tm);
                btn.setOnAction(event -> {
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/ItemDetailsForm.fxml"));
                        Parent parent=loader.load();
                        ItemDetailsFormController controller =loader.getController();
                        controller.loadOrder(tm.getOrderId());
                        Stage stage=new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


            }tblOrders.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnBackToHomeAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"))));

    }
}

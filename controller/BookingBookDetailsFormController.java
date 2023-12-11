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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.BookingDetailsTm;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookingBookDetailsFormController {

    public AnchorPane root;
    public TableView tblItem;
    public TableColumn colRoomId;
    public TableColumn colAvailability;
    public TableColumn colPrice;
    public TableColumn ColDayConut;
    public TableColumn colTotal;

    private QueryBo queryBo= BoFactory.getInstance().getBo(BoType.QUERY);
    //private QueryDao queryDao=DaoFactory.getInstance().getDao(DaoType.QUERY);

    public void initialize(){
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("RoomId"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        ColDayConut.setCellValueFactory(new PropertyValueFactory<>("dayCount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }



    public void BookingBookDetails(String id){

        try{
            ObservableList<BookingDetailsTm> oblist= FXCollections.observableArrayList();
            ArrayList<CustomDto> arrayList = queryBo.loadBookingJoinQuery(id);
            for (CustomDto c:arrayList){
                double tempPrice=c.getPrice();
                int temDayCount = c.getDayCount();
                double total=temDayCount*tempPrice;

                oblist.add(new BookingDetailsTm(c.getRoomId(),c.getAvailability(),tempPrice,temDayCount,total));

            }

            tblItem.setItems(oblist);
            return;


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void btnBackToHomeAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"))));
    }
}

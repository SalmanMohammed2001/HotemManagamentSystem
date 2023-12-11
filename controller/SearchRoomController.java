package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.RoomBo;
import dao.Custom.RoomDao;
import dao.Custom.impl.RoomDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.RoomDto;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.SearchRoomTm;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchRoomController {


    public TextField txtSearchRoom;
    public TableView<SearchRoomTm> tblRoom;
    public TableColumn colId;
    public TableColumn colRoomType;
    public TableColumn colAvaliable;
    public TableColumn colCleaned;
    public TableColumn colPrice;
    public AnchorPane root;

    private String searchRoom="";

    RoomBo roomBo= BoFactory.getInstance().getBo(BoType.ROOM);

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colAvaliable.setCellValueFactory(new PropertyValueFactory<>("available"));
        colCleaned.setCellValueFactory(new PropertyValueFactory<>("cleaned"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        loardSearchRoom(searchRoom);

        txtSearchRoom.textProperty().addListener((observable, oldValue, newValue) -> {
            searchRoom=newValue;
            loardSearchRoom(searchRoom);
        });

    }

    private void loardSearchRoom(String text) {
        String searchText="%"+text+"%";

        try {
            ObservableList<SearchRoomTm>obList= FXCollections.observableArrayList();

            ArrayList<RoomDto> arrayList = roomBo.searchRoom(searchText);
            for (RoomDto r:arrayList){
                SearchRoomTm tm=new SearchRoomTm(
                        r.getId(),
                        r.getRoomType(),
                        r.getAvailable(),
                        r.getCleaned(),
                        r.getPrice()
                );obList.add(tm);

            }tblRoom.setItems(obList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void backAdminDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"))));
    }
}

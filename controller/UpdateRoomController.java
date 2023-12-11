package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.RoomBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Regex;
import util.TextFields;
import view.tm.SearchRoomTm;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateRoomController {

    public AnchorPane root;
    public JFXTextField txtId;
    public JFXButton saveRoom;
    public TextField txtSearchRoom;
    public TableView<SearchRoomTm> tblRoom;
    public TableColumn colId;
    public TableColumn colRoomType;
    public TableColumn colCleaned;
    public TableColumn colAvailable;
    public TableColumn colPrice;
    public ComboBox<String> cmbType;
    public JFXTextField txtPrice;
    public ComboBox<String> cmbAvailable;
    public ComboBox<String> cmbCleaned;

    private String searchRoom = "";



    private RoomBo roomBo= BoFactory.getInstance().getBo(BoType.ROOM);

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));
        colCleaned.setCellValueFactory(new PropertyValueFactory<>("cleaned"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        bedType();
        roomAvailable();
        cleaningStatus();

        loardSearchRoom(searchRoom);


        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                setDetails(newValue);
            }
        });

        txtSearchRoom.textProperty().addListener((observable, oldValue, newValue) -> {
            searchRoom = newValue;
            loardSearchRoom(searchRoom);
        });

    }

    private void setDetails(SearchRoomTm tm) {
        txtId.setText(tm.getId());
        cmbType.setValue(tm.getRoomType());
        cmbAvailable.setValue(tm.getAvailable());
        cmbCleaned.setValue(tm.getCleaned());
        txtPrice.setText(String.valueOf(tm.getPrice()));
    }

    private void loardSearchRoom(String text) {

        String searchText = "%" + text + "%";

        try {
            ObservableList<SearchRoomTm> obList = FXCollections.observableArrayList();


            ArrayList<RoomDto> arrayList = roomBo.searchRoom(searchText);
            for (RoomDto r : arrayList) {
                SearchRoomTm tm = new SearchRoomTm(
                        r.getId(),
                        r.getRoomType(),
                        r.getAvailable(),
                        r.getCleaned(),
                        r.getPrice()
                );
                obList.add(tm);


            }
            tblRoom.setItems(obList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void backAdminDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"))));
    }

    public void bedType() {
        ArrayList<String> roomType = new ArrayList<>();
        roomType.add("Double bed");
        roomType.add("Single bead");
        ObservableList<String> obList = FXCollections.observableArrayList(roomType);
        cmbType.setItems(obList);
    }

    public void roomAvailable() {
        ArrayList<String> roomAvailable = new ArrayList<>();
        roomAvailable.add("Available");
        roomAvailable.add("Occupied");
        ObservableList<String> obList = FXCollections.observableArrayList(roomAvailable);
        cmbAvailable.setItems(obList);

    }

    public void cleaningStatus() {
        ArrayList<String> cleaningStatus = new ArrayList<>();
        cleaningStatus.add("Cleaned");
        cleaningStatus.add("Dirty");
        ObservableList<String> obList = FXCollections.observableArrayList(cleaningStatus);
        cmbCleaned.setItems(obList);

    }

    public void btnSubmitOnAction(ActionEvent actionEvent) {
        if (txtPrice.getText().isEmpty()) return;
        if (cmbType.getValue().isEmpty()) return;
        if (cmbCleaned.getValue().isEmpty()) return;
        if (cmbAvailable.getValue().isEmpty()) return;

        boolean salary = Regex.isTextFieldValid(TextFields.DOUBLE, txtPrice.getText());
        if (!salary) {
            new Alert(Alert.AlertType.WARNING, "Invalid salary").show();
            return;
        }


        try {
           /* String sql="UPDATE Room SET  bedType=?, availability=?,cleanStatus=?,price=? WHERE id=?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1,cmbType.getValue());
            statement.setString(2, cmbAvailable.getValue());
            statement.setString(3,cmbCleaned.getValue());
            statement.setDouble(4, Double.parseDouble(txtPrice.getText()));
            statement.setString(5,txtId.getText());
            int add = statement.executeUpdate();*/

            boolean add = roomBo.updateRoom(new RoomDto(cmbType.getValue(), cmbAvailable.getValue(), cmbCleaned.getValue(), Double.parseDouble(txtPrice.getText()), txtId.getText()));
            if (add) {
                clearField();
                loardSearchRoom(searchRoom);
                new Alert(Alert.AlertType.INFORMATION, "Room update").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void clearField() {
        txtId.clear();
        txtPrice.clear();
        cmbType.setValue(null);
        cmbCleaned.setValue(null);
        cmbAvailable.setValue(null);

    }


    public void PriceKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE, txtPrice);
        System.out.println(Regex.isTextFieldValid(TextFields.DOUBLE, txtPrice.getText()));
    }
}

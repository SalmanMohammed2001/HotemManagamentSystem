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
import view.tm.RoomTm;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class AddRoomFormController {
    public AnchorPane root;
    public JFXTextField txtId;
    public ComboBox<String> cmbType;
    public JFXTextField txtPrice;
    public ComboBox<String> cmbCleaned;
    public ComboBox<String> cmbAvailable;
    public TableView<RoomTm> tblRoom;
    public TableColumn colId;
    public TableColumn colRoomType;
    public TableColumn colAvaliable;
    public TableColumn colCleaned;
    public TableColumn colPrice;
    public TableColumn colOption;
    public JFXButton saveRoom;
    public TextField txtSearchRoom;

   // private RoomDao roomDao= DaoFactory.getInstance().getDao(DaoType.ROOM);

    private RoomBo roomBo= BoFactory.getInstance().getBo(BoType.ROOM);
   private String searchRoom="";





    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colAvaliable.setCellValueFactory(new PropertyValueFactory<>("available"));
        colCleaned.setCellValueFactory(new PropertyValueFactory<>("cleaned"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        bedType();
        roomAvailable();
        cleaningStatus();

        autoGenerateId();

        searchRoom(searchRoom);

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if(null!=newValue) {
               setValue(newValue);
           }
        });
        txtSearchRoom.textProperty().addListener((observable, oldValue, newValue) -> {
            searchRoom=newValue;
            searchRoom(searchRoom);
        });




    }
    public void setValue(RoomTm tm){
        txtId.setText(tm.getId());
       cmbType.setValue(tm.getRoomType());
       cmbAvailable.setValue(tm.getAvailable());
       cmbCleaned.setValue(tm.getCleaned());
        txtPrice.setText(String.valueOf(tm.getPrice()));
        saveRoom.setText("Update Room");

    }

    public void backAdminDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashBoardForm.fxml"))));


    }
    public void btnSubmitOnAction(ActionEvent actionEvent) {
        if(txtPrice.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid Price");
            alert.show();
            return;
        }
        if(cmbType.getValue().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid Room");
            alert.show();
            return;
        }
        if(cmbCleaned.getValue().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid cleaned Status");
            alert.show();
            return;
        }
        if(cmbAvailable.getValue().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid Available");
            alert.show();
            return;
        }

        boolean salary = Regex.isTextFieldValid(TextFields.DOUBLE,txtPrice.getText());
        if (!salary) {
            new Alert(Alert.AlertType.WARNING, "Invalid salary").show();
            return;
        }

        if(saveRoom.getText().equalsIgnoreCase("Save Room")){

            try {
               boolean isSave=roomBo.saveRoom(
                       new RoomDto(txtId.getText(),cmbType.getValue(),cmbAvailable.getValue(),cmbCleaned.getValue(),
                               Double.parseDouble(txtPrice.getText()))
               );

                if(isSave){
                   searchRoom(searchRoom);
                    clearField();
                    new Alert(Alert.AlertType.INFORMATION,"Room Added").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {

            try {
                boolean isUpdate=roomBo.updateRoom(
                        new RoomDto(txtId.getText(),cmbType.getValue(),cmbAvailable.getValue(),cmbCleaned.getValue(),
                                Double.parseDouble(txtPrice.getText()))
                );


                if(isUpdate){
                    searchRoom(searchRoom);
                    clearField();
                    new Alert(Alert.AlertType.INFORMATION,"Room update").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();

                }



            } catch (SQLException e) {
                e.printStackTrace();
            }

        }





    }

    private void searchRoom(String text) {
        String searchText="%"+text+"%";


        try {
            ObservableList<RoomTm>obList=FXCollections.observableArrayList();
            ArrayList<RoomDto> arrayList=roomBo.searchRoom(searchText);

            for (RoomDto r:arrayList){
                Button btn=new Button("Delete");
                RoomTm tm=new RoomTm(
                        r.getId(),
                        r.getRoomType(),
                         r.getAvailable(),
                         r.getCleaned(),
                         r.getPrice()
                         ,btn

                );
                obList.add(tm);
              btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Do you want delete",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if(buttonType.get()==ButtonType.YES) {

                        try {

                            if(roomBo.deleteRoom(tm.getId())){
                                searchRoom(searchRoom);
                                clearField();

                                new Alert(Alert.AlertType.CONFIRMATION, "Delete Success").show();

                            } else {
                                new Alert(Alert.AlertType.WARNING, "try again").show();
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                });

            }tblRoom.setItems(obList);



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void AddNewItem(ActionEvent actionEvent) {
        saveRoom.setText("save Room");
        clearField();
    }

    public void bedType(){
        ArrayList<String> roomType=new ArrayList<>();
        roomType.add("Double bed");
        roomType.add("Single bead");
        ObservableList<String>obList= FXCollections.observableArrayList(roomType);
        cmbType.setItems(obList);
    }

    public void roomAvailable(){
        ArrayList<String> roomAvailable=new ArrayList<>();
        roomAvailable.add("Available");
        roomAvailable.add("Occupied");
        ObservableList<String>obList= FXCollections.observableArrayList(roomAvailable);
        cmbAvailable.setItems(obList);

    }
    public void cleaningStatus(){
        ArrayList<String> cleaningStatus=new ArrayList<>();
        cleaningStatus.add("clean");
        cleaningStatus.add("Dirty");
        ObservableList<String>obList= FXCollections.observableArrayList(cleaningStatus);
        cmbCleaned.setItems(obList);

    }

    public void clearField(){
        txtId.clear();
        txtPrice.clear();
        cmbType.setValue(null);
        cmbCleaned.setValue(null);
        cmbAvailable.setValue(null);
        autoGenerateId();
    }
    public  void autoGenerateId(){


        try {
            Connection connection = DBConnection.getInstance().getConnection();
           /* Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select  id   from Room order by  id   desc limit 1");*/
            RoomDaoImpl roomDao = new RoomDaoImpl();
            ResultSet resultSet=roomDao.autoGenerateId();
            boolean next = resultSet.next();
            if(next){
                String userId = resultSet.getString(1);
                userId= userId.substring(1,userId.length());
                int intId = Integer.parseInt(userId);
                intId++;
                if(intId<10){
                    txtId.setText("R00"+intId);

                }else if(intId<100){
                    txtId.setText("R0"+intId);

                }else
                    txtId.setText("R"+intId);



            }else{
                txtId.setText("R001");


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void txtPriceKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtPrice);
        System.out.println(Regex.isTextFieldValid(TextFields.DOUBLE,txtPrice.getText()));
    }
}

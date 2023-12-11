package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.EmployeeBo;
import dao.Custom.EmployeeDao;
import dao.Custom.impl.EmployeeDaoImpl;
import db.DBConnection;
import dto.EmployeeDto;
import entity.Employee;
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
import view.tm.EmployeeInfoTm;
import view.tm.SearchRoomTm;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeInfoController {
    public TableView tblEmployee;
    public TableColumn colEmployeeId;
    public TableColumn colName;
    public TableColumn colIdNumber;
    public TableColumn colEmail;
    public TableColumn colMobileNumber;
    public TableColumn colAge;
    public TableColumn colJob;
    public TableColumn colSalary;
    public AnchorPane root;
    public TextField txtSerachEmployee;
    private String searchText="";

    EmployeeBo employeeBo= BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    public void initialize(){

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Ename"));
        colIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colJob.setCellValueFactory(new PropertyValueFactory<>("job"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        loadEmployeeInfo(searchText);

        txtSerachEmployee.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            loadEmployeeInfo(searchText);

        });

    }

    private void loadEmployeeInfo(String text) {
        String searchText="%"+text+"%";

        try {

            ObservableList<EmployeeInfoTm> obList= FXCollections.observableArrayList();


            ArrayList<EmployeeDto> arrayList = employeeBo.searchEmployee(searchText);
            for (EmployeeDto e:arrayList){
                EmployeeInfoTm tm=new EmployeeInfoTm(
                       e.getId(),
                        e.getEname(),
                        e.getIdNumber(),
                        e.getEmail(),
                        e.getJob(),
                        e.getNumber(),
                        e.getAge(),
                        e.getSalary()

                );obList.add(tm);

            }tblEmployee.setItems(obList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void backDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"))));
    }
}

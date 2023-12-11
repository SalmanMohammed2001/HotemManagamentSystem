package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.CustomerBo;
import dao.Custom.CustomerDao;
import dao.Custom.impl.CustomerDaoImpl;
import dao.DaoFactory;
import db.DBConnection;
import dto.CustomerDto;
import entity.Customer;
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
import view.tm.CustomerDetailsTm;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerInfoController {
    public AnchorPane root;
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCountry;
    public TableColumn colEmail;
    public TableColumn colMobileNo;
    public TableColumn colAge;
    public TableColumn colGender;
    public TextField txtSearchCustomer;
    private String searchText="";


    private CustomerBo customerBo= BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));


        loadCustomerDetails(searchText);


        txtSearchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            loadCustomerDetails(searchText);

        });
    }

    private void loadCustomerDetails(String text) {
        String searchText="%"+text+"%";


        try {
            ObservableList<CustomerDetailsTm>obList= FXCollections.observableArrayList();


            ArrayList<CustomerDto> arrayList = customerBo.searchCustomer(searchText);
            for (CustomerDto c:arrayList){
                CustomerDetailsTm tm=new CustomerDetailsTm(
                       c.getId(),
                        c.getName(),
                        c.getCountry(),
                        c.getEmail(),
                        c.getMobileNo(),
                        c.getAge(),
                        c.getGender()

                );obList.add(tm);
            }tblCustomer.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"))));
    }
}

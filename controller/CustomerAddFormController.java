package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.CustomerBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Custom.CustomerDao;
import dao.Custom.impl.CustomerDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.CustomerDto;
import entity.Customer;
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
import view.tm.CustomerTm;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerAddFormController {
    public AnchorPane root;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtXCarModel;
    public JFXTextField txtEmaill;
    public JFXTextField txtMobileNumber;
    public JFXButton saveDriver;
    public TextField txtSearchDriver;
    public TableView<CustomerTm> tblCustomer;
    public ComboBox<String> cmbGender;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCarModel;
    public TableColumn colEmail;
    public TableColumn colMobileNumber;
    public TableColumn colAge;
    public TableColumn colGender;
    public TableColumn colOption;
    public JFXButton saveCustomer;
    public TextField txtSearchCustomer;
    public JFXTextField txtAge;
    public JFXTextField txtMobileNo;
    public JFXTextField txtCountry;
    public TableColumn colCountry;
    public TableColumn colMobileNo;
    private String searchCustomer = "";

    // private CustomerDao customerDao= DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);


    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


        setDate();

        autoGenerateId();

        searchCustomer(searchCustomer);

        loadGender();

        txtSearchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
            searchCustomer = newValue;
            searchCustomer(searchCustomer);

        });


        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                loeadCustomerDetails(newValue);
            }
        });
    }

    private void setDate() {

    }

    private void loeadCustomerDetails(CustomerTm tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtCountry.setText(tm.getCountry());
        txtEmaill.setText(tm.getEmail());
        txtMobileNo.setText(tm.getMobileNo());
        txtAge.setText(tm.getAge());
        cmbGender.setValue(tm.getGender());
        saveCustomer.setText("update Customer");
    }

    private void loadGender() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Male");
        arrayList.add("Female");
        ObservableList<String> obList = FXCollections.observableArrayList(arrayList);
        cmbGender.setItems(obList);
    }

    public void backAdminDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"))));
    }


    public void btnAddNewCustomer(ActionEvent actionEvent) {

        saveCustomer.setText("save Customer");
        clearField();
    }

    public void btnSaveCustomer(ActionEvent actionEvent) {
        if(txtAge.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid age");
           alert.show();
           return;

        };
        if(txtMobileNo.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid Mobile No");
            alert.show();
            return;
        }
        if(txtCountry.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid Country No");
            alert.show();
            return;
        }
        if(txtName.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid name");
            alert.show();
            return;
        }
        if(txtEmaill.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid Email");
            alert.show();
            return;
        }
        if(cmbGender.getValue().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid gender");
            alert.show();
            return;
        }


        boolean mobile = Regex.isTextFieldValid(TextFields.PHONE, txtMobileNo.getText());
        if (!mobile) {
            new Alert(Alert.AlertType.WARNING, "Invalid Mobile No").show();
            return;
        }
        boolean name = Regex.isTextFieldValid(TextFields.NAME, txtName.getText());
        if (!name) {
            new Alert(Alert.AlertType.WARNING, "Invalid Name").show();
            return;
        }
        boolean country = Regex.isTextFieldValid(TextFields.NAME, txtCountry.getText());
        if (!country) {
            new Alert(Alert.AlertType.WARNING, "Invalid Country").show();
            return;
        }

        boolean email = Regex.isTextFieldValid(TextFields.EMAIL, txtEmaill.getText());
        if (!email) {
            new Alert(Alert.AlertType.WARNING, "Invalid Email").show();
            return;
        }
        boolean age = Regex.isTextFieldValid(TextFields.INTEGER, txtAge.getText());
        if (!age) {
            new Alert(Alert.AlertType.WARNING, "Invalid age").show();
            return;
        }

        if (saveCustomer.getText().equalsIgnoreCase("save Customer")) {
            try {
                boolean isAdd = customerBo.saveCustomer(
                        new CustomerDto(txtId.getText(), txtName.getText(), txtCountry.getText(), txtEmaill.getText(),
                                txtMobileNo.getText(), txtAge.getText(), cmbGender.getValue())
                );
                if (isAdd) {
                    searchCustomer(searchCustomer);
                    clearField();
                    new Alert(Alert.AlertType.INFORMATION, "Customer Added").show();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else {


            try {
                boolean isUpdate = customerBo.updateCustomer(
                        new CustomerDto(txtId.getText(), txtName.getText(), txtCountry.getText(), txtEmaill.getText(),
                                txtMobileNo.getText(), txtAge.getText(), cmbGender.getValue())
                );

                if (isUpdate) {
                    clearField();
                    searchCustomer(searchCustomer);
                    new Alert(Alert.AlertType.INFORMATION, "Customer Update").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again").show();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    private void searchCustomer(String text) {
        String searchText = "%" + text + "%";


        try {
            ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
            ArrayList<CustomerDto> arrayList = customerBo.searchCustomer(searchText);

            for (CustomerDto c : arrayList) {
                Button btn = new Button("delete");
                CustomerTm tm = new CustomerTm(
                        c.getId(),
                        c.getName(),
                        c.getCountry(),
                        c.getEmail(),
                        c.getMobileNo(),
                        c.getAge(),
                        c.getGender()

                        , btn
                );
                obList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want delete", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get() == ButtonType.YES) {

                        try {


                            if (customerBo.deleteCustomer(tm.getId())) {
                                searchCustomer(searchCustomer);
                                new Alert(Alert.AlertType.CONFIRMATION, "Delete Success").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try again").show();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void autoGenerateId() {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
         /*   Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select  id   from Customer order by  id   desc limit 1");
*/

           ResultSet resultSet= customerBo.autoGenerateIdCustomer();
            boolean next1 = resultSet.next();

            if (next1) {
                String userId = resultSet.getString(1);

                userId = userId.substring(1, userId.length());
                int intId = Integer.parseInt(userId);

                intId++;

                if (intId < 10) {
                    txtId.setText("00" + intId);

                } else if (intId < 100) {
                    txtId.setText("0" + intId);

                } else
                    txtId.setText("0" + intId);


            } else {
                txtId.setText("001");


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void clearField() {
        txtId.clear();
        txtName.clear();
        txtAge.clear();
        txtCountry.clear();
        txtMobileNo.clear();
        txtEmaill.clear();
        cmbGender.setValue(null);

        autoGenerateId();
    }

    public void txtNameKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME, txtName);
        System.out.println(Regex.isTextFieldValid(TextFields.NAME, txtName.getText()));
    }

    public void txtCountryKeyOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME, txtCountry);

        System.out.println(Regex.isTextFieldValid(TextFields.NAME, txtCountry.getText()));
    }

    public void txtEmaillKeyReleadedOnActiion(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EMAIL, txtEmaill);
        System.out.println(Regex.isTextFieldValid(TextFields.EMAIL, txtCountry.getText()));
    }

    public void txtMobileNoKeyReleadedOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PHONE, txtMobileNo);
        System.out.println(Regex.isTextFieldValid(TextFields.PHONE, txtMobileNo.getText()));
    }

    public void txtAgekeyRelasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INTEGER, txtAge);
        System.out.println(Regex.isTextFieldValid(TextFields.INTEGER, txtAge.getText()));
    }
}

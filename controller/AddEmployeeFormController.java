package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.EmployeeBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dao.Custom.EmployeeDao;
import dao.Custom.impl.CustomerDaoImpl;
import dao.Custom.impl.EmployeeDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.EmployeeDto;
import entity.Employee;
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

import rex.utils.S;
import util.Regex;
import util.TextFields;
import view.tm.EmployeeTm;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class AddEmployeeFormController {
    public JFXRadioButton rBtnFemale;
    public AnchorPane incomesForAdminContext;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtIdNumber;
    public JFXTextField txtEmaill;
    public JFXTextField txtNumber;
    public JFXTextField txtSalary;
    public JFXTextField txtAge;
    public JFXTextField txtJob;
    public TableView<EmployeeTm> tblEmployee;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colIdNumber;
    public TableColumn colEmail;
    public TableColumn colJob;
    public TableColumn colMobileNumber;
    public TableColumn colSalary;
    public TableColumn colAge;
    public TableColumn colOption;
    public AnchorPane root;
    public JFXTextField txtEmployeId;
    public TableColumn colEmployeeId;
    public TextField txtSearchItem;
    public JFXButton saveItem;

  //  private EmployeeDao employeeDao=DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);

    private EmployeeBo employeeBo= BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    private String searchText="";

    public void initialize(){
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Ename"));
        colIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colJob.setCellValueFactory(new PropertyValueFactory<>("job"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        searchItem(searchText);

        autoGenerateId();

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue){
                setValue(newValue);
            }
        });

        txtSearchItem.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            searchItem(searchText);
        });


    }

    private void setValue(EmployeeTm tm) {
        txtEmployeId.setText(tm.getId());
        txtName.setText(tm.getEname());
        txtIdNumber.setText(tm.getIdNumber());
        txtEmaill.setText(tm.getEmail());
        txtJob.setText(tm.getJob());
        txtNumber.setText(tm.getNumber());
        txtAge.setText(tm.getAge());
        txtSalary.setText(String.valueOf(tm.getSalary()));
        saveItem.setText("update Employee");


    }

    private void searchItem(String text) {
        String searchText="%"+text+"%";


        try {
            ObservableList<EmployeeTm> obList= FXCollections.observableArrayList();

            ArrayList<EmployeeDto>arrayList=employeeBo.searchEmployee(searchText);
            for (EmployeeDto e:arrayList){

                Button btn=new Button("Delete");
                EmployeeTm tm=new EmployeeTm(
                        e.getId(),
                        e.getEname(),
                        e.getIdNumber(),
                        e.getEmail(),
                        e.getJob(),
                        e.getNumber(),
                        e.getAge(),
                        e.getSalary(),btn
                );

                obList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Do you want delete",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get()==ButtonType.YES){

                        try {
                            if(employeeBo.deleteEmployee(tm.getId())){
                                searchItem(searchText);
                                autoGenerateId();
                                new Alert(Alert.AlertType.INFORMATION,"Delete Success").show();
                            }else {
                                new Alert(Alert.AlertType.WARNING,"Try again").show();
                            }

                        } catch (SQLException s) {
                            s.printStackTrace();
                        }
                    }

                });

            }  tblEmployee.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }





    }

    public void btnBackToHomeAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashBoardForm.fxml"))));




    }

    public void AddNewItem(ActionEvent actionEvent) {
        saveItem.setText("Save Employee");
        clearItem();


    }

    public void saveItemOnAction(ActionEvent actionEvent) {
        if(txtName.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid name");
            alert.show();
            return;
        }
        if(txtIdNumber.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid number");
            alert.show();
            return;
        }
        if(txtAge.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"age gender");
            alert.show();
            return;
        }
        if(txtSalary.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid salary");
            alert.show();
            return;
        }
        if(txtJob.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid job");
            alert.show();
            return;
        }
        if(txtNumber.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid Number");
            alert.show();
            return;
        }
        if(txtEmaill.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid Email");
            alert.show();
            return;
        }

        boolean mobile = Regex.isTextFieldValid(TextFields.PHONE, txtNumber.getText());
        if (!mobile) {
            new Alert(Alert.AlertType.WARNING, "Invalid Mobile No").show();
            return;
        }

       boolean name = Regex.isTextFieldValid(TextFields.NAME, txtName.getText());
        if (!name) {
            new Alert(Alert.AlertType.WARNING, "Invalid Name").show();
            return;
        }

        boolean email = Regex.isTextFieldValid(TextFields.EMAIL, txtEmaill.getText());
        if (!email) {
            new Alert(Alert.AlertType.WARNING, "Invalid Email").show();
            return;
        }

        boolean salary = Regex.isTextFieldValid(TextFields.DOUBLE,txtSalary.getText());
        if (!email) {
            new Alert(Alert.AlertType.WARNING, "Invalid salary").show();
            return;
        }

     /*   boolean idNumber= Regex.isTextFieldValid(TextFields.ID,txtIdNumber.getText());
        if (!idNumber) {
            new Alert(Alert.AlertType.WARNING, "Invalid Id Number").show();
            return;
        }*/

        boolean age= Regex.isTextFieldValid(TextFields.INTEGER,txtAge.getText());
        if (!age) {
            new Alert(Alert.AlertType.WARNING, "Invalid Age").show();
            return;
        }


        if(saveItem.getText().equalsIgnoreCase("Save Employee")){

            try {
              boolean isSaved=  employeeBo.saveEmployee(
                        new EmployeeDto(
                                txtEmployeId.getText(),txtName.getText(),txtIdNumber.getText(),
                                txtEmaill.getText(),txtJob.getText(),txtNumber.getText(),txtAge.getText(),Double.parseDouble(txtSalary.getText())

                        )
                );
                if(isSaved){
                    searchItem(searchText);
                    clearItem();
                    new Alert(Alert.AlertType.INFORMATION,"Employee Added").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            try {

                boolean isUpdate =employeeBo.updateEmployee(
                        new EmployeeDto(
                                txtEmployeId.getText(),txtName.getText(),txtIdNumber.getText(),
                                txtEmaill.getText(),txtJob.getText(),txtNumber.getText(),txtAge.getText(),Double.parseDouble(txtSalary.getText())

                        )
                );

                if(isUpdate){
                    searchItem(searchText);
                    clearItem();
                    new Alert(Alert.AlertType.INFORMATION,"Customer Update").show();
                }else {
                    new Alert(Alert.AlertType.WARNING,"Try again").show();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }

    private void clearItem() {
        txtEmployeId.clear();
        txtName.clear();
        txtIdNumber.clear();
        txtEmaill.clear();
        txtJob.clear();
        txtNumber.clear();
        txtAge.clear();
        txtSalary.clear();

        autoGenerateId();
    }

    public void btnSalaryOnAction(ActionEvent actionEvent) {
    }
    public  void autoGenerateId(){


        try {
           /* Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select  eId   from Employee order by  eId   desc limit 1");*/

            EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
           ResultSet resultSet= employeeDao.autoGenerateId();
            boolean next = resultSet.next();
            if(next){
                String userId = resultSet.getString(1);

                userId= userId.substring(1,userId.length());
                int intId = Integer.parseInt(userId);

                intId++;

                if(intId<10){
                    txtEmployeId.setText("E00"+intId);

                }else if(intId<100){
                    txtEmployeId.setText("E0"+intId);

                }else
                    txtEmployeId.setText("E"+intId);



            }else{
                txtEmployeId.setText("E001");


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void txtNumberReleseOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PHONE,txtNumber);
        System.out.println(Regex.isTextFieldValid(TextFields.PHONE,txtNumber.getText()));
    }

    public void txtAgeKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INTEGER,txtAge);
        System.out.println(Regex.isTextFieldValid(TextFields.INTEGER,txtNumber.getText()));
    }


    public void txtSalarykeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtSalary);
        System.out.println(Regex.isTextFieldValid(TextFields.INTEGER,txtSalary.getText()));
    }

    public void txtNameKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);
        System.out.println(Regex.isTextFieldValid(TextFields.INTEGER,txtSalary.getText()));
    }

    public void txtIdNumberKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ID,txtName);
        System.out.println(Regex.isTextFieldValid(TextFields.ID,txtIdNumber.getText()));
    }

    public void txtEmaillKeyReleasedOnAvtion(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EMAIL,txtEmaill);
        System.out.println(Regex.isTextFieldValid(TextFields.EMAIL,txtEmaill.getText()));

    }

    public void txtJobkeyReleasedOnAction(KeyEvent keyEvent) {
    }
}



package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.AdminRegisterBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.CrudDao;
import dao.Custom.AdminRegisterDao;
import dao.Custom.impl.AdminRegisterDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.AdminRegisterDto;
import entity.AdminRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;



public class AdminRegistarFormController {

    public JFXTextField txtUserName;
    public JFXTextField txtEmail;
    public JFXTextField txtPassword;
    public JFXTextField txtConfirmPssword;
    public JFXButton btnRegisterId;
    public Label lblPasswordDoesNotMatch1;
    public Label lblPasswordDoesNotMatch2;
    public Label isEmpty;
    public Label lblId;
    public AnchorPane root;

   // CrudDao<AdminRegister,String> crudDao=new AdminRegisterDaoImpl();

    private AdminRegisterBo adminRegisterBo= BoFactory.getInstance().getBo(BoType.ADMIN);

    public void initialize(){
        setVisibility(false);
        isDisableCommon(true);

    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        register();

    }

    private void clear() {
        txtPassword.clear();
        txtConfirmPssword.clear();

        txtPassword.requestFocus();
    }

    public void setBorderColor(String color){
        txtPassword.setStyle("-fx-border-color:"+color);
        txtConfirmPssword.setStyle("-fx-border-color:"+color);

    }

    public void setVisibility(boolean isVisible){{
        lblPasswordDoesNotMatch1.setVisible(isVisible);
        lblPasswordDoesNotMatch2.setVisible(isVisible);

    }

    }

    public void btnAddNewUser(ActionEvent actionEvent) {
        isDisableCommon(false);
        txtUserName.requestFocus();


        autoGanarateId();

    }

    private void autoGanarateId() {



        try {



            ResultSet set=adminRegisterBo.autoGenerateIdAdmin();;
            boolean aa=  set.next();
            if(aa){
                String userId = set.getString(1);

                userId= userId.substring(1,userId.length());
                int intId = Integer.parseInt(userId);

                intId++;

                if(intId<10){
                    lblId.setText("A00"+intId);

                }else if(intId<100){
                    lblId.setText("A0"+intId);

                }else
                    lblId.setText("A"+intId);

            }else {
              lblId.setText("A001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void isDisableCommon(boolean isDisable){
        txtUserName.setDisable(isDisable);
        txtEmail.setDisable(isDisable);
        txtPassword.setDisable(isDisable);
        txtConfirmPssword.setDisable(isDisable);
        btnRegisterId.setDisable(isDisable);

    }

    public void txtConfirmPasswordOnAction(ActionEvent actionEvent) {
        register();

    }


    public void register(){
        String password = txtPassword.getText();
        String conPassword = txtConfirmPssword.getText();

        if(password.equals(conPassword)){
            setBorderColor("transparent");
            setVisibility(false);

            String id = lblId.getText();
            String name = txtUserName.getText();
            String email = txtEmail.getText();



            try {

              boolean  save=  adminRegisterBo.saveAdmin(new AdminRegisterDto(id,name,email,conPassword));

                if(save){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION,"Admin Register Success");
                    alert.showAndWait();


                    Stage window = (Stage) root.getScene().getWindow();
                    window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashBoardForm.fxml"))));


                }


            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }








        }


    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashBoardForm.fxml"))));
    }
}

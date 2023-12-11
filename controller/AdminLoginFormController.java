package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.AdminRegisterBo;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginFormController {

    public AnchorPane root;
    public JFXPasswordField txtPassword;
    public JFXTextField txtUserNameId;
    public Label lblError;

   // AdminRegisterDao registerDao=new AdminRegisterDaoImpl();

  //  private AdminRegisterDao adminRegisterDao= DaoFactory.getInstance().getDao(DaoType.ADMIN);

    private AdminRegisterBo adminRegisterBo= BoFactory.getInstance().getBo(BoType.ADMIN);

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        login();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        login();

    }
    public void login(){

        String name = txtUserNameId.getText();
        String password = txtPassword.getText();



        try {


           ResultSet resultSet=adminRegisterBo.loginAdmin(new AdminRegisterDto(name,password));

            boolean next = resultSet.next();

            if(next){
                Parent parent = FXMLLoader.load(getClass().getResource("../view/AdminDashBoardForm.fxml"));
                Scene scene=new Scene(parent);
                Stage stage= (Stage) root.getScene().getWindow();

                stage.setScene(scene);
                stage.setTitle("AdminDashBoardForm");
                stage.centerOnScreen();

            }else {
                Alert alert=new Alert(Alert.AlertType.ERROR,"ERROR");
                alert.showAndWait();

                txtUserNameId.clear();
                txtPassword.clear();
                txtUserNameId.requestFocus();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }







}

package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.ReceptionRegisterBo;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.CrudUtil;
import dao.Custom.ReceptionRegisterDao;
import dao.Custom.impl.ReceptionRegisterDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.ReceptionistRegisterDto;
import entity.ReceptionistRegister;
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

public class ReceptionistLoginFormController {
    public AnchorPane root;
    public JFXPasswordField txtPassword;
    public JFXTextField txtUserNameId;
    public Label lblError;

    private  ReceptionRegisterBo receptionRegisterBo= BoFactory.getInstance().getBo(BoType.RECEPTIONIST);


    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
    }


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        login();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        login();

    }

    public void login() {

        String name = txtUserNameId.getText();
        String password = txtPassword.getText();

        Connection connection = DBConnection.getInstance().getConnection();

        try {


            ResultSet resultSet = receptionRegisterBo.loginReception(new ReceptionistRegisterDto(name, password));

            boolean next = resultSet.next();

            if (next) {


                Parent parent = FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("ToDoForm");
                stage.centerOnScreen();


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR");
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

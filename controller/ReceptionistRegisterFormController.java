package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.ReceptionRegisterBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Custom.ReceptionRegisterDao;
import dao.Custom.impl.ReceptionRegisterDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.ReceptionistRegisterDto;
import entity.ReceptionistRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ReceptionistRegisterFormController {
    public AnchorPane root;
    public AnchorPane btnAddNewUser;
    public JFXTextField txtUserName;
    public JFXTextField txtEmail;
    public JFXTextField txtPassword;
    public JFXTextField txtConfirmPssword;
    public JFXButton btnRegisterId;
    public Label lblPasswordDoesNotMatch1;
    public Label lblPasswordDoesNotMatch2;
    public Label lblId;



     private ReceptionRegisterBo receptionRegisterBo= BoFactory.getInstance().getBo(BoType.RECEPTIONIST);

    public void initialize() {
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

    public void setBorderColor(String color) {
        txtPassword.setStyle("-fx-border-color:" + color);
        txtConfirmPssword.setStyle("-fx-border-color:" + color);

    }

    public void setVisibility(boolean isVisible) {
        {
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

            ResultSet resultSet = receptionRegisterBo.autoGenerateIdReception();
            boolean next = resultSet.next();

            if (next) {
                String userId = resultSet.getString(1);

                userId = userId.substring(1, userId.length());
                int intId = Integer.parseInt(userId);

                intId++;

                if (intId < 10) {
                    lblId.setText("R00" + intId);

                } else if (intId < 100) {
                    lblId.setText("R0" + intId);

                } else
                    lblId.setText("R" + intId);

            } else {
                lblId.setText("R001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void isDisableCommon(boolean isDisable) {
        txtUserName.setDisable(isDisable);
        txtEmail.setDisable(isDisable);
        txtPassword.setDisable(isDisable);
        txtConfirmPssword.setDisable(isDisable);
        btnRegisterId.setDisable(isDisable);

    }

    public void txtConfirmPasswordOnAction(ActionEvent actionEvent) {
        register();

    }


    public void register() {
        String password = txtPassword.getText();
        String conPassword = txtConfirmPssword.getText();

        if (password.equals(conPassword)) {
            setBorderColor("transparent");
            setVisibility(false);

            String id = lblId.getText();
            String name = txtUserName.getText();
            String email = txtEmail.getText();

            Connection connection = DBConnection.getInstance().getConnection();

            try {
                boolean add = receptionRegisterBo.saveReception(new ReceptionistRegisterDto(id, name, email, conPassword));

                if (add) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Admin Register Success");
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
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashBoardForm.fxml"))));
    }
}

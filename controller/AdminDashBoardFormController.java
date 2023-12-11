package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class AdminDashBoardFormController {
    public AnchorPane root;
    public Label lblDate;
    public Label lblTime;

    public void initialize(){
        loadDate();
        loadTime();
    }

    private void loadTime() {
        final DateFormat format=DateFormat.getDateInstance();
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> {

            lblTime.setText(LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("HH:mm:ss")
            ));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void loadDate() {
        lblDate.setText(new SimpleDateFormat("yyy-MM-dd").format(new Date()));

    }



    public void AdminRegisterFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AdminRegistarForm");

    }

    public void btnReceptionistRegisterFormOnAction(ActionEvent actionEvent) throws IOException {
       setUi("ReceptionistRegisterForm");


    }

    public void btnLougot(ActionEvent actionEvent) throws IOException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Do you want to Logout", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if(buttonType.get().equals(ButtonType.YES)) {
           setUi("AdminLoginForm");
        }

    }

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddEmployeeForm");
    }

    public void btnAddRoomOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddRoomForm");

    }

    public void btnAddDriver(ActionEvent actionEvent) throws IOException {
        setUi("AddDriverForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));

    }

    public void btnAddMealOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddMealsForm");
    }
}

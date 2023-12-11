package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
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



public class ReceptionistDashBoardFormController {

    public Label lblTime;
    public Label lblDate;

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

    public AnchorPane repDashBoardContext;

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Do you want to Logout", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if(buttonType.get().equals(ButtonType.YES)) {
           setUi("ReceptionistLoginForm");
        }
    }



    public void SearchRoomOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SearchRoom");
    }


    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerAddForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage= (Stage) repDashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));

    }

    public void btnRoomBooking(ActionEvent actionEvent) throws IOException {
        setUi("BookingDetailsForm");
    }

    public void btnBookingDetailsOnAction(ActionEvent actionEvent) throws IOException {

        setUi("BookingBookedForm");
    }

    public void btnCkeckOutWinndow(ActionEvent actionEvent) throws IOException {
        setUi("CheckOutForm");
    }

    public void brnEmployeeInfo(ActionEvent actionEvent) throws IOException {
        setUi("EmployeeInfo");
    }

    public void btnCustomerInfo(ActionEvent actionEvent) throws IOException {

        setUi("CustomerInfo");
    }

    public void btnMelOnAction(ActionEvent actionEvent) throws IOException {
        setUi("MealOrderForm");
    }

    public void btnMealOrderDetailsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("OrderDetailsForm");
    }

    public void RoomUpdateOnAction(ActionEvent actionEvent) throws IOException {
        setUi("UpdateRoom");
    }
}

package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.BookingDetailsBo;
import bo.Custom.RoomBo;
import dao.Custom.BookingDetailsDao;
import dao.Custom.RoomDao;
import dao.Custom.impl.BookingDetailsDaoImpl;
import dao.Custom.impl.RoomDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.BookingDetailsDto;
import entity.BookingDetails;
import entity.Room;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CheckOutFormController {
    public AnchorPane root;

    public TextField txtBookingId;
    public TextField txtDate;
    public TextField txtTotal;

    public TextField txtCustomer;
    public ComboBox<String> cmbRoomId;
    public TextField price;
    public TextField txtAvailability;
    public TextField txtDayCount;

   // RoomDaoImpl roomDao = new RoomDaoImpl();
   // RoomDao roomDao= DaoFactory.getInstance().getDao(DaoType.ROOM);
   // BookingDetailsDao bookingDetailsDao=DaoFactory.getInstance().getDao(DaoType.BOOKINGDETAILS);
    private RoomBo roomBo= BoFactory.getInstance().getBo(BoType.ROOM);
    private BookingDetailsBo bookingDetailsBo=BoFactory.getInstance().getBo(BoType.BOOKINGDETAILS);

    public void initialize(){

        loadDate();
        RoomId();

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue){
                loadBookingDetails();
            }
        });
    }

    private void loadDate() {

            Timeline timeline=new Timeline(
                    new KeyFrame(Duration.ZERO, e->{
                        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
                        txtDate.setText(LocalDateTime.now().format(formatter));
                    }), new KeyFrame(Duration.seconds(1)));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();


    }

    private void loadBookingDetails() {

        try {

            ArrayList<BookingDetailsDto> arrayList = bookingDetailsBo.updateBooking(cmbRoomId.getValue());
            for (BookingDetailsDto b:arrayList){
                txtBookingId.setText(b.getBookingId());
                price.setText(String.valueOf(b.getPrice()));
                txtDayCount.setText(String.valueOf(b.getDayCount()));




            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void RoomId() {

        try {
            ResultSet set=roomBo.loadRoomId();
            ArrayList<String> customerList=new ArrayList<>();
            while (set.next()){
                customerList.add(set.getString(1));
            }
            ObservableList<String> oblist= FXCollections.observableArrayList(customerList);
            cmbRoomId.setItems(oblist);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void btnCheckOut(ActionEvent actionEvent) {

        try {
            /*String sql="update Room set availability='Available', cleanStatus='dirty' where id=?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1,cmbRoomId.getValue());
            int isUpdate = statement.executeUpdate();*/
           boolean isUpdate= roomBo.updateRoomCheckDetails((cmbRoomId.getValue()));

            if(isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"CheckOut").show();
                clearAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


    public void clearAll(){
        txtBookingId.clear();
        txtDate.clear();
        price.clear();
        txtDayCount.clear();
        cmbRoomId.setValue(null);

    }

    public void btnBackToHome(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"))));
    }
}

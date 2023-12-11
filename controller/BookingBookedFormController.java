package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.BookingBo;
import dao.Custom.BookingDao;
import dao.Custom.impl.BookingDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.BookingDto;
import entity.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.BookingTm;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class BookingBookedFormController {
    public AnchorPane root;
    public TableView tblOrders;
    public TableColumn colBookingId;
    public TableColumn colDate;
    public TableColumn colCustomer;
    public TableColumn colTotalCost;
    public TableColumn ColOption;

   // BookingDao bookingDao = new BookingDaoImpl();
  // BookingDao bookingDao= DaoFactory.getInstance().getDao(DaoType.BOOKING);
  private BookingBo bookingBo= BoFactory.getInstance().getBo(BoType.BOOKING);

    public void initialize(){
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("BookingId"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        ColOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


        loadBookingBookDetails();
    }

    private void loadBookingBookDetails() {


        try {
            ObservableList<BookingTm>obList= FXCollections.observableArrayList();

            ArrayList<BookingDto> bookings =bookingBo.bookingLoadAllBooking();
            for (BookingDto b:bookings){
                Button btn=new Button("View More");
                BookingTm tm=new BookingTm(
                        b.getBookingId(),
                        b.getDate(),
                        b.getCustomer(),
                        b.getTotalCost()
                        ,btn);
                obList.add(tm);
                btn.setOnAction(event -> {
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/BookingBookDetailsForm.fxml"));
                        Parent parent=loader.load();
                        BookingBookDetailsFormController controller =loader.getController();
                        controller.BookingBookDetails(tm.getBookingId());
                        Stage stage=new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }tblOrders.setItems(obList);

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

    }

    public void btnBackToHomeAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"))));
    }
}

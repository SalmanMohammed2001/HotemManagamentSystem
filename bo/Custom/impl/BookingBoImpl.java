package bo.Custom.impl;

import bo.Custom.BookingBo;
import controller.BookingFormController;
import dao.Custom.BookingDao;
import dao.Custom.BookingDetailsDao;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.BookingDetailsDto;
import dto.BookingDto;
import dto.CustomerDto;
import dto.RoomDto;

import entity.Booking;
import entity.BookingDetails;
import entity.Customer;
import entity.Room;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;


public class BookingBoImpl implements BookingBo {

    BookingDao bookingDao = DaoFactory.getInstance().getDao(DaoType.BOOKING);
    BookingDetailsDao bookingDetailsDao = DaoFactory.getInstance().getDao(DaoType.BOOKINGDETAILS);


    @Override
    public ArrayList<RoomDto> bookingSetRoomDetails(String id) throws SQLException {
        ArrayList<Room> arrayList = bookingDao.setRoomDetails(id);
        ArrayList<RoomDto> dtoArrayList = new ArrayList<>();
        for (Room r : arrayList) {
            dtoArrayList.add(new RoomDto(
                    r.getRoomType(),
                    r.getAvailable(),
                    r.getCleanStatus(),
                    r.getPrice()
            ));
        }
        return dtoArrayList;

    }

    @Override
    public ArrayList<CustomerDto> bookingSetCustomerDetails(String id) throws SQLException {
        ArrayList<Customer> arrayList = bookingDao.setCustomerDetails(id);
        ArrayList<CustomerDto> dtoArrayList = new ArrayList<>();
        for (Customer c : arrayList) {
            dtoArrayList.add(new CustomerDto(
                    c.getName(),
                    c.getCountry(),
                    c.getEmail(),
                    c.getMobileNo(),
                    c.getAge(),
                    c.getGender()

            ));
        }
        return dtoArrayList;
    }

    @Override
    public ResultSet bookingLoadRoomId() throws SQLException {
        return bookingDao.loadRoomId();
    }

    @Override
    public ResultSet bookingLoadCustomerId() throws SQLException {
        return bookingDao.loadCustomerId();
    }

    @Override
    public ResultSet bookingOrderId() throws SQLException {
        return bookingDao.bookingId();
    }

    @Override
    public ArrayList<BookingDto> bookingLoadAllBooking() throws SQLException, ParseException {
        ArrayList<Booking> arrayList = bookingDao.loadAllBooking();
        ArrayList<BookingDto> dtoArrayList = new ArrayList<>();
        for (Booking b : arrayList) {
            dtoArrayList.add(new BookingDto(
                    b.getBookingId(),
                    b.getDate(),
                    b.getCustomer(),
                    b.getTotalCost()
            ));
        }
        return dtoArrayList;
    }

    @Override
    public boolean bookingOrderSave(BookingDto b, ArrayList<BookingDetailsDto> details) throws SQLException {

        Connection con = null;

        try {

            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            {

                boolean add = bookingDao.save(
                        new Booking(b.getBookingId(), b.getDate(), b.getCustomer(), b.getTotalCost()));

                if (add) {
                    boolean isAllUpdate = manageRoom(details);
                    if (isAllUpdate) {
                        con.commit();
                        // jesperReport();
                        new Alert(Alert.AlertType.INFORMATION, " All Update").show();
                        //  clearAll();
                    } else {
                        con.setAutoCommit(true);
                        con.rollback();
                        new Alert(Alert.AlertType.INFORMATION, " Try again").show();

                    }

                } else {
                    con.setAutoCommit(true);
                    con.rollback();
                    new Alert(Alert.AlertType.INFORMATION, " Not Update").show();

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public boolean manageRoom(ArrayList<BookingDetailsDto> details) throws SQLException {


        for (BookingDetailsDto d : details) {


            boolean isBookingDetailsSaved = bookingDetailsDao.save(new BookingDetails(d.getRoomId(), d.getBookingId(), d.getAvailability(), d.getPrice(), d.getDayCount()));

            if (isBookingDetailsSaved) {
                boolean isAvailabilityUpdate = update(d);
                if (!isAvailabilityUpdate) {
                    return false;
                }

            } else {
                return false;
            }


        }


        return true;
    }

    @Override
    public boolean update(BookingDetailsDto d) throws SQLException {


        return bookingDetailsDao.update(new BookingDetails(d.getRoomId()));

    }
}
package bo.Custom;

import dto.BookingDetailsDto;
import dto.BookingDto;
import dto.CustomerDto;
import dto.RoomDto;
import entity.Booking;
import entity.BookingDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface BookingBo {
    public ArrayList<RoomDto> bookingSetRoomDetails(String id) throws SQLException;
    public ArrayList<CustomerDto> bookingSetCustomerDetails(String id) throws SQLException;
    public ResultSet bookingLoadRoomId() throws SQLException;
    public ResultSet bookingLoadCustomerId() throws SQLException;
    public ResultSet bookingOrderId() throws SQLException;
    public ArrayList<BookingDto>bookingLoadAllBooking() throws SQLException, ParseException;

    public boolean bookingOrderSave(BookingDto b, ArrayList<BookingDetailsDto> details) throws SQLException;

    public boolean manageRoom(ArrayList<BookingDetailsDto> details) throws SQLException;

     boolean update(BookingDetailsDto d) throws SQLException;



}
